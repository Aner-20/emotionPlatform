import { Navigate } from "react-router-dom"
import { useContext } from "react"
import AuthContext from "../context/AuthContext"

function ProtectedRoute({ children }){
    const { isAuthenticated, loading } = useContext(AuthContext);
    
    if (loading) {
        return <p>Caricamento...</p>
    }

    if(!isAuthenticated){
        return <Navigate to="/" replace />
    }

    return children; // se è autenticato

}


export default ProtectedRoute;