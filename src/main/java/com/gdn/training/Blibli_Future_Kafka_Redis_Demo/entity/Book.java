package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {

  @Id
  private String id;

  private String title;

  private String author;

  private Long price;

  private int quantity;
}
