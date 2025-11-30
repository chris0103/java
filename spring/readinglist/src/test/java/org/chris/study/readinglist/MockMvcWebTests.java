package org.chris.study.readinglist;


import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.chris.study.readinglist.entity.Book;
import org.chris.study.readinglist.entity.Reader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReadingListApplication.class)
@WebAppConfiguration
public class MockMvcWebTests {

	@Autowired
	private WebApplicationContext webContext;

	private MockMvc mockMvc;

	@Before
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webContext)
				.apply(springSecurity())
				.build();
	}

	@Test
	public void homePage() throws Exception {
		mockMvc.perform(get("/readingList"))
				.andExpect(status().isOk())
				.andExpect(view().name("readingList"))
				.andExpect(model().attributeExists("books"))
				.andExpect(model().attribute("books",
						is(empty()))); // Assuming no books in the list initially
	}

	@Test
	public void postBook() throws Exception {
		mockMvc.perform(post("/")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("title", "Effective Java")
				.param("author", "Joshua Bloch")
				.param("isbn", "978-0134686097")
				.param("description", "Best practices for Java programming"))
				.andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/"));

		Book expectedBook = new Book();
		expectedBook.setId(1L);

		Reader expectedReader = new Reader();
		expectedReader.setUsername("craig");
		expectedReader.setPassword("password");
		expectedReader.setFullname("Craig Walls");
		expectedBook.setReader(expectedReader);

		expectedBook.setTitle("Effective Java");
		expectedBook.setAuthor("Joshua Bloch");
		expectedBook.setIsbn("978-0134686097");
		expectedBook.setDescription("Best practices for Java programming");

		mockMvc.perform(get("/readingList"))
				.andExpect(status().isOk())
				.andExpect(view().name("readingList"))
				.andExpect(model().attributeExists("books"))
				.andExpect(model().attribute("books", hasSize(1)))
				.andExpect(model().attribute("books",
						contains(samePropertyValuesAs(expectedBook))));
	}

}
