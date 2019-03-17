package it.discovery.service;

import it.discovery.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(AppConfig.class)
@ActiveProfiles({"dev", "test"})
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    void findBooks_returnAllBooks() throws Exception {
        assertEquals(0, bookService.findBooks().get().size());
    }
}
