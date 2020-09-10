package com.movie.assesement.movie.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.movie.assesement.movie.entity.Movie;

@Repository
public class MovieDaoHibernateImpl implements MovieDao {

	// define field for entity manager
	private EntityManager entityManager;

	// set up constructor injection
	@Autowired
	public MovieDaoHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override

	public List<Movie> findAll() {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create a query
		Query<Movie> theQuery = currentSession.createQuery("from Movie", Movie.class);

		// execute query and get result list
		List<Movie> movies = theQuery.getResultList();

		// return the results
		return movies;
	}

	@Override
	public Movie findById(int theId) {

		// get the current hibernate seesion
		Session currentSession = entityManager.unwrap(Session.class);

		// get the movie
		Movie theMovie = currentSession.get(Movie.class, theId);
		;

		// return the movie

		return theMovie;
	}

	@Override
	public void save(Movie theMovie) {
		// getting hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// save the movie into database

		currentSession.saveOrUpdate(theMovie);

	}

	@Override
	public void deleteById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// delete object with the primary key
		Query theQuery = currentSession.createQuery("delete from Movie where id=:movieId");
		theQuery.setParameter("movieId", theId);
		theQuery.executeUpdate();

	}

}
