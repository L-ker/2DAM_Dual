import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Usuarios from './Usuarios'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div>
      <Usuarios/>
    </div>
  )
}

export default App
