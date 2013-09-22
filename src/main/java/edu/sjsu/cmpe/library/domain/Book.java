package edu.sjsu.cmpe.library.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.ReverbType;

import org.json.simple.JSONObject;

import net.sf.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe.library.LibraryService;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Book
{
	public long isbn;
	public long getIsbn() {
		return isbn;
	}

	public String title,language,status;
	public ArrayList<LinkDto> authors = new ArrayList();
	public ArrayList<Reviews> reviews = new ArrayList();
	public static HashMap<Long, Object> AuthorsMap = new HashMap<Long, Object>();
	public static HashMap<Long, ArrayList> BookAuthorsMap = new HashMap<Long, ArrayList>();
	@JsonProperty("publication-date") private String publication_date;
	@JsonProperty("num-pages")private int num_pages;
	Random randomGenerator;

	public ArrayList<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Reviews> reviews) {
		this.reviews = reviews;
	}

	public ArrayList<LinkDto> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<LinkDto> authors) {
		this.authors = authors;
	}

	public Book()
	{

	}
	
	public Book(String title1, ArrayList<Author> authors2, String lang, int num_pages, String pub_date, String status, ArrayList<Reviews> reviews) {
		
		randomGenerator = new Random();
		isbn = (long) randomGenerator.nextInt(100);
		this.title = title1;
		this.language = lang;
		this.num_pages = num_pages;
		this.publication_date = pub_date;
		this.status=status;
		this.reviews=reviews;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
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

	//**Add reviews to arraylist***///

	/*public void addRev(Reviews revlist) {
		reviews.add(revlist);
	}*/




}
