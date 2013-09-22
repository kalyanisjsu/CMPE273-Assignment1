package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.Collection;

import edu.sjsu.cmpe.library.domain.Author;

public class AuthorsDto extends LinksDto{

 public Collection<Author> author=null;
	 
	 
	 public AuthorsDto(Collection<Author> collection) {
			this.author = collection;
		}


	public void setAuthor(ArrayList<Author> author) {
		this.author = author;
	}
	
	
}
