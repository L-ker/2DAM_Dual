import React from 'react';
import useUsuarios from "./componentes/useUsuarios";
import BotonCargar from "./componentes/BotonCargar";
import ListaUsuarios from "./componentes/ListaUsuarios";
import './Usuarios.css'

function Usuarios() {

  const { usuarios, cargarUsuarios } = useUsuarios();

  return (
    <div className='main'>
      <div className='header'>
        <h1>Listado de usuarios</h1>
      </div>
      <div className='contenido'>
        <BotonCargar onClick={cargarUsuarios} />

        <ListaUsuarios usuarios={usuarios} />
      </div>  
    </div>
  );
}

export default Usuarios;
