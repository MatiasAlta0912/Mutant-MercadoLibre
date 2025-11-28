package com.example.mutant.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MutantControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testMutantEndpointReturns200() {
        String body = """
        {
          "dna": [
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
          ]
        }
        """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        // Usamos String.class para no deserializar a MutantResponse
        ResponseEntity<String> response =
                restTemplate.postForEntity("/mutant", request, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testHumanEndpointReturns403() {
        String body = """
        {
          "dna": [
            "ATGCGA",
            "CAGTGC",
            "TTATTT",
            "AGACGG",
            "GCGTCA",
            "TCACTG"
          ]
        }
        """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity("/mutant", request, String.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
