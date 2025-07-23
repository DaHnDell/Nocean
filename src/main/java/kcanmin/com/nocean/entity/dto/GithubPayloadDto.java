package kcanmin.com.nocean.entity.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GithubPayloadDto {
  private String ref;
  private Map<String, Object> repository;
  private Map<String, Object> head_commit;
}
