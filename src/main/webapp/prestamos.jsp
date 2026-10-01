<%@ page import="java.util.List" %>
<%@ page import="com.app.logica.Prestamo" %>
<%@ page import="com.app.logica.Libro" %>
<%@ page import="com.app.logica.Usuario" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Préstamos</title>

    <link rel="stylesheet"
          href="css/estilos.css">

</head>

<body>

    <header>

        <h1>📖 Gestión de Préstamos</h1>

        <nav>

            <a href="index.jsp">Inicio</a>

            <a href="LibroServlet?accion=listar">
                Libros
            </a>

            <a href="UsuarioServlet?accion=listar">
                Usuarios
            </a>

            <a href="PrestamoServlet?accion=listar">
                Préstamos
            </a>

        </nav>

    </header>


    <main>

        <section class="formulario">

            <h2>Registrar préstamo</h2>

            <form action="PrestamoServlet" method="POST">

                <input type="hidden"
                       name="accion"
                       value="crear">


                <label>Libro:</label>

                <select name="idLibro" required>

                    <option value="">
                        Seleccionar libro
                    </option>

                    <%
                        List<Libro> listaLibros =
                            (List<Libro>) request.getAttribute(
                                "listaLibros"
                            );

                        if (listaLibros != null) {

                            for (Libro libro : listaLibros) {
                    %>

                    <option value="<%= libro.getId() %>">

                        <%= libro.getTitulo() %>

                    </option>

                    <%
                            }
                        }
                    %>

                </select>


                <label>Usuario:</label>

                <select name="idUsuario" required>

                    <option value="">
                        Seleccionar usuario
                    </option>

                    <%
                        List<Usuario> listaUsuarios =
                            (List<Usuario>) request.getAttribute(
                                "listaUsuarios"
                            );

                        if (listaUsuarios != null) {

                            for (Usuario usuario : listaUsuarios) {
                    %>

                    <option value="<%= usuario.getId() %>">

                        <%= usuario.getNombre() %>
                        <%= usuario.getApellido() %>

                    </option>

                    <%
                            }
                        }
                    %>

                </select>


                <button type="submit">
                    Registrar préstamo
                </button>

            </form>

        </section>


        <section>

            <h2>Préstamos registrados</h2>

            <table>

                <thead>

                    <tr>

                        <th>ID</th>
                        <th>Libro</th>
                        <th>Usuario</th>
                        <th>Fecha préstamo</th>
                        <th>Fecha devolución</th>
                        <th>Acciones</th>

                    </tr>

                </thead>


                <tbody>

                    <%
                        List<Prestamo> listaPrestamos =
                            (List<Prestamo>) request.getAttribute(
                                "listaPrestamos"
                            );

                        if (listaPrestamos != null) {

                            for (Prestamo prestamo : listaPrestamos) {
                    %>

                    <tr>

                        <td>
                            <%= prestamo.getId() %>
                        </td>

                        <td>
                            <%= prestamo.getLibro().getTitulo() %>
                        </td>

                        <td>

                            <%= prestamo.getUsuario().getNombre() %>
                            <%= prestamo.getUsuario().getApellido() %>

                        </td>

                        <td>
                            <%= prestamo.getFechaPrestamo() %>
                        </td>

                        <td>
                            <%= prestamo.getFechaDevolucion() %>
                        </td>

                        <td>

                            <a class="btn-editar"
                               href="PrestamoServlet?accion=editar&id=<%= prestamo.getId() %>">

                                Editar

                            </a>

                            <a class="btn-eliminar"
                               href="PrestamoServlet?accion=eliminar&id=<%= prestamo.getId() %>"
                               onclick="return confirm('¿Eliminar este préstamo?');">

                                Eliminar

                            </a>

                        </td>

                    </tr>

                    <%
                            }

                        }
                    %>

                </tbody>

            </table>

        </section>

    </main>


    <footer>

        <p>Biblioteca Inteligente</p>

    </footer>

</body>

</html>