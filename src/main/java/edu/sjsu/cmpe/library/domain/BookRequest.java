package edu.sjsu.cmpe.library.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BookRequest {


	@NotEmpty
	@JsonProperty("title") public String title;
	@NotEmpty
	@JsonProperty("publication-date") public String publication_date;
	//@Pattern(regexp = "[lost]|[available]")
	//@JsonProperty public String status;
	
	//@List(value= {@Pattern(regexp="available")|| @Pattern(regexp="checked-out"), @Pattern(regexp="in-queue"), @Pattern(regexp="lost")})
	
	public String language,status;
	ArrayList<Reviews> reviews = new ArrayList<Reviews>();
	@JsonProperty("num-pages")private int num_pages;
	@Valid
	public ArrayList<Author> authors = new ArrayList();
	
	public BookRequest(@JsonProperty("title") String title, @JsonProperty("publication-date") String publication_date, @JsonProperty("status") String status) {
		this.title = title;
		this.publication_date=publication_date;
		this.status = "available";
	}
	
	
	public ArrayList<Reviews> getReviews() {
		return reviews;
	}
	public void setReviews(ArrayList<Reviews> reviews) {
		this.reviews = reviews;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPublication_date() {
		return publication_date;
	}
	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}
	public int getNum_pages() {
		return num_pages;
	}
	public void setNum_pages(int num_pages) {
		this.num_pages = num_pages;
	}
	public ArrayList<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(ArrayList<Author> authors) {
		this.authors = authors;
	}
	
	
	
}
