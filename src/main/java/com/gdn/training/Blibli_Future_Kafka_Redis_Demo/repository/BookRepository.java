package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.repository;

import com.gdn.training.Blibli_Future_Kafka_Redis_Demo.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
