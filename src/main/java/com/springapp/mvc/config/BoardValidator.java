package com.springapp.mvc.config;

import com.springapp.mvc.model.BoardDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BoardDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.title", "내용을 입력 하셔야 합니다.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file_name", "error.file_name", "사진을 등록 하셔야 합니다.");
    }
}

