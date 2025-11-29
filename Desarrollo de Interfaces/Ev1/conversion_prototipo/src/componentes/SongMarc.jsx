import albumPhoto from '../assets/drawingMarc.jpg';

export default function SongMarc() {
  return (
    <div className="componentDiv songDiv">
        <div className="photoDiv">
          <img src={albumPhoto} alt="Marc Anthony's album photo"/>
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