package kcanmin.com.nocean.controller;

import kcanmin.com.nocean.service.GptService;
import kcanmin.com.nocean.service.NotionService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/notion")
public class NotionTestController {

  private final GptService gptService;
  private final NotionService notionService;

  public NotionTestController(GptService gptService, NotionService notionService) {
    this.gptService = gptService;
    this.notionService = notionService;
  }

  @PostMapping("/record")
  public String recordSummary(@RequestBody Map<String, String> payload) {
    String message = payload.getOrDefault("message", "제목 없음");
    String diff = payload.getOrDefault("diff", "");
    String url = payload.getOrDefault("url", "");

    // GPT 요약 생성
    String summary = gptService.summarize(message, diff);

    // Notion 기록
    notionService.record(message, summary, url);

    return "기록 완료";
  }
}
