package com.shuman.tracksdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuman.tracksdemo.model.*;
import com.shuman.tracksdemo.repository.CarRepository;
import com.shuman.tracksdemo.repository.TrackRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TracksDemoApplication.class)
@AutoConfigureMockMvc
@Transactional
public class TracksDemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void create_and_get_track() throws Exception {
        // prepare
        Track trackToBeCreated = Util.track();

        // act
        ResultActions resultActions = performPost("/tracks", "create_track.json");

        // assert
        UUID createdTrackId = extractCreatedEntityId(resultActions);
        trackToBeCreated.setId(createdTrackId);

        assertEquals("Created track mismatch", trackToBeCreated, trackRepository.getOne(createdTrackId));

        String tracksResponse =
                performGet("/tracks/").andReturn().getResponse().getContentAsString();
        Track[] trackFromApi = objectMapper.readValue(tracksResponse, Track[].class);

        assertEquals("API cars mismatch", trackToBeCreated, trackFromApi[0]);
    }

    @Test
    public void update_track() throws Exception {
        // prepare
        Track savedTrack = trackRepository.save(Util.track());

        // act
        performPut("/tracks/" + savedTrack.getId(), "update_track.json");

        // assert
        savedTrack.setName("Millbrooksdfsd");
        savedTrack.setLength(new TrackLength().setUnit(LengthUnit.KM).setValue(7.472823932));

        assertEquals("Track update mismatch", savedTrack, trackRepository.getOne(savedTrack.getId()));
    }

    @Test
    public void remove_track() throws Exception {
        // prepare
        Track savedTrack = trackRepository.save(new Track().setName("dummy track"));

        Car savedCar1 = carRepository.save(Util.car1(savedTrack));
        Car savedCar2 = carRepository.save(Util.car2(savedTrack));

        // act
        performDelete("/tracks/" + savedTrack.getId());

        // assert
        assertEquals("Track has not been deleted", 0, trackRepository.findAll().size());
        assertEquals("Cars have not been deleted", 0, carRepository.findAll().size());
    }

    @Test
    public void add_and_get_cars() throws Exception {
        // prepare
        Track savedTrack = trackRepository.save(new Track().setName("dummy track"));

        Car carToCreate1 = Util.car1(savedTrack);
        Car carToCreate2 = Util.car2(savedTrack);

        // act
        UUID car1Id = extractCreatedEntityId(performPost("/tracks/" + savedTrack.getId().toString() + "/cars", "create_car_1.json"));
        UUID car2Id = extractCreatedEntityId(performPost("/tracks/" + savedTrack.getId().toString() + "/cars", "create_car_2.json"));

        // assert
        carToCreate1.setId(car1Id).setTrack(savedTrack);
        carToCreate2.setId(car2Id).setTrack(savedTrack);

        List<Car> carsFromDb = carRepository.findByTrackId(savedTrack.getId());

        assertEquals("Database cars mismatch", List.of(carToCreate1, carToCreate2), carsFromDb);

        String carsResponse =
                performGet("/tracks/" + savedTrack.getId() + "/cars").andReturn().getResponse().getContentAsString();
        Car[] carsFromApi = objectMapper.readValue(carsResponse, Car[].class);

        carToCreate1.setTrack(null);
        carToCreate2.setTrack(null);

        assertArrayEquals("API cars mismatch", new Car[]{carToCreate1, carToCreate2}, carsFromApi);
    }

    @Test
    public void update_car() throws Exception {
        // prepare
        Track savedTrack = trackRepository.save(new Track().setName("dummy track"));

        Car car = Util.car1(savedTrack);

        Car savedCar = carRepository.save(car);

        // act
        performPut("/cars/" + savedCar.getId(), "update_car.json");

        // assert
        savedCar.setCode("rddlsdfb1");
        savedCar.setMaxSpeed(new CarSpeed().setUnit(SpeedUnit.MPS).setValue(923.3423));
        savedCar.setTrack(null);

        assertEquals("Car update mismatch", savedCar, carRepository.getOne(savedCar.getId()));
    }

    @Test
    public void update__negative_speed_fail() throws Exception {
        // prepare
        Track savedTrack = trackRepository.save(new Track().setName("dummy track"));

        Car car = Util.car1(savedTrack);

        Car savedCar = carRepository.save(car);

        // act
        try {
            mvc.perform(put("/cars/" + savedCar.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(readFile("update_car_negative_speed.json")))

               // assert
               .andExpect(status().is5xxServerError());
        } catch (Exception e) {
            assertTrue("Wrong exception", e.getCause() instanceof IllegalArgumentException);
        }
    }

    @Test
    public void remove_car() throws Exception {
        // prepare
        Track savedTrack = trackRepository.save(new Track().setName("dummy track"));

        Car carToSave1 = Util.car1(savedTrack);
        Car carToSave2 = Util.car2(savedTrack);

        Car savedCar1 = carRepository.save(carToSave1);
        Car savedCar2 = carRepository.save(carToSave2);

        // act
        performDelete("/cars/" + savedCar1.getId());

        // assert
        assertEquals("Track cars mismatch", List.of(savedCar2), carRepository.findByTrackId(savedTrack.getId()));
    }

    private UUID extractCreatedEntityId(ResultActions resultActions) throws Exception {
        String responseString = resultActions.andReturn().getResponse().getContentAsString();
        return UUID.fromString(objectMapper.readTree(responseString).path("id").asText());
    }

    private ResultActions performPost(String url, String contentFileName) throws Exception {
        return mvc.perform(post(url)
                                   .contentType(MediaType.APPLICATION_JSON)
                                   .content(readFile(contentFileName)))

                  // assert
                  .andExpect(status().isOk())
                  .andExpect(content()
                                     .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                  .andExpect(jsonPath("$.id").exists());
    }

    private ResultActions performPut(String url, String contentFileName) throws Exception {
        return mvc.perform(put(url)
                                   .contentType(MediaType.APPLICATION_JSON)
                                   .content(readFile(contentFileName)))

                  // assert
                  .andExpect(status().isOk())
                  .andExpect(content()
                                     .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    private ResultActions performGet(String url) throws Exception {
        return mvc.perform(get(url))

                  // assert
                  .andExpect(status().isOk())
                  .andExpect(content()
                                     .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    private ResultActions performDelete(String url) throws Exception {
        return mvc.perform(delete(url))

                  // assert
                  .andExpect(status().isOk());
    }

    private String readFile(String fileName) throws IOException {
        String path = getClass().getClassLoader().getResource(fileName).getPath();
        return Files.readString(Paths.get(path));
    }
}
