import { useState } from "react";

function useUsuarios() {
  const [usuarios, setUsuarios] = useState([]);

  function cargarUsuarios() {
    fetch('http://localhost:3000/api/usuarios')
      .then(res => res.json())
      .then(datos => setUsuarios(datos))
      .catch(err => console.error("Error cargando usuarios:", err));
  }

  return { usuarios, cargarUsuarios };
}

export default useUsuarios;
