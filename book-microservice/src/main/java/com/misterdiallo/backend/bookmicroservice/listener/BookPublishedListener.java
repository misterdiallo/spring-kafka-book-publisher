package com.misterdiallo.backend.bookmicroservice.listener;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.misterdiallo.backend.bookmicroservice.domain.Book;
import com.misterdiallo.backend.bookmicroservice.domain.Notification;
import com.misterdiallo.backend.bookmicroservice.exception.InvalidMessageException;
import com.misterdiallo.backend.bookmicroservice.service.BooksService;
import com.misterdiallo.backend.bookmicroservice.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Profile("production")
@Slf4j
public class BookPublishedListener {

    private final ObjectMapper objectMapper;

    private final BooksService booksService;

    private final NotificationService notificationService;

    public BookPublishedListener(
            final ObjectMapper objectMapper,
            final BooksService booksService,
            final NotificationService notificationService) {
        this.objectMapper = objectMapper;
        this.booksService = booksService;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "books.published")
    public String listens(final String in) {
        log.info("Received Book: {}", in);
        try {
            final Map<String, Object> payload = readJsonAsMap(in);

            final Book book = bookFromPayload(payload);
            final Book savedBook = booksService.save(book);

            final String message = String.format(
                    "Book '%s' [%s] persisted!",
                    savedBook.getTitle(),
                    savedBook.getIsbn()
            );
            notificationService.publishNotification(
                    Notification.builder()
                            .message(message)
                            .timestamp(LocalDateTime.now())
                            .service("book-persistence")
                            .build());

        } catch(final InvalidMessageException ex) {
            log.error("Invalid message received: {}", in);
        }


        return in;
    }

    private Map<String, Object> readJsonAsMap(final String json) {
        try{
            final TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
            return objectMapper.readValue(json, typeRef);
        } catch(JsonProcessingException ex) {
            throw new InvalidMessageException();
        }
    }

    /**
     * Note - There are MUCH MUCH MUCH better ways of doing this.
     * 	      Implemented in this way for brevity.
     */
    private Book bookFromPayload(final Map<String, Object> payload) {
        final Integer authorId = (Integer)((HashMap<String, Object>)payload.get("author")).get("id"); /* <- Don't do this in prod!!! :| */
        return Book.builder()
                .isbn(payload.get("isbn").toString())
                .title(payload.get("title").toString())
                .author(authorId.longValue())
                .build();
    }
}