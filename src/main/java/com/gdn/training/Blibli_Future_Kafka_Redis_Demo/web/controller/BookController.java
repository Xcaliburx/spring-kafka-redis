package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.controller;

import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.entity.Book;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.service.BookService;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.Response;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book.BookFilter;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book.BookResponse;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book.CreateBookRequest;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book.UpdateBookRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@Validated
@RestController
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @ApiOperation("Create a book")
  @PostMapping(path = "/api/books",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @CacheEvict(value = "find-all-books", allEntries = true)
  public Response<BookResponse> create(@RequestBody CreateBookRequest request) {
    Book book = bookService.create(request);
    BookResponse bookResponse = convertToResponse(book);

    return Response.<BookResponse>builder()
        .status(HttpStatus.OK.value())
        .data(bookResponse)
        .build();
  }

  @ApiOperation("Find all books")
  @GetMapping(path = "/api/books",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Cacheable(value = "find-all-books", key = "#bookFilter.page + '-' + #bookFilter.size")
  public Response<List<BookResponse>> findAll(BookFilter bookFilter) {
    Page<Book> bookPage = bookService.findAll(bookFilter);
    List<BookResponse> bookResponses = bookPage.map(this::convertToResponse).getContent();
    Response.Pagination pagination = convertToResponse(bookPage);

    return Response.<List<BookResponse>>builder()
        .status(HttpStatus.OK.value())
        .data(bookResponses)
        .pagination(pagination)
        .build();
  }

  @ApiOperation("Find book by id")
  @GetMapping(path = "/api/books/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Cacheable(value = "find-by-id", key = "#id")
  public Response<BookResponse> findById(@PathVariable String id) {
    Book book = bookService.findById(id);
    BookResponse bookResponse = convertToResponse(book);

    return Response.<BookResponse>builder()
        .status(HttpStatus.OK.value())
        .data(bookResponse)
        .build();
  }

  @ApiOperation("Update a book")
  @PutMapping(path = "/api/books/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Caching(
          evict = {@CacheEvict(value = "find-all-books", allEntries = true)},
          put = {@CachePut(value = "find-by-id", key = "#id")}
  )
  public Response<BookResponse> update(@PathVariable String id, @RequestBody UpdateBookRequest request) {
    Book book = bookService.update(id, request);
    BookResponse bookResponse = convertToResponse(book);


    return Response.<BookResponse>builder()
        .status(HttpStatus.OK.value())
        .data(bookResponse)
        .build();
  }

  @ApiOperation("Delete a book")
  @DeleteMapping(path = "/api/books/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Caching(
          evict = {
                  @CacheEvict(value = "find-all-books", allEntries = true),
                  @CacheEvict(value = "find-by-id", key = "#id")
          }
  )
  public Response<Void> delete(@PathVariable String id) {
    bookService.delete(id);

    return Response.<Void>builder()
        .status(HttpStatus.OK.value())
        .build();
  }

  private BookResponse convertToResponse(Book book) {
    BookResponse bookResponse = new BookResponse();
    BeanUtils.copyProperties(book, bookResponse);
    return bookResponse;
  }

  private Response.Pagination convertToResponse(Page<?> page) {
    return Response.Pagination.builder()
        .page(page.getNumber())
        .size((long) page.getSize())
        .totalItems(page.getTotalElements())
        .build();
  }

}
