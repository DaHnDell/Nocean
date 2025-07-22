package kcanmin.com.nocean.service;

import org.springframework.stereotype.Service;

public interface GptService {

  String summarize(String message, String diff);

}
