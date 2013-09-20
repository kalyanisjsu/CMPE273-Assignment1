package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;

@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto{
    private Book book=null;
 
  
   

	public BookDto(Book book) {	
    	//super();
	this.book = book;
    }
    
    public BookDto() {	
    	super();
    	//this.book=null;
    }
   
	
	

	public Book getBook() {
		
			return book;
		
		}
		
   public void setBook(Book book) {
    	
    	this.book = book;
    	
    	
    }
}
