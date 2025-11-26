import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { 
  Header,
  Song,
  Controls,
  Selects,
  EmojiCarousel,
  Footer
} from "./componentes";

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div className='mainDiv'>
        {/* Header */}
        <Header/>

        <hr/>

        {/* Song */}
        <Song/>

        {/* Controls */}
        <Controls/>

        {/* Selects */}
        <Selects/>

        {/* EmojiCarousel */}
        <EmojiCarousel/>

        {/* Footer */}
        <Footer/>
        
      </div>
    </>
  )
}

export default App
