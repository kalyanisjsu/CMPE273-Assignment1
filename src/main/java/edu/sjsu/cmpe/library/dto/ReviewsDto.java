package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import edu.sjsu.cmpe.library.domain.Reviews;

public class ReviewsDto {

	 public ArrayList<Reviews> reviews=null;
	 
	 
	 public ReviewsDto(ArrayList<Reviews> rev2) {
			this.reviews = rev2;
		}

	 
	/* public ArrayList<Reviews> getRev2() {
			return reviews;
		}*/

		public void setRev2(ArrayList<Reviews> reviews) {
			this.reviews = reviews;
		}
	 
}
