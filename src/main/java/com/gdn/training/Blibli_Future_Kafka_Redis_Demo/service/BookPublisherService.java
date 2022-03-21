package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.service;

import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book.CreateBookRequest;

public interface BookPublisherService {
  void sendMessage(CreateBookRequest createBookRequest);
}
