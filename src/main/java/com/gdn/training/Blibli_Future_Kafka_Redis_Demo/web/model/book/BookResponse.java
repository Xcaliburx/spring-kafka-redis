package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse implements Serializable {

  private static final long serialVersionUID = 6659584132588460514L;
  private String id;

  private String title;

  private String author;

  private Long price;

  private int quantity;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookResponse{");
    sb.append("id='").append(id).append('\'');
    sb.append(", title='").append(title).append('\'');
    sb.append(", author='").append(author).append('\'');
    sb.append(", price=").append(price);
    sb.append(", quantity=").append(quantity);
    sb.append('}');
    return sb.toString();
  }
}
