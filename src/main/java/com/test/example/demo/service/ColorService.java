package com.test.example.demo.service;

import com.test.example.demo.model.Color;
import com.test.example.demo.repository.ColorRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ColorService {

    private final ColorRepository colorRepository;

    public ColorService(final ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Async
    public void generateColor() {
        final var id = new Random().nextLong();

        colorRepository.save(
                new Color(
                        id,
                        new String("Test color" + id),
                        new String("#35235" + id)
                )
        );
    }

}
