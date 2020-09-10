package com.movie.assesement.movie.dao;

import java.util.List;

import com.movie.assesement.movie.entity.Movie;

public interface MovieDao {
	public List<Movie> findAll();

	public Movie findById(int theId);

	public void save(Movie theMovie);

	public void deleteById(int theId);

}
