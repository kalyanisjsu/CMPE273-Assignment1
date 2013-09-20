package edu.sjsu.cmpe.library.domain;

import java.util.Random;

import edu.sjsu.cmpe.library.dto.LinksDto;

public class Author extends LinksDto {
	
    public Long ID;
    public String Name;
    
    public Long create_id()
    {
      	 Random randomGenerator = new Random();
         for (int i = 1; i <= 50; ++i)
         {
        	 ID = (long) randomGenerator.nextInt(100);
    	 }
        return ID;
    }   
	public void setID(Long iD) {
		//ID = iD;
	}
	public void setName(String name) {
		Name = name;
	}
}
