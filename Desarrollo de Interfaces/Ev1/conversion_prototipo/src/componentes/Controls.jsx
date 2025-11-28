import { useState } from "react";

export default function Song() {
  /**
   * Use state es una función de react que devuelve:
   * Value - valor inicial (el que se pasa como parametro)
   * setValue - función para cambiar el valor
   */
  const [value, setValue] = useState(30); 
  const [value1, setValue1] = useState(30); 

  return (
    <div className="componentDiv ">
      <button>⏹️</button>

      <input
        className="progressBar"
        type="range"
        min="0"
        max="100"
        value={value}
        onChange={(e) => setValue(e.target.value)}
        style={{
          width: "60%",
          /**
           * En el css tengo unas propiedades asignadas a un valor custom que es --progress y aqui lo que hago es pasarselo para que cambie dichos atributos
           */
          "--progress": `${value}%`
        }}
      />

      <button>🔊</button>

      <div className="componentDiv controlsDiv">
        <input
        className="progressBar"
        type="range"
        min="0"
        max="100"
        value={value1}
        onChange={(e) => setValue1(e.target.value)}
        style={{
          width: "20%",
          /**
           * En el css tengo unas propiedades asignadas a un valor custom que es --progress y aqui lo que hago es pasarselo para que cambie dichos atributos
           */
          "--progress": `${value1}%`
        }}
        />
        <div>
          <button>⏪</button>
          <button>▶️</button>
          <button>⏩</button>
        </div>
      </div>
    </div>
  );
}
