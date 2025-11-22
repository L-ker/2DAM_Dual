import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { 
  Header, 
  Idiomas, 
  Experiencia, 
  SobreMi, 
  Educacion, 
  ProyectosDestacados, 
  Habilidades 
} from "./componentes";



function App() {
  return (
    <div className="contenedor-principal">
      
      {/* Header */}
      <Header />

      <div className="caja-flex">
        <div className="columna-izquierda-flex">
          {/* Sobre Mí */}
          <SobreMi />

          {/* Habilidades */}
          <Habilidades/>

          {/* Idiomas */}
          <Idiomas/>
        </div>
        <div className="columna-derecha-flex">
          {/* Experiencia */}
          <Experiencia/>

          {/* Educación */}
          <Educacion/>

          {/* Proyectos destacados */}
          <ProyectosDestacados/>
        </div>
      </div>
    </div>
  );
}

export default App
