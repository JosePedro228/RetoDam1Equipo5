const tooltip = document.getElementById('tooltip-activo');
const tituloZona = document.getElementById("titulo-zona");
const cuerpoTabla = document.getElementById("datos-inventario");
const tooltip = document.getElementById("tooltip-activo");
let mapaUbicaciones = {};
let mapaListo = false;

/* =========================
   CARGAR UBICACIONES
========================= */
function cargarUbicaciones() {

  return fetch("http://98.90.166.154:3000/api/ubicaciones")
    .then(res => res.json())
    .then(data => {

      data.forEach(u => {
        mapaUbicaciones[u.nombre] = u.id_ubicacion;
      });

      mapaListo = true;
      console.log("Mapa listo:", mapaUbicaciones);
    });
}

/* =========================
   HIGHLIGHT + BACKEND
========================= */
function highlightCell(id) {

  clearHighlight();

  const el = document.getElementById(id);

  if (!el) {
    tooltip.textContent = 'ID no encontrado: ' + id;
    return;
  }

  el.classList.add('resaltado');
  el.scrollIntoView({ behavior: 'smooth', block: 'center' });

  tooltip.textContent = 'Seleccionado: ' + id;

  cargarInventario(id);
}

/* =========================
   INVENTARIO
========================= */
function cargarInventario(nombreZona) {

    if (!mapaListo) {
        console.warn("Mapa no cargado");
        return;
    }

    const idUbicacion = mapaUbicaciones[nombreZona];

    if (!idUbicacion) {
        console.warn("Zona no encontrada:", nombreZona);
        return;
    }

    tituloZona.textContent = "Inventario: " + nombreZona;

    fetch(`http://98.90.166.154:3000/api/inventario/${idUbicacion}`)
        .then(res => res.json())
        .then(data => {

            cuerpoTabla.innerHTML = "";

            if (!Array.isArray(data) || data.length === 0) {
                cuerpoTabla.innerHTML = `
                    <tr>
                        <td colspan="3" class="vacio">
                            No hay componentes en esta ubicación
                        </td>
                    </tr>
                `;
                return;
            }

            data.forEach(item => {

                let claseEstado = "ok";

                if (item.estado === "prestado")
                    claseEstado = "warn";

                if (item.estado === "en_reparacion")
                    claseEstado = "danger";

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
                    <td colspan="3" class="vacio">
                        Error conectando con el servidor
                    </td>
                </tr>
            `;
        });
}

/* =========================
   LIMPIAR
========================= */
function clearHighlight() {
  document.querySelectorAll('.resaltado')
    .forEach(el => el.classList.remove('resaltado'));
  tooltip.textContent = '';
}

/* =========================
   EVENTS
========================= */
document.querySelectorAll('.celda').forEach(celda => {
  celda.addEventListener('click', () => highlightCell(celda.id));
  celda.addEventListener('keydown', e => {
    if (e.key === 'Enter' || e.key === ' ') {
      e.preventDefault();
      highlightCell(celda.id);
    }
  });
});

/* =========================
   INIT
========================= */
cargarUbicaciones().then(() => {
  console.log("Sistema listo");
});