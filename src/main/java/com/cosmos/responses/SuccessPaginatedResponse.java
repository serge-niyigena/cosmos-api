package com.cosmos.responses;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;

import com.cosmos.dtos.general.PageInfoDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = { "contentMap" })
public class SuccessPaginatedResponse {

    private int status = 200;

    private String message;

    private Object content;

    Map<String, Object> contentMap = new HashMap<String, Object>();

    public SuccessPaginatedResponse(int status, String message, Page<?> page) {
        setBasicProperties(status, message, page);
        contentMap.put("data", page.getContent());
        this.setContent(contentMap);
    }

    public SuccessPaginatedResponse(int status, String message, List<?> dataArray, Class<?> customClass,
            Class<?> entityClass)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {

        this.setStatus(status);
        this.setMessage(message);

        List<Object> contentList = new ArrayList<Object>();
        for (Object objectItem : dataArray) {
            Object customObj = customClass.getConstructor(entityClass).newInstance(objectItem);
            contentList.add(customObj);
        }
        contentMap.put("data", contentList);
        this.setContent(contentMap);
    }

    /**
     * Handles paginated responses that require transformation
     * 
     * @param status
     * @param message
     * @param page
     * @param customClass
     * @param entityClass
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public SuccessPaginatedResponse(int status, String message, Page<?> page, Class<?> customClass,
            Class<?> entityClass)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {

        setBasicProperties(status, message, page);

        List<Object> contentList = new ArrayList<Object>();
        for (Object obj : page.getContent()) {
            Object customObj = customClass.getConstructor(entityClass).newInstance(obj);
            contentList.add(customObj);
        }
        contentMap.put("data", contentList);
        this.setContent(contentMap);
    }

    /**
     * Abstracts setting of the basic SuccessPaginatedResponse props
     * 
     * @param status
     * @param message
     * @param page
     */
    public void setBasicProperties(int status, String message, Page<?> page) {
        contentMap.put("pageInfo", new PageInfoDTO(page));
        this.setStatus(status);
        this.setMessage(message);
    }
}
