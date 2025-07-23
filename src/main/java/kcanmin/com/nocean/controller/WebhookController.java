package kcanmin.com.nocean.controller;

import kcanmin.com.nocean.entity.dto.GithubPayloadDto;
import kcanmin.com.nocean.service.GptService;
import kcanmin.com.nocean.service.NotionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
@Log4j2
public class WebhookController {

  private final GptService gptService;
  private final NotionService notionService;

  @Value("${webhook.secret}")
  private String webhookSecret;

  @PostMapping("/webhook")
  public String handlePushEvent(@RequestBody GithubPayloadDto githubPayloadDto) {
    log.info("::: REQ has been signed in :::");
    try {
      Map<String, Object> headCommit = githubPayloadDto.getHead_commit();
      if (headCommit == null) return "head_commit 없음";

      String message = (String) headCommit.get("message");
      String url = (String) headCommit.get("url");
      String id = (String) headCommit.get("id");
      String timestamp = (String) headCommit.get("timestamp");

      String diff = String.format("Commit ID: %s\nTime: %s", id, timestamp);

      String summary = gptService.summarize(message, diff);
      notionService.record(message, summary, url);

      return "Webhook 처리 완료";
    } catch (Exception e) {
      return "오류: " + e.getMessage();
    }
  }


}
