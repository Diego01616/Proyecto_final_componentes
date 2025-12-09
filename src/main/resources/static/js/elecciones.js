const API_BASE = '/api/elecciones';

async function mostrar(mensaje) {
    document.getElementById('resultado').textContent = mensaje;
}

async function cargarTarjeton() {
    try {
        const res = await fetch(`${API_BASE}/tarjeton`);
        const data = await res.json();
        let html = 'TARJETÓN ELECTORAL:\n\n';
        data.forEach(c => {
            html += `${c.numero}. ${c.nombreLibro.padEnd(35)} | ${c.partido.padEnd(15)} | ${c.votos} votos\n`;
        });
        mostrar(html);
    } catch(e) { 
        mostrar('Error cargando tarjetón'); 
    }
}

async function votar(numero) {
    try {
        const res = await fetch(`${API_BASE}/votar`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({numero})
        });
        const resultado = await res.text();
        mostrar(`${resultado}\n\nRecarga el tarjetón para ver cambios`);
        setTimeout(cargarTarjeton, 1000); // Auto-recarga
    } catch(e) { 
        mostrar('Error votando'); 
    }
}

async function cargarEstadisticas() {
    try {
        const res = await fetch(`${API_BASE}/estadisticas`);
        const stats = await res.json();
        let html = `ESTADÍSTICAS (${new Date(stats.fechaConsulta).toLocaleString('es-CO')})\n`;
        html += `Total votos: ${stats.totalVotos}\n\n`;
        stats.candidatos.forEach(c => {
            const porc = stats.totalVotos > 0 ? ((c.votos/stats.totalVotos)*100).toFixed(1) : 0;
            html += `${c.numero}. ${c.nombreLibro.padEnd(30)} ${c.votos} votos (${porc}%)\n`;
        });
        mostrar(html);
    } catch(e) { 
        mostrar('Error cargando estadísticas'); 
    }
}

async function cargarAutor() {
    try {
        const res = await fetch(`${API_BASE}/autor`);
        const autor = await res.text();
        mostrar(`👤 ${autor}`);
    } catch(e) { 
        mostrar('Error cargando autor'); 
    }
}

// Cargar tarjetón al iniciar
window.onload = () => cargarTarjeton();
