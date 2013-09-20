package edu.sjsu.cmpe.library.domain;

import java.util.Random;

import com.yammer.dropwizard.jersey.params.IntParam;

public class Reviews {

    public Long ID;
    public int Rating;
    public String Comment;
  
	public Long create_revid()
    {
      	 Random randomGenerator = new Random();
         for (int i = 1; i <= 50; ++i)
         {
        	 ID = (long) randomGenerator.nextInt(100);
    	      
         }
        return ID;
    }   
	public void setID(Long iD) {
		ID = iD;
	}

	
	public void setRating(int rating) {
		Rating = rating;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
}
