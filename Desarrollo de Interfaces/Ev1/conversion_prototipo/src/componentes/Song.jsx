import drawing from '../assets/drawing.jpg';

export default function Song() {
  return (
    <div className="componentDiv songDiv">
        <div className="photoDiv">
          <img src={drawing} alt="Alejandro Sanz drawing"/>
        </div>
        <div className="textDiv">
          <div className="textPlaceholder long"></div>
          <div className="textPlaceholder medium"></div>
          <div className="textPlaceholder long"></div>
          <div className="textPlaceholder short"></div>
        </div>
    </div>
  );
}