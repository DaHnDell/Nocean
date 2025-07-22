package kcanmin.com.nocean.controller;

import kcanmin.com.nocean.entity.dto.LogDto;
import kcanmin.com.nocean.service.GptService;
import kcanmin.com.nocean.service.NotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogController {

  private final NotionService notionService;
  private final GptService gptService;

  public ResponseEntity<?> handleLog(@RequestBody LogDto log){
    String summary = gptService.summarize(log.getMessage(), log.getDiff());
    notionService.record(log.getMessage(), summary, log.getUrl());

    return ResponseEntity.ok("ok");
  }

}
