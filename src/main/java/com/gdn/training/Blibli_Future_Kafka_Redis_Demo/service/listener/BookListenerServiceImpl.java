package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.service.listener;

import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.service.BookListenerService;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.service.BookService;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book.CreateBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class BookListenerServiceImpl implements BookListenerService {

  public static final String BOOK_TOPIC = "book_topic";
  public static final String GROUP_ID = "book_group";

  private final BookService bookService;

  public BookListenerServiceImpl(BookService bookService) {
    this.bookService = bookService;
  }

  @Override
  @KafkaListener(topics = BOOK_TOPIC, groupId = GROUP_ID,
          containerFactory = "KafkaListenerContainerFactory")
  public void onEventConsumed(CreateBookRequest createBookRequest) {
    log.info(" ####### Listener Message =============== {}", createBookRequest);
    bookService.create(createBookRequest);
  }
}
