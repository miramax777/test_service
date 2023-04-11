package com.test.example.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Response<T> {
    private HttpStatus statusCode;
    private T body;
    private ErrorCode error;
    private String errorMsg;
    private List<FieldErrorVM> fieldErrors;
}
