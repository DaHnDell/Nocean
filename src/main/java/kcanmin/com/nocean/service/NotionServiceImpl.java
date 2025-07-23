package kcanmin.com.nocean.service;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NotionServiceImpl implements NotionService {

  @Value("${notion.token}")
  private String notionToken;

  @Value("${notion.database-id}")
  private String databaseId;

  private final OkHttpClient client = new OkHttpClient();

  @Override
  public void record(String message, String summary, String url) {
    JSONObject properties = new JSONObject();

    properties.put("제목", new JSONObject()
            .put("title", new JSONArray().put(new JSONObject()
                    .put("text", new JSONObject().put("content", message)))));

    properties.put("요약", new JSONObject()
            .put("rich_text", new JSONArray().put(new JSONObject()
                    .put("text", new JSONObject().put("content", summary)))));

    properties.put("URL", new JSONObject()
            .put("url", url)); // URL 필드는 타입에 따라 다를 수 있음

    JSONObject body = new JSONObject();
    body.put("parent", new JSONObject().put("database_id", databaseId));
    body.put("properties", properties);

    Request request = new Request.Builder()
            .url("https://api.notion.com/v1/pages")
            .addHeader("Authorization", "Bearer " + notionToken)
            .addHeader("Content-Type", "application/json")
            .addHeader("Notion-Version", "2022-06-28")
            .post(RequestBody.create(body.toString(), MediaType.get("application/json")))
            .build();

    try (Response response = client.newCall(request).execute()) {
      if (response.body() != null) {
        System.out.println("Notion 응답: " + response.body().string());
      } else {
        System.out.println("Notion 응답 없음");
      }
    } catch (IOException e) {
      System.out.println("Notion 요청 오류: " + e.getMessage());
    }
  }
}
