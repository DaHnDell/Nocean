package kcanmin.com.nocean.service;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GptServiceImpl implements GptService {

  @Value("${openai.api-key}")
  private String apiKey;

  @Value("${openai.prompt-template}")
  private String promptTemplate;

  private final OkHttpClient client = new OkHttpClient();

  public String summarize(String message, String diff){
    return "";
  }

}
