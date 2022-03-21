package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookFilter {

  private int page;

  private int size;

}
