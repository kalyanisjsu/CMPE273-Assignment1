package edu.sjsu.cmpe.library;

import java.util.HashMap;





import net.sf.json.JSONArray;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import edu.sjsu.cmpe.library.api.resources.BookResource;
import edu.sjsu.cmpe.library.api.resources.RootResource;
import edu.sjsu.cmpe.library.config.LibraryServiceConfiguration;
import edu.sjsu.cmpe.library.domain.Book;


public class LibraryService extends Service<LibraryServiceConfiguration> {
	
	
	
	public static long i=1;
	
    public static void main(String[] args) throws Exception {
    	
    
	new LibraryService().run(args);
	
	
	
    }


    @Override
    public void initialize(Bootstrap<LibraryServiceConfiguration> bootstrap) {
	bootstrap.setName("library-service");
    }

    @Override
    public void run(LibraryServiceConfiguration configuration,Environment environment) throws Exception {
	/** Root API */
	environment.addResource(RootResource.class);
	/** Books APIs */
	environment.addResource(BookResource.class);
	
		
    }
}
