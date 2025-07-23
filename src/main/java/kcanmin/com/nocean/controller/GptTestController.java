package kcanmin.com.nocean.controller;

import kcanmin.com.nocean.service.GptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/gpt")
@RequiredArgsConstructor
public class GptTestController {

  private final GptService gptService;

  @PostMapping("/summarize")
  public String summarize(@RequestBody Map<String, String> payload) {
    String message = payload.getOrDefault("message", "");
    String diff = payload.getOrDefault("diff", "");
    return gptService.summarize(message, diff);
  }



}
