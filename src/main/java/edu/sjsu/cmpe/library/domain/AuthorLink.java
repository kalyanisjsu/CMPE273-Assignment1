package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;

import edu.sjsu.cmpe.library.dto.LinkDto;

public class AuthorLink {

	
	private List<LinkDto> links = new ArrayList<LinkDto>();
	
	public void addLink(LinkDto link) {
	links.add(link);
    }

    public List<LinkDto> getLinks() {
	return links;
    }

    public void setLinks(List<LinkDto> links) {
	this.links = links;
    }
}
	

