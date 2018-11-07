package com.shuman.tracksdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TracksDemoApplication.class)
@AutoConfigureMockMvc
public class TracksDemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void create_track() throws Exception {
        mvc.perform(post("/tracks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(readFile("create_track.json")))
            .andExpect(status().isOk())
            .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].name").value("bob"));
    }

    @Test
    public void contextLoads() {
    }

    private String readFile(String fileName) throws IOException {
        String path = getClass().getClassLoader().getResource(fileName).getPath();
        return Files.readString(Paths.get(path));
    }
}
