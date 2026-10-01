package com.app.servlets;

import com.app.logica.ControladoraLogica;
import com.app.logica.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

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

                List<Usuario> listaUsuarios =
                        control.listaUsuarios();

                request.setAttribute(
                        "listaUsuarios",
                        listaUsuarios
                );

                request.getRequestDispatcher("usuarios.jsp")
                        .forward(request, response);

                break;

            case "eliminar":

                try {

                    int idEliminar =
                            Integer.parseInt(
                                    request.getParameter("id")
                            );

                    control.eliminarUsuario(idEliminar);

                    response.sendRedirect(
                            "UsuarioServlet?accion=listar"
                    );

                } catch (Exception e) {

                    e.printStackTrace();

                    response.sendError(
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            "Error al eliminar el usuario: "
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

                String nombre =
                        request.getParameter("nombre");

                String apellido =
                        request.getParameter("apellido");

                String email =
                        request.getParameter("email");

                Usuario usuario = new Usuario(
                        nombre,
                        apellido,
                        email
                );

                control.crearUsuario(usuario);

                response.sendRedirect(
                        "UsuarioServlet?accion=listar"
                );

            } catch (Exception e) {

                e.printStackTrace();

                response.sendError(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error al crear el usuario: "
                                + e.getMessage()
                );
            }
        }
    }
}