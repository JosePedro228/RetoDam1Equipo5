const tituloZona = document.getElementById("titulo-zona");
const cuerpoTabla = document.getElementById("datos-inventario");

// ahora el mapa se carga desde MySQL
let mapaUbicaciones = {};

/**
 * 1. Cargar ubicaciones desde la BD
 */
function cargarUbicaciones() {
    fetch("http://98.90.166.154:443/api/ubicaciones")
        .then(res => res.json())
        .then(data => {

            data.forEach(u => {
                mapaUbicaciones[u.nombre] = u.id_ubicacion;
            });

            console.log("Mapa cargado:", mapaUbicaciones);
        })
        .catch(err => console.error("Error cargando ubicaciones:", err));
}

/**
 * 2. Limpiar resaltado
 */
function clearHighlight() {
    document
        .querySelectorAll(".resaltado")
        .forEach(el => el.classList.remove("resaltado"));
}

/**
 * 3. Resaltar celda + cargar inventario
 */
function highlightCell(id) {
    clearHighlight();

    const celda = document.getElementById(id);

    if (celda) {
        celda.classList.add("resaltado");
        actualizarTabla(id);
    }
}

/**
 * 4. Cargar inventario según ubicación
 */
function actualizarTabla(nombreZona) {

    const idUbicacion = mapaUbicaciones[nombreZona];

    tituloZona.textContent = "Inventario: " + nombreZona;

    if (!idUbicacion) {
        cuerpoTabla.innerHTML = `
            <tr>
                <td colspan="3">Ubicación no encontrada en BD</td>
            </tr>
        `;
        return;
    }

    fetch(`http://98.90.166.154:443/api/inventario/${idUbicacion}`)
        .then(res => res.json())
        .then(data => {

            cuerpoTabla.innerHTML = "";

            if (data.length === 0) {
                cuerpoTabla.innerHTML = `
                    <tr>
                        <td colspan="3">No hay componentes en esta ubicación</td>
                    </tr>
                `;
                return;
            }

            data.forEach(item => {

                let claseEstado = "ok";

                if (item.estado === "prestado") claseEstado = "warn";
                if (item.estado === "en_reparacion") claseEstado = "danger";

                cuerpoTabla.innerHTML += `
                    <tr>
                        <td>${item.nombre}</td>
                        <td>${item.descripcion || "-"}</td>
                        <td>
                            <span class="badge ${claseEstado}">
                                ${item.estado}
                            </span>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(err => {
            console.error(err);
            cuerpoTabla.innerHTML = `
                <tr>
                    <td colspan="3">Error conectando con el servidor</td>
                </tr>
            `;
        });
}

/**
 * 5. Eventos en el mapa
 */
document.querySelectorAll(".celda").forEach(celda => {
    celda.addEventListener("click", () => {
        highlightCell(celda.id);
    });
});

/**
 * 6. Inicializar
 */
cargarUbicaciones();