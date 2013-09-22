package edu.sjsu.cmpe.library.domain;

import java.util.Random;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.jersey.params.IntParam;

public class Reviews {

	
    public Long ID;
    @NotEmpty
    @Length(max=5, message="Rate from 1-5")
    @JsonProperty("rating") public String Rating;
    @NotEmpty
    @JsonProperty("comment") public String Comment;
    Random randomGenerator;
  
	public Long createRevID()
    {
      	 randomGenerator = new Random();
         ID = (long) randomGenerator.nextInt(100);
    	 return ID;
    }   
	public void setID(Long iD) {
		ID = iD;
	}

	
	public void setRating(String rating) {
		Rating = rating;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
}
