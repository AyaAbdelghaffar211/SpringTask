package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingTest {

    @Test
    void testRatingConstructorAndGettersSetters() {
        Rating rating = new Rating(1L, 5, 101L);

        assertEquals(1L, rating.getId());
        assertEquals(5, rating.getNumber());
        assertEquals(101L, rating.getCourse_id());

        rating.setId(2L);
        rating.setNumber(4);
        rating.setCourse_id(102L);

        assertEquals(2L, rating.getId());
        assertEquals(4, rating.getNumber());
        assertEquals(102L, rating.getCourse_id());
    }

}