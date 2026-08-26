import AppRoutes from './routes/AppRoutes.jsx'
import { AuthProvider } from './context/AuthContext.jsx'

import './style/App.css'
function App() {

  return (
    <AuthProvider>
        <AppRoutes />
    </AuthProvider>
  )
}

export default App
