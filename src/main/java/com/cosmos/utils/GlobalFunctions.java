package com.cosmos.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.cosmos.dtos.general.PageDTO;

@Component
public class GlobalFunctions {
	
	public PageRequest getPageRequest(PageDTO pageDTO) {
		return PageRequest.of(pageDTO.getPageNumber(), pageDTO.getPageSize(),
                Sort.by(pageDTO.getDirection(),pageDTO.getSortVal()));
	}

}
