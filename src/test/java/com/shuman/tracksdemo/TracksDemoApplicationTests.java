package com.shuman.tracksdemo;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TracksDemoApplication.class)
@AutoConfigureMockMvc
public class TracksDemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	private static EmbeddedPostgres postgres;

	@BeforeClass
	public static void init() throws Exception{
		postgres = EmbeddedPostgres.builder().setPort(7839).start();
	}

	@After
	public void destroy() throws Exception{
		postgres.close();
	}

	@Test
	public void test() throws Exception {
		mvc.perform(get("/api/employees")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$[0].name").value("bob"));
	}

	@Test
	public void contextLoads() {
	}
}
