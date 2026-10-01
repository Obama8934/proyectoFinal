<%@ page import="java.util.List" %>
<%@ page import="com.app.logica.Libro" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Libros</title>

    <link rel="stylesheet"
          href="css/estilos.css">

</head>

<body>

    <header>

        <h1>📚 Gestión de Libros</h1>

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

            <h2>Agregar libro</h2>

            <form action="LibroServlet" method="POST">

                <input type="hidden"
                       name="accion"
                       value="crear">

                <label>Título:</label>

                <input
                    type="text"
                    name="titulo"
                    required
                >


                <label>Autor:</label>

                <input
                    type="text"
                    name="autor"
                    required
                >


                <label>Cantidad de páginas:</label>

                <input
                    type="number"
                    name="cantidadPaginas"
                    min="1"
                    required
                >


                <label>Costo:</label>

                <input
                    type="number"
                    name="costo"
                    step="0.01"
                    min="0"
                    required
                >


                <button type="submit">
                    Agregar libro
                </button>

            </form>

        </section>


        <section>

            <h2>Libros registrados</h2>

            <table>

                <thead>

                    <tr>

                        <th>ID</th>
                        <th>Título</th>
                        <th>Autor</th>
                        <th>Páginas</th>
                        <th>Costo</th>
                        <th>Acciones</th>

                    </tr>

                </thead>

                <tbody>

                    <%
                        List<Libro> listaLibros =
                            (List<Libro>) request.getAttribute(
                                "listaLibros"
                            );

                        if (listaLibros != null) {

                            for (Libro libro : listaLibros) {
                    %>

                    <tr>

                        <td>
                            <%= libro.getId() %>
                        </td>

                        <td>
                            <%= libro.getTitulo() %>
                        </td>

                        <td>
                            <%= libro.getAutor() %>
                        </td>

                        <td>
                            <%= libro.getCantidadPaginas() %>
                        </td>

                        <td>
                            $<%= libro.getCosto() %>
                        </td>

                        <td>

                            <a class="btn-editar"
                               href="LibroServlet?accion=editar&id=<%= libro.getId() %>">

                                Editar

                            </a>

                            <a class="btn-eliminar"
                               href="LibroServlet?accion=eliminar&id=<%= libro.getId() %>"
                               onclick="return confirm('¿Eliminar este libro?');">

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