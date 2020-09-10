package com.movie.assesement.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.assesement.movie.dao.MovieDao;
import com.movie.assesement.movie.entity.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieDao movieDao;

//  inject movie dao (use constructor injection
	@Autowired
	public MovieServiceImpl(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	@Transactional
	public List<Movie> findAll() {

		return movieDao.findAll();
	}

	@Transactional
	public Movie findById(int theId) {

		return movieDao.findById(theId);
	}

	@Transactional
	public Movie save(Movie theMovie) {

		movieDao.save(theMovie);
		return theMovie;
	}

	@Transactional
	public void deleteById(int theId) {

		movieDao.deleteById(theId);
	}

}
