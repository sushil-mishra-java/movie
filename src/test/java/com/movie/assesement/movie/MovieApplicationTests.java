package com.movie.assesement.movie;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.assesement.movie.entity.Movie;
import com.movie.assesement.movie.service.MovieService;

@SpringBootTest
class MovieApplicationTests {
	public MockMvc mvc;
	@MockBean
	@Autowired

	private MovieService movieService;

	@Test
	@Rollback(false)
	public void testCreateMovie() {
		Movie movie = new Movie("dujkl", "Drama", 3.5f);
		Movie savedMovie = movieService.save(movie);
		assertNotNull(savedMovie);

	}

	@Test
	@Before
	public void getMovieById() {

		Movie movie = new Movie("Loha", "Thriller", 3.0f);

		//
		when(movieService.findById(8)).thenReturn(movie);

	}

	@Test

	public void getAllMovie() {
		when(movieService.findAll())
				.thenReturn(Stream.of(new Movie("Luck", "Romantic", 3.5f), new Movie("Root", "Romantic", 3.5f))
						.collect(Collectors.toList()));
		assertEquals(2, movieService.findAll().size());

	}

	@Test

	public void saveMovie() {
		Movie movie = new Movie("Prem rog", "Drama", 3.7f);
		when(movieService.save(movie)).thenReturn(movie);
		assertEquals(movie, movieService.save(movie));

	}

	@Test
	public void deleteMovie() throws Exception {
		String uri = "/movies/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Movie is deleted successsfully");
	}

	// for mapping mapTo json
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	public void updateMovie() throws Exception {
		String uri = "/movies/2";
		Movie movie = new Movie();
		movie.setTitle("Race");
		String inputJson = mapToJson(movie);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Movie is updated successsfully");
	}

}
