package kcanmin.com.nocean.service;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GptServiceImpl implements GptService {

  @Value("${openai.api-key}")
  private String apiKey;

  @Value("${openai.prompt-template}")
  private String promptTemplate;

  private final OkHttpClient client = new OkHttpClient();

  @Override
  public String summarize(String message, String diff) {
    String prompt = promptTemplate
      .replace("{{message}}", message)
      .replace("{{diff}}", diff);

    MediaType mediaType = MediaType.parse("application/json");
    JSONObject messageObj = new JSONObject();
    messageObj.put("role", "user");
    messageObj.put("content", prompt);

    JSONObject bodyObj = new JSONObject();
    bodyObj.put("model", "gpt-3.5-turbo");
    bodyObj.put("max_tokens", 200);
    bodyObj.put("temperature", 0.6);
    bodyObj.put("messages", new JSONArray().put(messageObj));

    Request request = new Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .addHeader("Authorization", "Bearer " + apiKey)
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create(bodyObj.toString(), MediaType.get("application/json")))
            .build();
    try (Response response = client.newCall(request).execute()) {
      if (response.body() == null) return "응답 없음";

      String responseBody = response.body().string();
      System.out.println("GPT 응답 원문:\n" + responseBody);

      JSONObject obj = new JSONObject(responseBody);

      if (obj.has("error")) {
        return "OpenAI 오류: " + obj.getJSONObject("error").getString("message");
      }

      // 정상 응답 파싱
      String content = obj
              .getJSONArray("choices")
              .getJSONObject(0)
              .getJSONObject("message")
              .getString("content");

      return content.trim();

    } catch (IOException e) {
      return "오류 발생: " + e.getMessage();
    }
  }
}
