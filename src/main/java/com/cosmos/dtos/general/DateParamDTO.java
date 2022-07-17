package com.cosmos.dtos.general;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cosmos.exceptions.InvalidInputException;

import lombok.Data;

@Data
public class DateParamDTO {

    Logger logger = LoggerFactory.getLogger(DateParamDTO.class);

    private Date endDate;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm");

    private Date startDate;

    // Scenarios:
    // - start + end
    // - start
    // - end
    // - none

    @SuppressWarnings("deprecation")
    public DateParamDTO(Map<String, Object> dateParams)
            throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

        formatter.setLenient(false);

        String endDateStr = (String) dateParams.getOrDefault("endDate", null);
        String startDateStr = (String) dateParams.getOrDefault("startDate", null);

        if (endDateStr != null && startDateStr != null) {
            setDateField(startDateStr, this.getClass().getDeclaredField("startDate"));
            setDateField(endDateStr, this.getClass().getDeclaredField("endDate"));
            if (startDate.after(endDate)) {
                throw new InvalidInputException("invalid date range provided. startDate should be less than end date",
                        "startDate/endDate");
            }
        } else {
            setEndDate(new Date());
            setStartDate(new Date());
            startDate.setHours(0);
            startDate.setMinutes(0);
            startDate.setSeconds(0);
        }
    }

    /**
     * Abstracts seting all date fields within class
     * 
     * @param startDateStr
     * @param field
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public void setDateField(String startDateStr, Field field) throws IllegalArgumentException, IllegalAccessException {

        try {
            Object fieldValue = startDateStr == null ? new Date() : stringToDate(startDateStr);
            field.set(this, fieldValue);
        } catch (ParseException e) {
            field.set(this, new Date());
            logger.error("\n[MSG] - {}\n[CAUSE] - {}", e.getMessage(), e.getCause());
        }
    }

    /**
     * Converts date string to date obj
     * 
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public Date stringToDate(String dateStr) throws ParseException {
        return formatter.parse(dateStr);
    }
}
