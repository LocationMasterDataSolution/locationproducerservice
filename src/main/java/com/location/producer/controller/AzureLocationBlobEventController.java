package com.location.producer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AzureLocationBlobEventController {

    private static final Logger logger = LoggerFactory.getLogger(AzureLocationBlobEventController.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String TOPIC_NAME;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/blob-event")
    public ResponseEntity<String> handleBlobEvent(@RequestBody String blobEventData) {
        try {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
            System.out.println("Inside blob-event");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");

            // Convert blobEventData to a formatted JSON string
            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(blobEventData);

            // Log the JSON string using SLF4J
            logger.info(jsonString);

            //kafkaTemplate.send(TOPIC_NAME, blobEventData);

            return ResponseEntity.ok("Blob event processed successfully.");
        } catch (Exception e) {
            logger.error("Failed to process blob event", e);
            return ResponseEntity.badRequest().body("Failed to process blob event: " + e.getMessage());
        }
    }
}
