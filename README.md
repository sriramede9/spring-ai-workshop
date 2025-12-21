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

---

## 📖 Course Curriculum (Implementation Progress)

*Derived from Dan Vega's Spring AI Guide.*

### 1. Fundamentals & Chat

* [x] API Setup & Spring AI Starters
* [x] Prompt Engineering & System Messages
* [x] **Structured Output with Type Safety** 👈 *Current Focus*

### 2. Advanced Integration

* [ ] Multimodal AI (Images & Audio)
* [ ] Chat Memory & Conversation State
* [ ] RAG (Retrieval Augmented Generation)

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
