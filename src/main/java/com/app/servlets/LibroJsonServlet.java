package com.app.servlets;

import com.app.logica.ControladoraLogica;
import com.app.logica.Libro;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/LibroJsonServlet")
public class LibroJsonServlet extends HttpServlet {

    private ControladoraLogica control;
    private ObjectMapper mapper;

    @Override
    public void init() {

        control = new ControladoraLogica();
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        List<Libro> libros = control.listaLibros();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        mapper.writeValue(
                response.getWriter(),
                libros
        );
    }
}