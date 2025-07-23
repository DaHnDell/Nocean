
https://kcanmin-bucket.s3.ap-northeast-2.amazonaws.com/nocean/Nocean.png

---

## 🧠 Notion Logger

GitHub 커밋 메시지를 GPT로 요약하여 Notion에 자동 기록하는 Webhook 서비스입니다.

---

### 🚀 기능 소개

* GitHub Webhook 이벤트 수신
* 커밋 메시지 + 메타데이터 GPT 요약
* Notion Database에 자동 기록
* Docker로 간편하게 실행

---

### 🛠️ 사용법

#### 1. Docker 실행

```bash
docker run -d -p 8080:8080 \
  -e OPENAI_API_KEY=sk-... \
  -e NOTION_TOKEN=secret_... \
  -e NOTION_DATABASE_ID=... \
  -e WEBHOOK_SECRET=your-secret \
  --name notion-logger dahndell/notion-logger
```

#### 2. GitHub Webhook 설정

* URL: `https://your-domain.com/api/github/webhook`
* Content type: `application/json`
* Secret: `your-secret`

> Webhook 이벤트는 `push`만 설정하면 충분합니다.

---

### 🔐 환경 변수

https://kcanmin-bucket.s3.ap-northeast-2.amazonaws.com/nocean/Settings.png

| 환경 변수                | 설명                           |
| -------------------- | ---------------------------- |
| `OPENAI_API_KEY`     | OpenAI GPT API 키             |
| `NOTION_TOKEN`       | Notion 통합 토큰                 |
| `NOTION_DATABASE_ID` | 기록 대상 Notion DB ID           |
| `WEBHOOK_SECRET`     | GitHub Webhook에서 사용하는 보안 시크릿 |

---

### 예시 결과 (Notion에 기록되는 내용)

```
Commit Message: "Refactor user login logic and add error handling"
Summary: 사용자 로그인 로직 리팩토링 및 예외처리 추가에 대한 커밋입니다.

🔗 URL: https://github.com/DaHnDell/Nocean/commit/...
🕓 Commit ID: abc123456
```

---

### 📦 개발/배포 환경

* Java 17 (Spring Boot)
* Docker
* GitHub Actions (자동 빌드 및 배포)
* Notion API + OpenAI API 연동

---

### 👨‍💻 제작자

* [DaHnDell](https://github.com/DaHnDell)
* 프로젝트 리포지토리: [Nocean](https://github.com/DaHnDell/Nocean)

---
