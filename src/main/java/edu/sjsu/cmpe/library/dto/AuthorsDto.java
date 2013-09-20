package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import edu.sjsu.cmpe.library.domain.Author;

public class AuthorsDto {

 public ArrayList<Author> author=null;
	 
	 
	 public AuthorsDto(ArrayList<Author> authors) {
			this.author = authors;
		}


	public void setAuthor(ArrayList<Author> author) {
		this.author = author;
	}
	
	
}
