package com.example.mutant.controller;

import com.example.mutant.dto.StatsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StatsControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testStatsEndpointExists() {
        ResponseEntity<StatsResponse> response =
                restTemplate.getForEntity("/stats", StatsResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
