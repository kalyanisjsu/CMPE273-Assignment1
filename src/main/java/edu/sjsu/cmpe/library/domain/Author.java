package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;

public class Author{
	
		private Long ID=null;
	    private String Name=null;;
	    
	    public Long createAuthorID()
	    {
	      	 Random randomGenerator = new Random();
	         for (int i = 1; i <= 50; ++i)
	         {
	        	 ID = (long) randomGenerator.nextInt(100);
	    	 }
	        return ID;
	    }

		public void setID(Long iD) {
		//	ID = iD;
		}

		public void setName(String name) {
			Name = name;
		}

		public Long getID() {
			return ID;
		}

		public String getName() {
			return Name;
		}

		
		
	

	
}
