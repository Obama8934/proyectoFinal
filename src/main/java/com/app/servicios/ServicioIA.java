package com.app.servicios;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServicioIA {

    private final String apiKey;
    private final String modelo;
    private final ObjectMapper mapper;
    private final HttpClient cliente;

    public ServicioIA() {

        apiKey = System.getenv("OPENAI_API_KEY");

        modelo = System.getenv("OPENAI_MODEL") != null
                ? System.getenv("OPENAI_MODEL")
                : "gpt-6-luna";

        mapper = new ObjectMapper();
        cliente = HttpClient.newHttpClient();
    }

    public String preguntar(String pregunta) throws IOException, InterruptedException {

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "No se encontró la variable OPENAI_API_KEY."
            );
        }

        ObjectNode body = mapper.createObjectNode();

        body.put("model", modelo);

        body.put(
                "instructions",
                """
                Sos el asistente virtual de una biblioteca.
                Respondé de forma clara, amable y breve.
                Ayudá al usuario con preguntas relacionadas
                con libros, préstamos y usuarios de la biblioteca.
                Si la pregunta no está relacionada con la biblioteca,
                indicá amablemente que tu función principal es ayudar
                con la biblioteca.
                """
        );

        body.put("input", pregunta);

        String jsonBody = mapper.writeValueAsString(body);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/responses"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(
                        HttpRequest.BodyPublishers.ofString(jsonBody)
                )
                .build();

        HttpResponse<String> response =
                cliente.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() < 200 ||
                response.statusCode() >= 300) {

            throw new IOException(
                    "Error de OpenAI: "
                    + response.statusCode()
                    + " - "
                    + response.body()
            );
        }

        return extraerRespuesta(response.body());
    }

    private String extraerRespuesta(String json) throws IOException {

        JsonNode root = mapper.readTree(json);

        JsonNode output = root.get("output");

        if (output != null && output.isArray()) {

            for (JsonNode item : output) {

                JsonNode content = item.get("content");

                if (content != null && content.isArray()) {

                    for (JsonNode contenido : content) {

                        if ("output_text".equals(
                                contenido.path("type").asText()
                        )) {

                            return contenido
                                    .path("text")
                                    .asText();
                        }
                    }
                }
            }
        }

        return "La IA no devolvió una respuesta de texto.";
    }
}