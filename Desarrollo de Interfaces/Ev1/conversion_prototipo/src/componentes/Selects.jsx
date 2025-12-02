import { useEffect } from "react";

export default function Selects() {

  /*
  Recibe una funcion que quieres que suceda despues del render y un array de depenedncias, vacio = ejecuta 1 vez cuando se usa el componente
  ademas esto garantiza que se se use post carga del dom
  */
  useEffect(() => {
    document.querySelectorAll(".intermedio").forEach(ch => {
      ch.indeterminate = true;
    });
  }, []);


  return (
    <div className="componentDiv selectsDiv">
      <div>
        <label className="checkLabel">
          <input type="checkbox" /> Not selected
        </label>
        <label className="checkLabel">
          <input type="checkbox" defaultChecked /> Selected
        </label>
        <label className="checkLabel">
          <input type="checkbox" className="intermedio"/> Indeterminate
        </label>
    </div>

    <div>
        <label className="checkLabel">
          <input type="checkbox" /> Not selected
        </label>
        <label className="checkLabel">
          <input type="checkbox" defaultChecked /> Selected
        </label>
        <label className="checkLabel">
          <input type="checkbox" className="intermedio"/> Indeterminate
        </label>
    </div>
    </div>
  );  
}