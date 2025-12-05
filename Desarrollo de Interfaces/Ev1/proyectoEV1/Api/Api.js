// Importas el módulo 'http' usando sintaxis ES Modules
import http from 'http';

// Defines un pequeño array con usuarios de ejemplo
const usuarios = [
  { id: 1, nombre: 'Ivan',  apellidos: 'Ezquerro', urlImagen: 'https://randomuser.me/api/portraits/men/1.jpg',   fechaNacimiento: '1990-05-15' },
  { id: 2, nombre: 'Adrán', apellidos: 'Laga', urlImagen: 'https://randomuser.me/api/portraits/men/2.jpg', fechaNacimiento: '1985-10-30' },
  { id: 3, nombre: 'Victor',  apellidos: 'Simón', urlImagen: 'https://randomuser.me/api/portraits/men/3.jpg',   fechaNacimiento: '1990-05-15' },
  { id: 4, nombre: 'Rafael', apellidos: 'Valerio', urlImagen: 'https://randomuser.me/api/portraits/men/4.jpg', fechaNacimiento: '1985-10-30' },
  { id: 5, nombre: 'Nerea',  apellidos: 'Pellés', urlImagen: 'https://randomuser.me/api/portraits/women/5.jpg',   fechaNacimiento: '1990-05-15' },
  { id: 6, nombre: 'Ruben', apellidos: 'Pasamon', urlImagen: 'https://randomuser.me/api/portraits/men/6.jpg', fechaNacimiento: '1985-10-30' },
  { id: 7, nombre: 'Victor',  apellidos: 'Lacruz', urlImagen: 'https://randomuser.me/api/portraits/men/7.jpg',   fechaNacimiento: '1990-05-15' },
  { id: 8, nombre: 'Irene', apellidos: 'Fernández', urlImagen: 'https://randomuser.me/api/portraits/women/8.jpg', fechaNacimiento: '1985-10-30' },
  { id: 9, nombre: 'Lilja',  apellidos: 'Svara', urlImagen: 'https://randomuser.me/api/portraits/women/9.jpg',   fechaNacimiento: '1990-05-15' },
  { id: 10, nombre: 'Lucas', apellidos: 'Perez', urlImagen: 'https://randomuser.me/api/portraits/men/10.jpg', fechaNacimiento: '1985-10-30' }
];

// Crea el servidor HTTP con soporte básico para CORS
const server = http.createServer((req, res) => {
  // Permite las peticiones desde el frontend en el puerto 5173
  res.setHeader('Access-Control-Allow-Origin', 'http://localhost:5173');
  res.setHeader('Access-Control-Allow-Methods', 'GET');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type');

  if (req.method === 'OPTIONS') {
    res.writeHead(204);
    return res.end();
  }

  if (req.method === 'GET' && req.url === '/api/usuarios') {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(usuarios));
  } else {
    res.writeHead(404);
    res.end('No encontrado');
  }
});

// El servidor escucha en el puerto 3000
server.listen(3000, () => {
  console.log('Servidor API muy sencillo en http://localhost:3000');
});