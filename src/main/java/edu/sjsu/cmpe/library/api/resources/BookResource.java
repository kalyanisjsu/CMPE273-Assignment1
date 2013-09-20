package edu.sjsu.cmpe.library.api.resources;

import java.lang.Object;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.sf.json.JSONArray;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.jersey.params.IntParam;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.LibraryService;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Reviews;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;



@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class BookResource {
	
	public static HashMap<Long, Object> book_map =new HashMap<Long, Object>();
	public static HashMap<Long, Object> ReviewMap = new HashMap<Long, Object>();
	public static HashMap<Long, ArrayList> BookReviewMap = new HashMap<Long, ArrayList>();
	public static ArrayList<Reviews> allrev = new ArrayList<Reviews>();
	public BookResource() {
		
  }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    
    public Response getBookByIsbn(@PathParam("isbn") LongParam isbn) throws JsonParseException, JsonMappingException, IOException
    {
    	
    	//File file =  new File("D:\\books_get.json");
    	//FileWriter fout = new FileWriter(file,true);
    	//ObjectMapper mapper = new ObjectMapper();
         System.out.println("Inside get" +isbn + isbn.get());
		 System.out.println(book_map.size());
		 Book book = (Book) book_map.get(isbn.get());
		//mapper.writeValue(fout, book);
		 //System.out.println(book.getTitle());
		 //	book = mapper.readValue(file, Book.class);
		 BookDto bookResponse = new BookDto(book);
		 bookResponse.addLink(new LinkDto("view-book", "/books/" + isbn,"GET"));
		 bookResponse.addLink(new LinkDto("update-book","/books/" + isbn, "PUT"));
		 bookResponse.addLink(new LinkDto("delete-book","/books/" + isbn, "DELETE"));
		 bookResponse.addLink(new LinkDto("create-review","/books/" + isbn +"/reviews", "POST"));
		 if(allrev.size()>0)
		 bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + isbn + "/reviews", "GET"));
		 // add more links
		 return Response.ok(bookResponse).build();
		 // return Response.st.entity(bookResponse).build();
	}
        
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Timed(name = "create-book")
    
    // public BookDto createBook(@QueryParam("title")String title, @QueryParam("publication-date") String publication_date, @QueryParam("language") String language,  @QueryParam("num-pages") int num_pages ,@QueryParam("status") String status) throws JsonGenerationException, JsonMappingException, IOException
   
      public Response createBook(Book book)
    {
        Long new_isbn = book.create_isbn();
        book.setIsbn(new_isbn);
        book_map.put(new_isbn,book);
        System.out.println(book_map.size());
        BookDto bookResponse = new BookDto(book);
    	bookResponse.addLink(new LinkDto("view-book", "/books/" + new_isbn,"GET"));
    	bookResponse.addLink(new LinkDto("update-book","/books/" + new_isbn, "PUT"));
    	bookResponse.addLink(new LinkDto("delete-book","/books/" + new_isbn, "PUT"));
    	bookResponse.addLink(new LinkDto("create-review","/books/" + new_isbn + "/reviews", "POST"));
    	
    	// FileWriter fout = new FileWriter("D:\\books.json",true);
    	//ObjectMapper mapper = new ObjectMapper();
        //mapper.writeValue(fout, book_map.get(new_isbn));
    	//return Response.ok(bookResponse).build();
    	return Response.created(null).entity(bookResponse).build();
       	//return bookResponse;
    }
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    
    public Response deleteBook(@PathParam("isbn") LongParam isbn) throws JsonGenerationException, JsonMappingException, IOException 
    {
    	System.out.println("Inside delete");
    	System.out.println(book_map.size());
    	Book book = (Book) book_map.get(isbn.get());
    	book_map.remove(isbn.get());
    	//FileWriter fout = new FileWriter("D:\\books.json",true);
     	//ObjectMapper mapper = new ObjectMapper();
       // mapper.writeValue(fout, book_map);  	
    	BookDto bookResponse = new BookDto(book);
    	bookResponse.addLink(new LinkDto("create-book", "/books/","POST"));
    	return Response.ok(bookResponse).build();
    	
    }
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    
    public Response updateBook(@PathParam("isbn") LongParam isbn, @QueryParam("status") String status) throws IOException
    {
    	System.out.println("Inside update");
    	System.out.println(book_map.size());
    	Book book = (Book) book_map.get(isbn.get());
    	book.setStatus(status);
    	//book_map.remove(isbn.get());
    	//book.setStatus(status);
    	//book_map.put(isbn.get(), book);
    	//FileWriter fout = new FileWriter("D:\\books.json",true);
     	//ObjectMapper mapper = new ObjectMapper();
        //mapper.writeValue(fout, book_map);  	
    	BookDto bookResponse = new BookDto(book);
    	bookResponse.addLink(new LinkDto("view-book", "/books/" + isbn,"GET"));
    	bookResponse.addLink(new LinkDto("update-book","/books/" + isbn, "PUT"));
    	bookResponse.addLink(new LinkDto("delete-book","/books/" + isbn, "DELETE"));
    	bookResponse.addLink(new LinkDto("create-review","/books/" + isbn + "/reviews", "POST"));
    	if(allrev.size()>0)
    	bookResponse.addLink(new LinkDto("view-all-reviews", "/books/" + isbn + "/reviews", "GET"));
    	return Response.ok(bookResponse).build();
    	
    }
    
    @POST
	@Path("/{isbn}/reviews")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @Timed(name = "create-review")
   
	public Response createBookReview(@PathParam("isbn") LongParam isbn,Reviews reviews)
	{
    	System.out.println("Inside review 1");
    	Reviews r = new Reviews();
		Book book = (Book) book_map.get(isbn.get());
		Long rev_id = r.create_revid();
		reviews.setID(rev_id);
		book.addRev(reviews);
		allrev.add(reviews);
		BookReviewMap.put(isbn.get(), allrev);
		ReviewMap.put(rev_id, reviews);
		System.out.println(ReviewMap.size());
		ReviewDto revResponse = new ReviewDto();
		revResponse.addLink(new LinkDto("view-review", "/books/" + isbn + "/reviews/" + rev_id,"GET"));
		return Response.created(null).entity(revResponse).build();
    }
   
    @GET
	@Path("/{isbn}/reviews/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    @Timed(name = "view-review")
    public Response getBookReview(@PathParam("isbn") LongParam isbn,@PathParam("id") LongParam id)
    {
	  Reviews rev = (Reviews) ReviewMap.get(id.get());
	  System.out.println("Inside review :: " + ReviewMap.size());
	  ReviewDto revResponse = new ReviewDto(rev);
	//  BookReviewMap.put(isbn.get(), Book.reviews);
	 // bookResponse.addRev(rev);
	  revResponse.addLink(new LinkDto("view-review", "/books/" + isbn + "/reviews/" + id ,"GET"));
      return Response.ok(revResponse).build();
    	
    }
    
    @GET
   	@Path("/{isbn}/reviews")
   	@Produces(MediaType.APPLICATION_JSON)
   	@Consumes(MediaType.APPLICATION_JSON)
   	@Timed(name="view-all-reviews")
    
   	public Response getAllReview(@PathParam("isbn") LongParam isbn)
   	{
       	System.out.println("Inside all review" + BookReviewMap.get(isbn));
       	ArrayList<Reviews> revarr = BookReviewMap.get(isbn.get());
       	ReviewsDto reviewsResponse = new ReviewsDto(revarr);
       	//bookResponse.addLink(new LinkDto("view-review", "/books/" + isbn + "/reviews/" ,"GET"));
       	return Response.ok(reviewsResponse).build();
   	}
    
    @GET
   	@Path("/{isbn}/authors")
   	@Produces(MediaType.APPLICATION_JSON)
   	@Consumes(MediaType.APPLICATION_JSON)
   	@Timed(name="view-all-authors")
    
   	public Response getAllAuthors(@PathParam("isbn") LongParam isbn)
   	{
       	System.out.println("Inside all authors" + Book.BookAuthorsMap.size());
       	ArrayList<Author> allauthors = Book.BookAuthorsMap.get(isbn.get());
       	AuthorsDto authorsResponse = new AuthorsDto(allauthors);
       	//bookResponse.addLink(new LinkDto("view-review", "/books/" + isbn + "/reviews/" ,"GET"));
       	return Response.ok(authorsResponse).build();
   	}
 }

