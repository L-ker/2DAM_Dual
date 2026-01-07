import { useContext } from 'react';
import styled from 'styled-components';
import { LucesContext } from './LucesContext';

const LuzEstilizada = styled.div`
  width: 25px;
  height: 25px;
  background-color: ${props => props.$encendida ? '#FFD700' : '#f05555ff'};
  border-radius: 50%;
  transition: all 0.3s ease;
  box-shadow: ${props => props.$encendida ? 
    '0 0 10px #FFD700, 0 0 20px #FFD700, 0 0 30px #FFA500' : 
    'none'};
`;

const PixelLuz = ({ className }) => {
  const { lucesEncendidas } = useContext(LucesContext);
  return <LuzEstilizada className={className} $encendida={lucesEncendidas} />;
};

export default PixelLuz;