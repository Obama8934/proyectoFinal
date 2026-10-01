package com.app.servlets;

import com.app.logica.ControladoraLogica;
import com.app.logica.Libro;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/LibroServlet")
public class LibroServlet extends HttpServlet {

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {

            case "listar":

                List<Libro> listaLibros = control.listaLibros();

                request.setAttribute("listaLibros", listaLibros);

                request.getRequestDispatcher("libros.jsp")
                        .forward(request, response);

                break;

            case "eliminar":

                try {
                    int idEliminar =
                            Integer.parseInt(request.getParameter("id"));

                    control.eliminarLibro(idEliminar);

                    response.sendRedirect("LibroServlet?accion=listar");

                } catch (Exception e) {
                    e.printStackTrace();

                    response.sendError(
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            "Error al eliminar el libro: " + e.getMessage()
                    );
                }

                break;
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("crear".equals(accion)) {

            try {
                String titulo = request.getParameter("titulo");
                String autor = request.getParameter("autor");

                int paginas =
                        Integer.parseInt(request.getParameter("cantidadPaginas"));

                double costo =
                        Double.parseDouble(request.getParameter("costo"));

                Libro libro = new Libro(
                        titulo,
                        autor,
                        paginas,
                        costo
                );

                control.crearLibro(libro);

                response.sendRedirect("LibroServlet?accion=listar");

            } catch (Exception e) {
                e.printStackTrace();

                response.sendError(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error al crear el libro: " + e.getMessage()
                );
            }
        }
    }
}