import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { 
  Header,
  HeaderMarc,
  Song,
  SongMarc,
  Controls,
  Selects,
  EmojiCarousel,
  Footer
} from "./componentes";

function App() {
  return (
    <>
      <div className='mainDiv'>
        {/* Header */}
        <HeaderMarc/>

        {/* Song */}
        <SongMarc/>

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
