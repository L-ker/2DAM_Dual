function ListaUsuarios({ usuarios }) {
  return (
    <ul>
      {usuarios.map(u => (
        <li key={u.id}>
          <img src={u.urlImagen} width="50" alt={u.nombre} />
          {u.nombre} {u.apellidos} - {u.fechaNacimiento}
        </li>
      ))}
    </ul>
  );
}

export default ListaUsuarios;
