package com.springapp.mvc.board.config;

import com.springapp.mvc.board.model.BoardDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BoardValidator implements Validator {

    //공통 변수 : 문자열 길이 15
    private static final int MAX_TITLE_LENGTH = 16;
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

