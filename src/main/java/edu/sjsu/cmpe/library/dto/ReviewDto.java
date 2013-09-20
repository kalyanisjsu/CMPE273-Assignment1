package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.Reviews;

public class ReviewDto extends LinksDto{

	 private Reviews review=null;
	 
	 public ReviewDto(Reviews rev) {
			this.review=rev;
		}
		
	 public ReviewDto()
	 {
		 
	 }
		public Reviews getRev() {
			return review;
		}

		public void setRev(Reviews rev) {
			this.review = rev;
		}
	
}
