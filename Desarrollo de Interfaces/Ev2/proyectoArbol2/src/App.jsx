import React, { useState } from 'react';
import './App.css';
import LeafPixel from './components/LeafPixel';
import TrunkPixel from './components/TrunkPixel';
import PotPixel from './components/PotPixel';
import LightPixel from './components/LightPixel';
import LightButtons from './components/LightButtons';

const App = () => {
    const [lightsOn, setLightsOn] = useState(false);

    const turnOnLights = () => setLightsOn(true);
    const turnOffLights = () => setLightsOn(false);

    return (
        <div className="container">
            <div className="tree">
                {/* Piso 1 */}
                <div className="row">
                    <LeafPixel />
                    <LightPixel isOn={lightsOn} />
                    <LeafPixel />
                </div>
                {/* Piso 2 */}
                <div className="row">
                    <LeafPixel />
                    <LeafPixel />
                    <LeafPixel />
                </div>
                {/* Tronco */}
                <div className="row">
                    <TrunkPixel />
                </div>
                {/* Maceta */}
                <div className="row">
                    <PotPixel />
                </div>
            </div>

            {/* Botones */}
            <LightButtons turnOn={turnOnLights} turnOff={turnOffLights} />
        </div>
    );
};

export default App;
