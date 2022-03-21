package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.service;

import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.entity.Book;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book.BookFilter;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book.CreateBookRequest;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book.UpdateBookRequest;
import org.springframework.data.domain.Page;

public interface BookService {

  Book create(CreateBookRequest request);

  Page<Book> findAll(BookFilter filter);

  Book findById(String id);

  Book update(String id, UpdateBookRequest request);

  void delete(String id);

}
