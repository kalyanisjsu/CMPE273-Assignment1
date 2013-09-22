package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import edu.sjsu.cmpe.library.domain.Reviews;

public class ReviewsDto {

	 public ArrayList  reviews=null;
	 
	 
	 public ReviewsDto(ArrayList reviews2) {
			this.reviews = reviews2;
		}

	 
	/* public ArrayList<Reviews> getRev2() {
			return reviews;
		}*/

		public void setRev2(ArrayList reviews) {
			this.reviews = reviews;
		}
	 
}
