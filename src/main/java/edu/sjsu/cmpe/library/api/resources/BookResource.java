package edu.sjsu.cmpe.library.api.resources;

import java.lang.Object;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import javax.validation.Valid;
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
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.BookRequest;
import edu.sjsu.cmpe.library.domain.Reviews;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class BookResource {

	public static Map<Long, Object> book_map =new HashMap<Long, Object>();
	public static Map<Long, Reviews> ReviewMap = new HashMap<Long, Reviews>();
	public static Map<Long, ArrayList> BookReviewMap = new HashMap<Long, ArrayList>();
	public static Map<Long, Map<Long,Reviews>> BookSpecReviewMap = new HashMap<Long, Map<Long,Reviews>>();
	public static Map<Long, Map<Long,Author>> bookAuthorMap = new HashMap<Long, Map<Long,Author>>();
	public static ArrayList<Reviews> allreviews = new ArrayList<Reviews>();

	public BookResource() {

	}

	@GET
	@Path("/{isbn}")
	@Timed(name = "view-book")

	public Response getBookByIsbn(@PathParam("isbn") LongParam isbn) throws JsonParseException, JsonMappingException, IOException
	{
		try
		{
			System.out.println("Inside get" +isbn.get());
			System.out.println(book_map.size());
			Book book = (Book) book_map.get(isbn.get());
			System.out.println(book.getTitle());
			BookDto bookResponse = new BookDto(book);
			bookResponse.addLink(new LinkDto("view-book", "/books/" + isbn,"GET"));
			bookResponse.addLink(new LinkDto("update-book","/books/" + isbn, "PUT"));
			bookResponse.addLink(new LinkDto("delete-book","/books/" + isbn, "DELETE"));
			bookResponse.addLink(new LinkDto("create-review","/books/" + isbn +"/reviews", "POST"));
			if(allreviews.size()>0)
				bookResponse.addLink(new LinkDto("view-all-reviews","/books/" + isbn + "/reviews", "GET"));
			return Response.ok(bookResponse).build();
		}
		catch(Exception e )
		{
			return Response.ok("Book doesn't exist").build();
		}

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Timed(name = "create-book")

	// public BookDto createBook(@QueryParam("title")String title, @QueryParam("publication-date") String publication_date, @QueryParam("language") String language,  @QueryParam("num-pages") int num_pages ,@QueryParam("status") String status) throws JsonGenerationException, JsonMappingException, IOException

	public Response createBook(@Valid BookRequest bookRequest)
	{
		ArrayList<LinkDto> authors = new ArrayList();
		Book book = new Book(bookRequest.getTitle(), bookRequest.getAuthors(),bookRequest.getLanguage(),bookRequest.getNum_pages(),bookRequest.getPublication_date(),bookRequest.getStatus(),bookRequest.getReviews());
		Long new_isbn = book.getIsbn();
		book_map.put(new_isbn,book);
		System.out.println(book_map.size());
		Map<Long,Author> authorMap =  new HashMap<Long, Author>();
		for(Author author: bookRequest.getAuthors())
		{
			System.out.println("**** " + isAuthorNameEmpty(author));
			if(isAuthorNameEmpty(author))
			{
				return Response.created(null).entity("Please enter Author name").build();
			}
			Long author_id = author.createAuthorID();
			authorMap.put(author_id, author);
			authors.add(new LinkDto("view-author","/books/" + new_isbn + "/authors/" + author_id , "GET"));
		}

		book.setAuthors(authors);
		bookAuthorMap.put(new_isbn, authorMap);
		System.out.println("bookAuthorMap" + bookAuthorMap.size() +authorMap.size() );
		LinksDto linkResponse = new LinksDto();
		//BookDto bookResponse = new BookDto();
		linkResponse.addLink(new LinkDto("view-book", "/books/" + new_isbn,"GET"));
		linkResponse.addLink(new LinkDto("update-book","/books/" + new_isbn, "PUT"));
		linkResponse.addLink(new LinkDto("delete-book","/books/" + new_isbn, "PUT"));
		linkResponse.addLink(new LinkDto("create-review","/books/" + new_isbn + "/reviews", "POST"));
		return Response.created(null).entity(linkResponse).build();
	}



	public boolean isAuthorNameEmpty(Author author) {
		if(author.getName().isEmpty())
			return true;

		return false;
	}

	@DELETE
	@Path("/{isbn}")
	@Timed(name = "delete-book")

	public Response deleteBook(@PathParam("isbn") LongParam isbn) throws JsonGenerationException, JsonMappingException, IOException 
	{
		try
		{
		System.out.println("Inside delete");
		System.out.println(book_map.size());
		book_map.remove(isbn.get());
		BookReviewMap.remove(isbn.get());
		LinksDto linkResponse = new LinksDto();
		//BookDto bookResponse = new BookDto();
		linkResponse.addLink(new LinkDto("create-book", "/books/","POST"));
		return Response.ok(linkResponse).build();
		}
		catch(Exception e)
		{
			return Response.ok("Please check Book ID").build();
		}

	}

	@PUT
	@Path("/{isbn}")
	@Timed(name = "update-book")

	public Response updateBook(@PathParam("isbn") LongParam isbn, @QueryParam("status") String status) throws IOException
	{
		System.out.println("Inside update");
		System.out.println(book_map.size());
		Book book = (Book) book_map.get(isbn.get());
		if(status.equalsIgnoreCase("available")||status.equalsIgnoreCase("lost")||status.equalsIgnoreCase("checked-out")||status.equalsIgnoreCase("in-queue"))
		{
			book.setStatus(status);
			LinksDto linkResponse = new LinksDto();
			//BookDto bookResponse = new BookDto();
			linkResponse.addLink(new LinkDto("view-book", "/books/" + isbn,"GET"));
			linkResponse.addLink(new LinkDto("update-book","/books/" + isbn, "PUT"));
			linkResponse.addLink(new LinkDto("delete-book","/books/" + isbn, "DELETE"));
			linkResponse.addLink(new LinkDto("create-review","/books/" + isbn + "/reviews", "POST"));
			if(allreviews.size()>0)
				linkResponse.addLink(new LinkDto("view-all-reviews", "/books/" + isbn + "/reviews", "GET"));
			return Response.ok(linkResponse).build();
		}
		else
			return Response.ok("Please enter valid status (available/checked-out/in-queue/lost)").build();
	}

	@POST
	@Path("/{isbn}/reviews")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "create-review")

	public Response createBookReview(@PathParam("isbn") LongParam isbn,@Valid Reviews review)
	{	
		try
		{
		System.out.println("Inside review 1");
		Book book = (Book) book_map.get(isbn.get());
		Reviews rev = new Reviews();
		Long rev_id = rev.createRevID();
		review.setID(rev_id);
		ReviewMap.put(rev_id, review);
		allreviews.add(review);
		BookReviewMap.put(isbn.get(), allreviews);
		BookSpecReviewMap.put(isbn.get(),ReviewMap);
		System.out.println(BookSpecReviewMap.size());
		//ReviewDto revResponse = new ReviewDto();
		LinksDto linkResponse = new LinksDto();
		linkResponse.addLink(new LinkDto("view-review", "/books/" + isbn + "/reviews/" + rev_id,"GET"));
		return Response.created(null).entity(linkResponse).build();
		}
		catch(Exception e)
		{
			return Response.created(null).entity("Please check Book ID").build();
		}
	}

	@GET
	@Path("/{isbn}/reviews/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "view-review")
	public Response getBookReview(@PathParam("isbn") LongParam isbn,@PathParam("id") LongParam id)
	{
		try
		{
		System.out.println("**** " + BookSpecReviewMap.size());
		Reviews reviews = BookSpecReviewMap.get(isbn.get()).get(id.get());
		ReviewDto revResponse = new ReviewDto(reviews);
		revResponse.addLink(new LinkDto("view-review", "/books/" + isbn + "/reviews/" + id ,"GET"));
		return Response.ok(revResponse).build();
		}
		catch(Exception e)
		{
			return Response.ok("Please check Book ID / Review ID ").build();
		}

	}

	@GET
	@Path("/{isbn}/reviews")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name="view-all-reviews")

	public Response getAllReview(@PathParam("isbn") LongParam isbn)
	{
		try
		{
			System.out.println("Inside all review" + BookReviewMap.get(isbn.get()));
			ArrayList reviews = BookReviewMap.get(isbn.get());
			ReviewsDto reviewsResponse = new ReviewsDto(reviews);
			return Response.ok(reviewsResponse).build();
		}
		catch(Exception e)
		{
			return Response.ok("Book doesn't exist").build();	
		}
	}

	@GET
	@Path("/{isbn}/authors/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name = "view-author")
	public Response getBookAuthor(@PathParam("isbn") LongParam isbn,@PathParam("id") LongParam id)
	{	
		try
		{
			Author author=bookAuthorMap.get(isbn.get()).get(id.get());
			AuthorDto authResponse = new AuthorDto(author);
			authResponse.addLink(new LinkDto("view-author", "/books/" + isbn + "/authors/" + id ,"GET"));
			return Response.ok(authResponse).build();
		}
		catch(Exception e)
		{
			return Response.ok("Please check Book ID / Author ID ").build();
		}
	}



	@GET
	@Path("/{isbn}/authors")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Timed(name="view-all-authors")

	public Response getAllAuthors(@PathParam("isbn") LongParam isbn)
	{
		try
		{
			System.out.println("Inside all authors");
			Map<Long, Author> authorDetails = bookAuthorMap.get(isbn.get());
			AuthorsDto authorsResponse = new AuthorsDto(authorDetails.values());
			return Response.ok(authorsResponse).build();
		}
		catch(Exception e)
		{
			return Response.ok("Book doesn't exist").build();
		}
	}



}

