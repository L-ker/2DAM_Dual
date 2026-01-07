import { useState } from 'react';
import './App.css';
import { 
  pixelVerde as PixelVerde,
  pixelMarron as PixelMarron,
  pixelMarronOscuro as PixelMarronOscuro,
  pixelLuz as PixelLuz,
  BotonEncender,
  BotonApagar,
  pixelVacio as PixelVacio
} from "./componentes";
import { LucesContext } from './componentes/LucesContext';

function App() {
  const [lucesEncendidas, setLucesEncendidas] = useState(false);

  const encenderLuces = () => setLucesEncendidas(true);
  const apagarLuces = () => setLucesEncendidas(false);

  return (
    <LucesContext.Provider value={{ lucesEncendidas, encenderLuces, apagarLuces }}>
      <div className='mainDiv'>
        <div className='divPixeles'>
          <PixelVerde>
            <PixelLuz className="luz"/>
          </PixelVerde>
        </div>
        <div className='divPixeles'>
          <PixelVerde>
            <PixelLuz className="luz"/>
          </PixelVerde>
          <PixelVerde />
          <PixelVerde>
            <PixelLuz className="luz"/>
          </PixelVerde>
        </div>
        <div className='divPixeles'>
          <PixelVerde />
          <PixelVerde>
            <PixelLuz className="luz"/>
          </PixelVerde>
          <PixelVerde />
          <PixelVerde>
            <PixelLuz className="luz"/>
          </PixelVerde>
          <PixelVerde />
        </div>
        <div className='divPixeles'>
            <PixelVerde>
                <PixelLuz className="luz"/>
            </PixelVerde>
            <PixelVerde/>
            <PixelVerde/>
            <PixelVerde>
                <PixelLuz className="luz"/>
            </PixelVerde>
            <PixelVerde/>   
            <PixelVerde/>
            <PixelVerde>
                <PixelLuz className="luz"/>
            </PixelVerde>
        </div>
        <div className='divPixeles'>
            <PixelMarron/>
        </div>
        <div className='divPixeles'>
            <PixelMarron/>
        </div>
        <div className='divPixeles'>
            <PixelMarronOscuro/>
            <PixelVacio/>
            <PixelMarron/>
            <PixelVacio/>
            <PixelMarronOscuro/>
        </div>
        <div className='divPixeles'>
            <PixelMarronOscuro/>
            <PixelMarronOscuro/>
            <PixelMarronOscuro/>
            <PixelMarronOscuro/>
            <PixelMarronOscuro/>
        </div>
        <div className='divPixeles'>
            <BotonEncender/>
            <BotonApagar/>
        </div>
      </div>
    </LucesContext.Provider>
  );
}

export default App;