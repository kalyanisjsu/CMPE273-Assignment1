package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Author;


public class AuthorDto extends LinksDto {

	public Author author;
	
	public AuthorDto()
	{
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public AuthorDto(Author author) {
		this.author=author;
	}



}
