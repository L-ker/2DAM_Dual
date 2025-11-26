import drawing from '../assets/drawing.jpg';

export default function Song() {
  return (
    <div>
        <div  className="componentDiv controlsDiv">
            <button>Cuadrado</button>
            <button>Volumen</button>
        </div>
        <div>
            <input
                className='controlsSlider'
                type="range"
                style={{ width: "70%" }}
            />
        </div>
    </div>
  );
}