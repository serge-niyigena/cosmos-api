package com.cosmos.utilities.specs;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

import com.cosmos.dtos.general.SearchCriteriaDTO;

public class SpecBuilder<T> {

    private List<SearchCriteriaDTO> searchParams;

    public SpecBuilder() {
        searchParams = new ArrayList<SearchCriteriaDTO>();
    }

    public SpecBuilder<T> with(String connector, String key, String operation, Object value) {
        searchParams.add(new SearchCriteriaDTO(key, operation, value));
        return this;
    }

    public Specification<T> build() {
        if (searchParams.size() == 0) {
            return null;
        }

        Specification<T> result = null;
        
        for (SearchCriteriaDTO criteria: searchParams) {
            if (searchParams.indexOf(criteria) == 0) {
                result = new SearchSpec<T>(criteria);
                continue;
            }
            result = Specification.where(result);
            
        }
      
        return result;
    }
}
