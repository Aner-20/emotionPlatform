import { Navigate } from "react-router-dom"
// Navigate serve a react per effettuare automaticamente un redirect
import { useContext } from "react"
import AuthContext from "../context/AuthContext"

import UnAuthorizedPage from "../pages/UnAuthorizedPage.jsx";

// replace stabilisce come viene modificata la cronologia del browsere fa in modo che non si possa tornare indietro

function ProtectedRoute({ children }){
    const { isAuthenticated, loading } = useContext(AuthContext);
    
    if (loading) {
        return <p>Caricamento...</p>
    }

    if(!isAuthenticated){
        //return <Navigate to="/" replace />
        return <UnAuthorizedPage />
    }

    return children; // se è autenticato

}


export default ProtectedRoute;