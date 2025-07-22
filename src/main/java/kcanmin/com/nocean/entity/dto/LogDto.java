package kcanmin.com.nocean.entity.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogDto {

  private String message;
  private String diff;
  private String url;

}
