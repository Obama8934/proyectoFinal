async function preguntarAgente() {

    const input = document.getElementById("mensajeAgente");
    const chat = document.getElementById("chat");

    const mensaje = input.value.trim();

    if (mensaje === "") {
        return;
    }

    agregarMensaje("Vos", mensaje);

    input.value = "";

    agregarMensaje("Biblioteca IA", "Pensando...");

    try {

        const response = await fetch("AgenteServlet", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                mensaje: mensaje
            })
        });

        const data = await response.json();

        if (data.respuesta) {

            reemplazarUltimoMensaje(
                data.respuesta
            );

        } else {

            reemplazarUltimoMensaje(
                "No pude obtener una respuesta."
            );
        }

    } catch (error) {

        console.error(error);

        reemplazarUltimoMensaje(
            "Ocurrió un error al comunicarse con el agente."
        );
    }
}


function agregarMensaje(remitente, mensaje) {

    const chat = document.getElementById("chat");

    const div = document.createElement("div");

    div.classList.add("mensaje");

    div.innerHTML =
        "<strong>" +
        remitente +
        ":</strong> " +
        mensaje;

    chat.appendChild(div);

    chat.scrollTop = chat.scrollHeight;
}


function reemplazarUltimoMensaje(mensaje) {

    const chat = document.getElementById("chat");

    const mensajes =
        chat.getElementsByClassName("mensaje");

    if (mensajes.length === 0) {
        return;
    }

    const ultimo =
        mensajes[mensajes.length - 1];

    ultimo.innerHTML =
        "<strong>Biblioteca IA:</strong> "
        + mensaje;
}