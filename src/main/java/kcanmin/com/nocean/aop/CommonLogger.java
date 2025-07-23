package kcanmin.com.nocean.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Log4j2
@Component
@Aspect
public class CommonLogger {
  // Controller 진입점
  @Pointcut("execution(* kcanmin.com.nocean.controller..*(..))")
  public void controllerMethods() {}

  @Pointcut("execution(* kcanmin.com.nocean.service..*(..))")
  public void serviceMethods() {}

  // 공통 진입점 로깅
  @Before("controllerMethods() || serviceMethods()")
  public void logBefore(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().toShortString();
    Object[] args = joinPoint.getArgs();
    log.info("→ 진입점: {} | 파라미터: {}", methodName, Arrays.toString(args));
  }

  // 공통 반환점 로깅 (길이 길면 요약 출력)
  @AfterReturning(pointcut = "controllerMethods() || serviceMethods()", returning = "result")
  public void logAfterReturning(JoinPoint joinPoint, Object result) {
    String methodName = joinPoint.getSignature().toShortString();

    if (result == null) {
      log.info("← 반환점: {} | 타입: null | 리턴: null", methodName);
      return;
    }

    String resultType = result.getClass().getSimpleName();
    String resultStr = result.toString();

    if (resultStr.length() > 70) {
      log.info("← 반환점: {} | 타입: {} | 리턴: (생략 - 길이 {}자)", methodName, resultType, resultStr.length());
    } else {
      log.info("← 반환점: {} | 타입: {} | 리턴: {}", methodName, resultType, resultStr);
    }
  }
}
