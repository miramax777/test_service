package com.test.example.demo.controller;

import com.test.example.demo.model.Response;
import com.test.example.demo.service.ColorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j(topic = "SessionController")
public class ColorController {

    private ColorService colorService;

    public ColorController(final ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping(
            path = "/api/generate/color",
            produces = APPLICATION_JSON_VALUE
    )
    public Response<Object> generateColor() {
        colorService.generateColor();

        return Response.builder()
                .statusCode(HttpStatus.OK)
                .build();
    }

}
