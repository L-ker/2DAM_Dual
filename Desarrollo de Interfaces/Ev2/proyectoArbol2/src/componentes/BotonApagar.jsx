import { useContext } from 'react';
import styled from 'styled-components';
import { LucesContext } from './LucesContext';

const Boton = styled.button`
  color: white;
  margin: 50px 50px;
  background-color: #8B0000;
  border: 2px solid white;
  padding: 15px 30px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  border-radius: 8px;
  transition: all 0.3s ease;
  
  &:hover {
    transform: scale(1.05);
    box-shadow: 0 0 15px #8B0000;
  }
  
  &:active {
    transform: scale(0.98);
  }
`;

const BotonApagar = () => {
  const { apagarLuces } = useContext(LucesContext);
  return (
    <Boton 
      onClick={apagarLuces}
      className="botones"
    >
      ❌ Apagar Luces
    </Boton>
  );
};

export default BotonApagar;