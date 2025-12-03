import React from 'react';
import useUsuarios from "./componentes/useUsuarios";
import BotonCargar from "./componentes/BotonCargar";
import ListaUsuarios from "./componentes/ListaUsuarios";

function Usuarios() {

  const { usuarios, cargarUsuarios } = useUsuarios();

  return (
    <div>
      <h2>Listado de usuarios</h2>

      <BotonCargar onClick={cargarUsuarios} />

      <ListaUsuarios usuarios={usuarios} />
    </div>
  );
}

export default Usuarios;
