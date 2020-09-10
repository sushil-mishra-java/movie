package com.movie.assesement.movie.service;

import java.util.List;

import com.movie.assesement.movie.entity.Movie;

public interface MovieService {
	public List<Movie> findAll();

	public Movie findById(int theId);

	public Movie save(Movie theMovie);

	public void deleteById(int theId);

}
