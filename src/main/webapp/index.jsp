<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Biblioteca Inteligente</title>

    <link rel="stylesheet"
          href="css/estilos.css">

</head>

<body>

    <header>

        <h1>📚 Biblioteca Inteligente</h1>

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

        <section class="bienvenida">

            <h2>Bienvenido a la Biblioteca</h2>

            <p>
                Desde este sistema podés administrar
                libros, usuarios y préstamos.
            </p>

        </section>


        <section class="agente">

            <h2>🤖 Asistente de Biblioteca</h2>

            <p>
                Preguntale algo sobre la biblioteca.
            </p>

            <div id="chat" class="chat">

                <div class="mensaje">

                    <strong>Biblioteca IA:</strong>

                    ¡Hola! ¿En qué puedo ayudarte?

                </div>

            </div>


            <div class="entrada-agente">

                <input
                    type="text"
                    id="mensajeAgente"
                    placeholder="Escribí tu pregunta..."
                >

                <button onclick="preguntarAgente()">
                    Enviar
                </button>

            </div>

        </section>

    </main>


    <footer>

        <p>
            Biblioteca Inteligente - Proyecto Java Web
        </p>

    </footer>


    <script src="js/script.js"></script>

</body>

</html>