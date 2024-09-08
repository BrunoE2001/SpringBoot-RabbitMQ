package com.capitannemo.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RabbitmqApplication.class)
@AutoConfigureMockMvc()
class RabbitmqApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	public static final String ROOT = "/api/v1";

	@Test
	void messageControllerTest() throws Exception {
		var root = ROOT + "/publish";
		mockMvc.perform(get(root).param("message", "message"))
				.andExpect(status().is2xxSuccessful());
		assertNotNull(root);
	}

	@Test
	void messageJsonControllerTest() throws Exception {
		var root = ROOT + "/publish/json";
		var fileName = "json/user.json";
		var body = Files.readString(new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile()).toPath());

		mockMvc.perform(
				post(root)
						.content(body)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().is2xxSuccessful());
		assertNotNull(root);
	}

	/**
	 * Tests the main method of the RabbitmqApplication class, ensuring that it throws an IllegalArgumentException when called with null arguments.
	 *
	 * @throws IllegalArgumentException as expected when the main method is called with null arguments.
	 */
	@Test
	void RabbitmqApplicationTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			RabbitmqApplication.main(null);
		});
	}
}
