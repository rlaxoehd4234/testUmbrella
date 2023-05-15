package com.umbrella.domain.exception;

import com.umbrella.exception.BaseException;
import com.umbrella.exception.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PostExceptionType implements BaseExceptionType {

    NOT_FOUND_POST(404,HttpStatus.OK,"존재하지 않는 게시물 입니다."),
    ALREADY_PUSH_ERROR(444,HttpStatus.OK,"이미 좋아요를 눌렀습니다."),
    NON_PUSH_ERROR(444, HttpStatus.OK, "좋아요를 누르지 않았습니다.");
    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

}
