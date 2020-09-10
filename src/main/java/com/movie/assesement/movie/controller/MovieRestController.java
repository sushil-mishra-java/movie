package com.movie.assesement.movie.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.assesement.movie.entity.Movie;
import com.movie.assesement.movie.exception.ResourceNotFoundException;
import com.movie.assesement.movie.service.MovieService;

@RestController
@RequestMapping("/api")
public class MovieRestController {

	private MovieService movieService;

	@Autowired
	public MovieRestController(MovieService movieService) {
		this.movieService = movieService;
	}

	// expose "/movies" and return list of movies
	@GetMapping("/movies")
	public List<Movie> findAll() {
		return movieService.findAll();
	}

	// add mapping for GET/movies/{movieId}
	@GetMapping("/movies/{movieId}")
	public Movie getMovie(@PathVariable int movieId) throws ResourceNotFoundException {
		Movie theMovie = movieService.findById(movieId);

		if (theMovie == null) {
			throw new ResourceNotFoundException("Movie id not found -" + movieId);
		}
		return theMovie;
	}
	// add mapping for post movie


	@PostMapping("/movies")
	public ResponseEntity<Movie> createStudent(@Valid @RequestBody Movie movie) {
		Movie movies = movieService.save(movie);

		return new ResponseEntity<Movie>(movies, HttpStatus.OK);
	}

	// add mapping for PUT /movies - update existing movie

	@PutMapping("/movies")
	public Movie updateMovie(@RequestBody Movie theMovie) {

		movieService.save(theMovie);

		return theMovie;
	}

	// add mapping for DELETE /movies/{movieId} - delete movie

	@DeleteMapping("/movies/{movieId}")
	public String deleteMovie(@PathVariable int movieId) {

		Movie tempMovie = movieService.findById(movieId);

		// throw exception if null

		if (tempMovie == null) {
			throw new RuntimeException("Movie id not found - " + movieId);
		}

		movieService.deleteById(movieId);

		return "Deleted movie id - " + movieId;
	}

}
