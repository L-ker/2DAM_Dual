export default function ProyectosDestacados() {
  return (
    <div className="seccion">
        <h2 className="titulo-seccion">· Proyectos destacados</h2>

        <div className="item-proyecto">
            <h3>Proyecto de fin de grado (DAW)</h3>
            <p className="lenguajes">PHP · Laravel · MySQL</p>
            <a href="https://github.com/L-ker/ProyectoFinDeGrado" target="_blank">
            <img 
                src="https://cdn-icons-png.flaticon.com/512/25/25231.png" 
                className="github-icon" 
                alt="GitHub"
            />
            </a>
        </div>

        <div className="item-proyecto">
            <h3>Página Explicación Hugo</h3>
            <p className="lenguajes">HTML · CSS · JavaScript</p>
            <a href="https://github.com/L-ker/trabajoHugo" target="_blank">
            <img 
                src="https://cdn-icons-png.flaticon.com/512/25/25231.png" 
                className="github-icon" 
                alt="GitHub"
            />
            </a>
        </div>

        <div className="item-proyecto">
            <h3>Proyecto CRUD Laravel</h3>
            <p className="lenguajes">Laravel · Blade · MySQL</p>
            <a href="https://github.com/L-ker/ProyectoCrudLaravel" target="_blank">
            <img 
                src="https://cdn-icons-png.flaticon.com/512/25/25231.png" 
                className="github-icon" 
                alt="GitHub"
            />
            </a>
        </div>
    </div>
  );
}