package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.controller;

import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.entity.Book;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.repository.BookRepository;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class HelperController {

  private final BookRepository bookRepository;

  public HelperController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @ApiOperation("Populate book data")
  @PostMapping(path = "/api/helper/book/_populate",
               produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<Void> populateBookData() {
    initializeBookData();
    return Response.<Void>builder()
        .status(HttpStatus.OK.value())
        .build();
  }

  @ApiOperation("Clear book data")
  @DeleteMapping(path = "/api/helper/book/_clear",
                 produces = MediaType.APPLICATION_JSON_VALUE)
  public Response<Void> clearBookData() {
    bookRepository.deleteAll();
    return Response.<Void>builder()
        .status(HttpStatus.OK.value())
        .build();
  }

  private void initializeBookData() {
    bookRepository.save(Book.builder()
        .title("harry potter and the cursed child")
         .author("J.K. Rowling")
         .price(50000l)
         .quantity(5)
        .build());

    bookRepository.save(Book.builder()
            .title("The Subtle Art of Not Giving a F*ck")
            .author("Mark Manson")
            .price(30000l)
            .quantity(15)
            .build());

    bookRepository.save(Book.builder()
            .title("Naruto 15")
            .author("Masashi Kishimoto")
            .price(15000l)
            .quantity(100)
            .build());
  }

}
