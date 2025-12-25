This is a "fancy" overhaul of your README. It uses clear hierarchy, visual icons (emojis), and specific callouts to make your project look professional—especially for when you link this on your resume.

---

# 🤖 Spring AI Workshop: The Next Gen Java Developer

> *"Every Java Developer is now an AI Developer."* This project is a hands-on implementation based on the **Spring AI Masterclass** by [Dan Vega](https://www.danvega.dev). It demonstrates how to integrate Large Language Models (LLMs) into the Spring ecosystem to build production-ready, intelligent applications.

---

## 🌟 Featured Highlight: Structured Output

**I am currently focusing on the Structured Output section of this workshop.** In AI development, getting a "string" back isn't enough for enterprise apps. This project demonstrates how to use Spring AI to force LLMs to return **Type-Safe Java Objects**, ensuring seamless integration with backend logic.

* **Endpoint Demo:** `GET http://localhost:8080/vacation/structured?destination=Toronto`
* **Outcome:** Converts raw LLM text into a validated POJO (Plain Old Java Object).

---

## 🛠 Tech Stack & Tools

| Technology | Description |
| --- | --- |
| **Java 25** | Leveraging the latest modern Java features. |
| **Spring Boot 3.5** | The backbone of the microservice. |
| **Spring AI** | Framework for AI engineering (Chat, Embeddings, RAG). |
| **OpenAI** | Primary LLM provider for the workshop. |
| **Docker/Ollama** | For running local open-source models. |

---

## 🚀 Getting Started

### Prerequisites

* An API Key from [OpenAI](https://platform.openai.com/)
* Java 21+ (Project uses Java 25 features)
* Maven 3.9+

### Quick Test Endpoints

Once the application is running, try these commands:

* **New Topic Generation:** `http :8080/posts/new topic=="Java 25"`
* **Structured Destination Guide:**
  `http :8080/vacation/structured destination==Toronto`
* **Multimodel Image to Text Guide:**
  `http :8080/image-to-text`
* **Multimodel Text to Speech Guide:**
    `http :8080/generate-speech`
* **Multimodel Chat Memory:**
    `http :8080/conversation/memory message=="what's my name, what do I like, tell me a fact I should know about what I like , what is my core technology"`
---
## Kafka message that consumes and inject to elasticsearch
```
docker exec -it kafka kafka-console-producer --bootstrap-server localhost:9092 --topic bank-policy-updates
```

### Message
```
{"id": "POL-101", "title": "Montreal Mortgage Rates", "content": "The new fixed rate for 5 years is 4.5% for all Quebec branches.", "category": "MORTGAGE"}
```


## 📖 Course Curriculum (Implementation Progress)

*Derived from Dan Vega's Spring AI Guide.*

### 1. Fundamentals & Chat

* [x] API Setup & Spring AI Starters
* [x] Prompt Engineering & System Messages
* [x] **Structured Output with Type Safety** 👈 *Current Focus*

### 2. Advanced Integration

* [X] Multimodal AI (Images & Audio)
* [X] Chat Memory & Conversation State
* [ ] (RAG rieval Augmented Generation)
      
<img width="1920" height="1037" alt="Screenshot 2025-12-24 at 5 24 49 PM" src="https://github.com/user-attachments/assets/1b8723a8-ff1c-43bb-8373-9bc2fcdc0f5b" />



### 3. Production & Locality

* [ ] Local Models via Ollama & Docker
* [ ] Observability (Prometheus & Grafana)
* [ ] AI Testing & Model Evaluation

---

## 📝 Project Notes

* **Package Name Fix:** The original package `com.sri.spring-ai-workshop` was refactored to `com.sri.spring_ai_workshop` for naming convention compliance.
* **POM Overrides:** Manual overrides for `<license>` and `<developers>` are implemented in the `pom.xml` to keep the build clean from parent inheritance.

---

## 🔗 Resources

* **Original Course:** [Spring AI Masterclass on YouTube](https://www.youtube.com/watch?v=FzLABAppJfM)
* **Spring AI Docs:** [Official Reference](https://docs.spring.io/spring-ai/reference/index.html)
* **Spring Boot Guides:** [Building RESTful Services](https://spring.io/guides/gs/rest-service/)
