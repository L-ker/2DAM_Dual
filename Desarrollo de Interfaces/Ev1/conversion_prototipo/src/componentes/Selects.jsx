import drawing from '../assets/drawing.jpg';

export default function Song() {
  return (
    <div className="componentDiv selectsDiv">
      <div>
        <label className="checkLabel">
          <input type="checkbox" /> Not selected
        </label>
        <br/>
        <label className="checkLabel">
          <input type="checkbox" checked /> Selected
        </label>
        <br/>
        <label className="checkLabel">
          <input type="checkbox" /> Indeterminate
        </label>
    </div>

    <div>
        <label className="checkLabel">
          <input type="checkbox" /> Not selected
        </label>
        <br/>
        <label className="checkLabel">
          <input type="checkbox" checked /> Selected
        </label>
        <br/>
        <label className="checkLabel">
          <input type="checkbox" /> Indeterminate
        </label>
    </div>
    </div>
  );  
}