package edu.sjsu.cmpe.library.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.ReverbType;

import net.sf.json.JSONArray;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe.library.LibraryService;
import edu.sjsu.cmpe.library.dto.LinkDto;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Book
{
		public long isbn;
	    public String title,language,status;
	    public ArrayList<Reviews> reviews = new ArrayList<Reviews>();
	    public ArrayList<Author> authors = new ArrayList();
	    public static HashMap<Long, ArrayList> BookAuthorsMap = new HashMap<Long, ArrayList>();
	    @JsonProperty("publication-date") private String publication_date;
	    @JsonProperty("num-pages")private int num_pages;
	    Author author;
	    
	    
	    
	    public Book()
	    {
	    	
	    }
	    
	    
	        
	 /*  public ArrayList<Author> getAuthors() {
		   
		   for (int i = 0; i < authors.size(); i++) {
			   
			   author = authors.get(i);
			   author.addLink(new LinkDto("create-review","/books/" + 123 +"/reviews", "POST" ));
				//authors.add(author);
		} 
		   
		return authors;
		}*/

	   
		/*public void setAuthors(ArrayList<Author> authors) {
			this.authors = authors;
			for (int i = 0; i < authors.size(); i++) {
				author = authors.get(i);
				Long author_id = author.create_id();
			//	author.setID(author_id);
				System.out.println("Inside authors :: "  + author_id);
				//author.addLink(new LinkDto("create-review","/books/" + 123 +"/reviews", "POST" ));
			}
		}*/

	   /* public ArrayList getAuthors() {
			return authors;
		}



		public void setAuthors(ArrayList authors) {
			this.authors = authors;
		}*/



	public long create_isbn()
	    {
	      	 Random randomGenerator = new Random();
	         for (int i = 1; i <= 50; ++i)
	         {
	        	 isbn = (long) randomGenerator.nextInt(100);
	    	      
	         }
	        // BookAuthorsMap.put(isbn, authors);
	      
	         author = (Author) authors.get(0);
	         authors.clear();
	         author.addLink(new LinkDto("view-book", "/books/" + isbn,"GET"));
	         System.out.println("Author data:: " + authors + authors.size() );
	         return isbn;
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
		
		public void addRev(Reviews revlist) {
			reviews.add(revlist);
	    }

		
    
	   	   
}
