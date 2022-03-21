package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.service.publisher;

import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.service.BookPublisherService;
import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book.CreateBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookPublisherServiceImpl implements BookPublisherService {

  public static final String BOOK_TOPIC = "book_topic";

  private final KafkaTemplate<String, CreateBookRequest> kafkaTemplate;

  public BookPublisherServiceImpl(KafkaTemplate<String, CreateBookRequest> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Override
  public void sendMessage(CreateBookRequest createBookRequest) {
    log.info(" ####### Publish Message =============== {}", createBookRequest);
    this.kafkaTemplate.send(BOOK_TOPIC, createBookRequest);
  }
}
