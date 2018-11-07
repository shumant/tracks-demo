package com.shuman.tracksdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TracksDemoApplication.class)
@AutoConfigureMockMvc
public class TracksDemoApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test() throws Exception {
            /*mvc.perform(get("/api/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("bob"));*/
    }

    @Test
    public void contextLoads() {
    }
}
