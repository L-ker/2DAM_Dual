import { useState } from "react";

export default function EmojiCarousel() {
  const emojis = ["😷", "😄", "🤠", "😈", "🤖"];
  const [selected, setSelected] = useState(2);

  return (
    <div className="componentDiv emojisDiv">
      {emojis.map((emoji, i) => (
        <span
          key={i}
          className={i === selected ? "emoji selected" : "emoji"}
          onClick={() => setSelected(i)}
        >
          {emoji}
        </span>
      ))}
    </div>
  );
}
