package com.gdn.training.Blibli_Future_Kafka_Redis_Demo.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {

  private static final long serialVersionUID = 1618002060247672538L;
  private Integer status;

  private T data;

  private Map<String, List<String>> errors;

  private Pagination pagination;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Pagination implements Serializable {
    private static final long serialVersionUID = -675522804588536512L;

    private Integer page;

    private Long size;

    private Long totalItems;

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Pagination{");
      sb.append("page=").append(page);
      sb.append(", size=").append(size);
      sb.append(", totalItems=").append(totalItems);
      sb.append('}');
      return sb.toString();
    }
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Response{");
    sb.append("status=").append(status);
    sb.append(", data=").append(data);
    sb.append(", errors=").append(errors);
    sb.append(", pagination=").append(pagination);
    sb.append('}');
    return sb.toString();
  }
}
