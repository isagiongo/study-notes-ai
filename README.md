# study-notes-ai

Study notes API generator with reactive spring boot and open AI (ChatGPT).

Ps.: You will need to add your api key generated in your open ia account in the application properties file.

To test, use this example request, modifying only the body passed as a string

``curl http://localhost:8080/study-notes -H "Content-Type: application/json" -d '{"content": "java 21", "lines": 5, "maxTokens": 500}'"``

