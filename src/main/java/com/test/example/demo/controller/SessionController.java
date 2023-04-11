package com.test.example.demo.controller;

import com.test.example.demo.model.Response;
import com.test.example.demo.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j(topic = "SessionController")
public class SessionController {
    private final SessionService sessionService;

    public SessionController(
            final SessionService sessionService
    ) {
        this.sessionService = sessionService;
    }

    @PostMapping(
            path = "/api/randomSession",
            produces = APPLICATION_JSON_VALUE
    )
    public Response<Object> replaceTrip() {
        final var session = sessionService.constructNewSession();

        log.info("New session constructed " + session);

        return Response.builder()
                .body(session)
                .build();
    }
}
