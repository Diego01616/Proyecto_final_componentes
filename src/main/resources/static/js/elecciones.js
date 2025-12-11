const API_BASE = "/api/elecciones";

function mostrar(texto) {
    document.getElementById("resultado").textContent = texto;
}

function mostrarFormulario() {
    document.getElementById("formRegistro").style.display = "block";
}


// ========================
//   CARGAR TARJETÓN
// ========================
async function cargarTarjeton() {
    try {
        const res = await fetch(`${API_BASE}/tarjeton`);
        const data = await res.json();

        let html = "📘 TARJETÓN ELECTORAL\n\n";

        data.forEach(c => {
            html += `${c.numero}. ${c.nombreLibro} | ${c.partido} | ${c.votos} votos\n`;
        });

        mostrar(html);

        const lista = document.getElementById("listaVotos");
        lista.innerHTML = "";

        data.forEach(c => {
            lista.innerHTML += `
                <button class="btn voto" onclick="votar(${c.numero})">
                    ${c.numero}. ${c.nombreLibro}
                </button>`;
        });

    } catch {
        mostrar("Error cargando tarjetón");
    }
}



// ========================
//       VOTAR
// ========================
async function votar(numero) {
    try {
        const res = await fetch(`${API_BASE}/votar`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({ numero })
        });

        const resultado = await res.text();
        mostrar(resultado + "\n\nRecargando tarjetón...");

        setTimeout(cargarTarjeton, 800);

    } catch (e) {
        mostrar("Error votando");
    }
}


// ========================
//   REGISTRAR CANDIDATO
// ========================
async function registrarCandidato() {
    const nombreLibro = document.getElementById("nombreLibro").value;
    const partido = document.getElementById("partido").value;

    if (!nombreLibro || !partido) {
        mostrar("Debes llenar todos los campos");
        return;
    }

    try {
        const res = await fetch(`${API_BASE}/registrar`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({nombreLibro, partido})
        });

        const msg = await res.text();
        mostrar(msg);

        document.getElementById("nombreLibro").value = "";
        document.getElementById("partido").value = "";

        cargarTarjeton();

    } catch {
        mostrar("Error registrando candidato");
    }
}


// ========================
//     ESTADÍSTICAS
// ========================
async function cargarEstadisticas() {
    try {
        const res = await fetch(`${API_BASE}/estadisticas`);
        const stats = await res.json();

        let html = `ESTADÍSTICAS (${new Date(stats.fechaConsulta).toLocaleString("es-CO")})\n`;
        html += `Total votos: ${stats.totalVotos}\n\n`;

        stats.candidatos.forEach(c => {
            let p = stats.totalVotos > 0 ? ((c.votos / stats.totalVotos) * 100).toFixed(1) : 0;
            html += `${c.numero}. ${c.nombreLibro} → ${c.votos} votos (${p}%)\n`;
        });

        mostrar(html);

    } catch (e) {
        mostrar("Error cargando estadísticas");
    }
}


// ========================
//          AUTOR
// ========================
async function cargarAutor() {
    try {
        const res = await fetch(`${API_BASE}/autor`);
        const autor = await res.text();
        mostrar(`👤 ${autor}`);
    } catch (e) {
        mostrar("Error cargando autor");
    }
}


// Cargar tarjetón de inicio
window.onload = cargarTarjeton;
