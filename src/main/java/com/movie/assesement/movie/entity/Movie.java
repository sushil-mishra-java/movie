package com.movie.assesement.movie.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "movie")
public class Movie {

	// define fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")

	private int id;

	@Column(name = "title")
	@NotNull(message = "Enter title")
	@Size(min = 3, message = "title should have atleast 3 characters")
	private String title;

	@Column(name = "category")
	@NotNull
	@Size(min = 3, message = "category should have atleast 3 characters")
	private String category;

	@Column(name = "rating")
	@NotNull
	private float starRating;

	// define constructors

	public Movie() {

	}

	public Movie(String title, String category, float starRating) {
		super();
		// this.id = id;
		this.title = title;
		this.category = category;
		this.starRating = starRating;
	}

	// define getter/setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public float getStarRating() {
		return starRating;
	}

	public void setStarRating(float starRating) {
		this.starRating = starRating;
	}

	// define tostring

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", category=" + category + ", starRating=" + starRating + "]";
	}

}
