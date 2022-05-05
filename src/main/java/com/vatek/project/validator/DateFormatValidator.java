package com.catdev.project.validator;

import com.catdev.project.constant.DateConstant;
import com.catdev.project.validator.anotation.DateFormatConstraint;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author itsol.hungtt on 12/25/2020
 * Copyright @DONG.NV
 */
public class DateFormatValidator implements ConstraintValidator<DateFormatConstraint, String> {

    private String datePattern;
    private boolean required;

    @Override
    public void initialize(DateFormatConstraint dateFormatConstraint) {
        this.datePattern = dateFormatConstraint.datePattern();
        this.required = dateFormatConstraint.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext ct) {

        boolean isDateBlank = StringUtils.isBlank(s);

        if (required) {
            if (isDateBlank) {
                return false;
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

            try {
                simpleDateFormat.parse(s);
            } catch (ParseException e) {
                return false;
            }

        } else {

            if (isDateBlank) {
                return true;
            }

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
            try {
                simpleDateFormat.parse(s);
            } catch (ParseException e) {
                return false;
            }

        }

        return true;
    }

}
