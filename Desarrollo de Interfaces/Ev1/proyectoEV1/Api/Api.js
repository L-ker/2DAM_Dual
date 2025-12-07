// Importas el módulo 'http' usando sintaxis ES Modules
import http from 'http';

// Defines un pequeño array con usuarios de ejemplo
const usuarios = [
  { id: 1, nombre: 'Ivan',  apellidos: 'Ezquerro', urlImagen: 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRJEjoYKMRgjkWN-NmwMhHQRWhgkTmYqXVVqA&s',   fechaNacimiento: '1990-05-15' },
  { id: 2, nombre: 'Adrán', apellidos: 'Laga', urlImagen: 'https://images.unsplash.com/photo-1564564321837-a57b7070ac4f?fm=jpg&q=60&w=3000&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8aG9tYnJlJTIwZXNwYSVDMyVCMW9sfGVufDB8fDB8fHww', fechaNacimiento: '1985-10-30' },
  { id: 3, nombre: 'Victor',  apellidos: 'Simón', urlImagen: 'https://media.revistagq.com/photos/606b3183a359af169e483dcb/16:9/w_2560%2Cc_limit/estar-guapo.jpeg',   fechaNacimiento: '1990-05-15' },
  { id: 4, nombre: 'Rafael', apellidos: 'Valerio', urlImagen: 'https://www.hticlinic.com/wp-content/uploads/2023/03/emotions-people-concept-headshot-handsome-thoughtful-man-smiling-satisfied-touching-beard.jpg', fechaNacimiento: '1985-10-30' },
  { id: 5, nombre: 'Nerea',  apellidos: 'Pellés', urlImagen: 'https://i1.sndcdn.com/avatars-EJ9GGrkQ9typ3a0d-v74n4Q-t1080x1080.jpg',   fechaNacimiento: '1990-05-15' },
  { id: 6, nombre: 'Ruben', apellidos: 'Pasamon', urlImagen: 'https://media.revistavanityfair.es/photos/60e84b8e5be4efc0659fa06e/master/w_1600%2Cc_limit/39402.jpg', fechaNacimiento: '1985-10-30' },
  { id: 7, nombre: 'Victor',  apellidos: 'Lacruz', urlImagen: 'https://cdn-images.dzcdn.net/images/artist/be0a7c550567f4af0ed202d7235b74d6/1900x1900-000000-81-0-0.jpg',   fechaNacimiento: '1990-05-15' },
  { id: 8, nombre: 'Irene', apellidos: 'Fernández', urlImagen: 'https://cdn-images.dzcdn.net/images/cover/7ce6b8452fae425557067db6e6a1cad5/0x1900-000000-80-0-0.jpg', fechaNacimiento: '1985-10-30' },
  { id: 9, nombre: 'Lilja',  apellidos: 'Svara', urlImagen: 'https://images.mubicdn.net/images/cast_member/553878/cache-253671-1504830910/image-w856.jpg',   fechaNacimiento: '1990-05-15' },
  { id: 10, nombre: 'Lucas', apellidos: 'Perez', urlImagen: 'https://i.discogs.com/0B5XUwHWfM-EJ_5e9c5xSXSUyIdStA713I3Rr31ERVg/rs:fit/g:sm/q:40/h:300/w:300/czM6Ly9kaXNjb2dz/LWRhdGFiYXNlLWlt/YWdlcy9SLTIxOTI4/OTk2LTE3MDU0MzU3/ODItNzY5NS5qcGVn.jpeg', fechaNacimiento: '1985-10-30' }
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