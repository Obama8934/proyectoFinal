package com.app.servlets;

import com.app.servicios.ServicioIA;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AgenteServlet")
public class AgenteServlet extends HttpServlet {

    private ServicioIA servicioIA;
    private ObjectMapper mapper;

    @Override
    public void init() {

        servicioIA = new ServicioIA();
        mapper = new ObjectMapper();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            JsonNode body =
                    mapper.readTree(request.getReader());

            String pregunta =
                    body.path("mensaje").asText();

            if (pregunta == null || pregunta.isBlank()) {

                enviarError(
                        response,
                        "No se recibió ningún mensaje."
                );

                return;
            }

            String respuestaIA =
                    servicioIA.preguntar(pregunta);

            ObjectNode respuesta =
                    mapper.createObjectNode();

            respuesta.put(
                    "respuesta",
                    respuestaIA
            );

            response.getWriter().write(
                    mapper.writeValueAsString(respuesta)
            );

        } catch (Exception e) {

            enviarError(
                    response,
                    "No se pudo obtener una respuesta del agente."
            );
        }
    }

    private void enviarError(
            HttpServletResponse response,
            String mensaje)
            throws IOException {

        response.setStatus(
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR
        );

        ObjectNode error =
                mapper.createObjectNode();

        error.put("error", mensaje);

        response.getWriter().write(
                mapper.writeValueAsString(error)
        );
    }
}