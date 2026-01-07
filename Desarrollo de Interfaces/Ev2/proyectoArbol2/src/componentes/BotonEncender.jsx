import { useContext } from 'react';
import styled from 'styled-components';
import { LucesContext } from './LucesContext';

const Boton = styled.button`
  color: white;
  margin: 50px 50px;
  background-color: #228B22;
  border: 2px solid white;
  padding: 15px 30px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  border-radius: 8px;
  transition: all 0.3s ease;
  
  &:hover {
    transform: scale(1.05);
    box-shadow: 0 0 15px #228B22;
  }
  
  &:active {
    transform: scale(0.98);
  }
`;

const BotonEncender = () => {
  const { encenderLuces } = useContext(LucesContext);
  return (
    <Boton 
      onClick={encenderLuces}
      className="botones"
    >
      🎄 Encender Luces
    </Boton>
  );
};

export default BotonEncender;