import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  return (
    <div className="contenedor-principal">
      
      {/* Header */}
      <div className="header">
        <img 
          src="https://avatars.githubusercontent.com/u/55660363?v=4" 
          alt="Mi foto" 
          className="foto-perfil"
        />
        <h1>Lucas Perez Garcia</h1>
        <h2>Full Stack Developer</h2>
        <p>📧 Lucas Pérez García | 📱 657-806-739 | 📍 Zaragoza</p>
      </div>

      <div className="caja-flex">
        <div className="columna-izquierda-flex">
          {/* Sobre Mí */}
          <div className="seccion">
            <h2 className="titulo-seccion">· Sobre Mí</h2>
            <p>Desarrollador full stack con 1 año de experiencia capacitado para el desarrollo de aplicaciones Web y Multiplataforma, conocimientos en bases de datos y servidores</p>
          </div>

          {/* Habilidades */}
          <div className="seccion">
            <h2 className="titulo-seccion">· Habilidades</h2>
            <div className="lista-habilidades">
              <div className="habilidad">PHP
                <div class="stars">
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                </div>
              </div>
              <div className="habilidad">SQL
                <div class="stars">
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                </div>
              </div>
              <div className="habilidad">Laravel
                <div class="stars">
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star">★</span>
                </div>
              </div>
              <div className="habilidad">JavaScript
                <div class="stars">
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star">★</span>
                </div>
              </div>
              <div className="habilidad">Java
                <div class="stars">
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span> 
                  <span class="star">★</span>
                </div>
              </div>
              <div className="habilidad">HTML 
                <div class="stars">
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star">★</span>
                </div>
              </div>
              <div className="habilidad">CSS
                <div class="stars">
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star">★</span>
                  <span class="star">★</span>
                </div>
              </div>
              <div className="habilidad">Tailwind
                <div class="stars">
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star">★</span>
                  <span class="star">★</span>
                </div>
              </div>
              <div className="habilidad">Kotlin
                <div class="stars">
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star">★</span>
                  <span class="star">★</span>
                  <span class="star">★</span>
                </div>
              </div>
              <div className="habilidad">Python
                <div class="stars">
                  <span class="star filled">★</span>
                  <span class="star filled">★</span>
                  <span class="star">★</span>
                  <span class="star">★</span>
                  <span class="star">★</span>
                </div>
              </div>
            </div>
          </div>

          {/* Sobre Mí */}
          <div className="seccion">
            <h2 className="titulo-seccion">· Idiomas</h2>
            <p>Desarrollador full stack con 1 año de experiencia capacitado para el desarrollo de aplicaciones Web y Multiplataforma, conocimientos en bases de datos y servidores</p>
          </div>

          {/* Sobre Mí */}
          <div className="seccion">
            <h2 className="titulo-seccion">· Certificaciones</h2>
            <p>Desarrollador full stack con 1 año de experiencia capacitado para el desarrollo de aplicaciones Web y Multiplataforma, conocimientos en bases de datos y servidores</p>
          </div>
        </div>
        <div className="columna-derecha-flex">
            {/* Experiencia */}
          <div className="seccion">
            <h2 className="titulo-seccion">· Experiencia</h2>
            
            <div className="item-experiencia">
              <h3>Trainee</h3>
              <p className="empresa">Deloitte</p>
              <p className="periodo">04/2025 - 06/2025</p>
              <p>Becario en el equipo de integración utilizando la tecnología de Mulesoft</p>
            </div>

            <div className="item-experiencia">
              <h3>Student</h3>
              <p className="empresa">NTT DATA</p>
              <p className="periodo">09/2025 - Actualidad</p>
              <p>Student en el equipo de Low Code utilizando la tecnología Microsoft Power Platform</p>
            </div>
          </div>

          {/* Educación */}
          <div className="seccion">
            <h2 className="titulo-seccion">Educación</h2>
            
            <div className="item-experiencia">
              <h3>Desarrollador de Aplicacions Web</h3>
              <p className="empresa">CPIFP Los Enlaces</p>
              <p className="periodo">2023 - 2025</p>
            </div>
            <div className="item-experiencia">
              <h3>Título Obtenido</h3>
              <p className="empresa">Desarrollador de Aplicaciones Multiplataforma (Dual intensiva)</p>
              <p className="periodo">2025 - 2026</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default App
