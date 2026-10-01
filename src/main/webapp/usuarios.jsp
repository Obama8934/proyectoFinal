<%@ page import="java.util.List" %>
<%@ page import="com.app.logica.Usuario" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Usuarios</title>

    <link rel="stylesheet"
          href="css/estilos.css">

</head>

<body>

    <header>

        <h1>👤 Gestión de Usuarios</h1>

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

            <h2>Agregar usuario</h2>

            <form action="UsuarioServlet" method="POST">

                <input type="hidden"
                       name="accion"
                       value="crear">


                <label>Nombre:</label>

                <input
                    type="text"
                    name="nombre"
                    required
                >


                <label>Apellido:</label>

                <input
                    type="text"
                    name="apellido"
                    required
                >


                <label>Email:</label>

                <input
                    type="email"
                    name="email"
                    required
                >


                <button type="submit">
                    Agregar usuario
                </button>

            </form>

        </section>


        <section>

            <h2>Usuarios registrados</h2>

            <table>

                <thead>

                    <tr>

                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Email</th>
                        <th>Acciones</th>

                    </tr>

                </thead>


                <tbody>

                    <%
                        List<Usuario> listaUsuarios =
                            (List<Usuario>) request.getAttribute(
                                "listaUsuarios"
                            );

                        if (listaUsuarios != null) {

                            for (Usuario usuario : listaUsuarios) {
                    %>

                    <tr>

                        <td>
                            <%= usuario.getId() %>
                        </td>

                        <td>
                            <%= usuario.getNombre() %>
                        </td>

                        <td>
                            <%= usuario.getApellido() %>
                        </td>

                        <td>
                            <%= usuario.getEmail() %>
                        </td>

                        <td>

                            <a class="btn-editar"
                               href="UsuarioServlet?accion=editar&id=<%= usuario.getId() %>">

                                Editar

                            </a>

                            <a class="btn-eliminar"
                               href="UsuarioServlet?accion=eliminar&id=<%= usuario.getId() %>"
                               onclick="return confirm('¿Eliminar este usuario?');">

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