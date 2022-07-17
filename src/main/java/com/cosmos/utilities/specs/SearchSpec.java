package com.cosmos.utilities.specs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.cosmos.dtos.general.SearchCriteriaDTO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class SearchSpec<T> implements Specification<T> {

    private static final long serialVersionUID = 1L;

    private SearchCriteriaDTO searchCriteria;

    public SearchSpec(SearchCriteriaDTO searchCriteriaDTO) {
        setSearchCriteria(searchCriteriaDTO);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = null;
        String operation = searchCriteria.getOperation();
        Object value = searchCriteria.getValue();
        Join<?, ?> fieldJoin = null;

        String[] keys = searchCriteria.getKey().split("\\.");
        String key = keys[0];
        Class<? extends Object> keyType = root.get(key).getJavaType();
        if (keys.length > 1) {
            Path<Object> keyVal;
            fieldJoin = root.join(keys[0], JoinType.LEFT);
            for (int i = 1; i < keys.length - 1; i++) {
                fieldJoin = fieldJoin.join(keys[i], JoinType.LEFT);
                keyVal = fieldJoin.get(keys[i + 1]);
                keyType = keyVal.getJavaType();
            }
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        try {
            value = keyType == Date.class ? formatter.parse((String) value) : value;
        } catch (ParseException e) {
            log.error("\n[LOCATION] - specifications.SearchSpec.toPredicate() \n[MSG] - {}", e.getLocalizedMessage());
            return null;
        }
    
        if (operation.equalsIgnoreCase("GT")) {
            predicate = keyType == Date.class ?
                criteriaBuilder.greaterThanOrEqualTo(root.<Date>get(key), (Date)value) :
                criteriaBuilder.greaterThanOrEqualTo(root.<String> get(key), value.toString());
        } else if (operation.equalsIgnoreCase("LT")) {
            predicate = keyType == Date.class ?
                criteriaBuilder.lessThanOrEqualTo(root.<Date>get(key), (Date)value) :
                criteriaBuilder.lessThanOrEqualTo(root.<String> get(key), value.toString());
        } else if (fieldJoin != null && operation.equalsIgnoreCase("EQ")) {
            String maxKeysValue = keys[keys.length -1];
            predicate = keyType == String.class ?
                criteriaBuilder.like(criteriaBuilder.lower(fieldJoin.<String>get(maxKeysValue)), "%" + ((String) value).toLowerCase() + "%") : 
                criteriaBuilder.equal(fieldJoin.<String>get(maxKeysValue), value);
        } else if (operation.equalsIgnoreCase("EQ")) {
            predicate = keyType == String.class ?
                criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(key)), "%" + ((String) value).toLowerCase() + "%") : 
                criteriaBuilder.equal(root.get(key), value);
        }
        return predicate;
    }

}
