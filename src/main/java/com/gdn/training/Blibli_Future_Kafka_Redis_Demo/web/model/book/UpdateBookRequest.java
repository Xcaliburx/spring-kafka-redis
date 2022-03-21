package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequest {

  private String title;

  private String author;

  private Long price;

  private int quantity;
}
