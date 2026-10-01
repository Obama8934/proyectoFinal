package com.app.servlets;

import com.app.logica.ControladoraLogica;
import com.app.logica.Libro;
import com.app.logica.Prestamo;
import com.app.logica.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/PrestamoServlet")
public class PrestamoServlet extends HttpServlet {

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

                List<Prestamo> listaPrestamos =
                        control.listaPrestamo();

                List<Libro> listaLibros =
                        control.listaLibros();

                List<Usuario> listaUsuarios =
                        control.listaUsuarios();

                request.setAttribute(
                        "listaPrestamos",
                        listaPrestamos
                );

                request.setAttribute(
                        "listaLibros",
                        listaLibros
                );

                request.setAttribute(
                        "listaUsuarios",
                        listaUsuarios
                );

                request.getRequestDispatcher("prestamos.jsp")
                        .forward(request, response);

                break;

            case "eliminar":

                try {

                    int idEliminar =
                            Integer.parseInt(
                                    request.getParameter("id")
                            );

                    control.eliminarPrestamo(idEliminar);

                    response.sendRedirect(
                            "PrestamoServlet?accion=listar"
                    );

                } catch (Exception e) {

                    e.printStackTrace();

                    response.sendError(
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            "Error al eliminar el préstamo: "
                                    + e.getMessage()
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

                int idLibro =
                        Integer.parseInt(
                                request.getParameter("idLibro")
                        );

                int idUsuario =
                        Integer.parseInt(
                                request.getParameter("idUsuario")
                        );

                Libro libro =
                        control.buscarPorIdLibro(idLibro);

                Usuario usuario =
                        control.buscarPorIdUsuario(idUsuario);

                Date fechaPrestamo = new Date();

                Date fechaDevolucion =
                        new Date(
                                fechaPrestamo.getTime()
                                        + (7L * 24 * 60 * 60 * 1000)
                        );

                Prestamo prestamo = new Prestamo(
                        fechaPrestamo,
                        fechaDevolucion,
                        libro,
                        usuario
                );

                control.crearPrestamo(prestamo);

                response.sendRedirect(
                        "PrestamoServlet?accion=listar"
                );

            } catch (Exception e) {

                e.printStackTrace();

                response.sendError(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error al crear el préstamo: "
                                + e.getMessage()
                );
            }
        }
    }
}