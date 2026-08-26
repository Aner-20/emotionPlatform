import { Navigate } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../context/AuthContext.jsx";

// PublicRoute fa in modo che l'utente autenticato non possa nuovamente visualizzare nuovamente la pagina di login

function PublicRoute({ children }){
    //const { isAuthenticated, loading } = useContext(AuthContext);
    const { user, loading } = useContext(AuthContext); 

    if (loading){
        return <p>Caricamento...</p>
    }

    if (user){
        if (user.role.name === "ADMIN"){
            return <Navigate to="/admin" replace />
        }
        
        else if (user.role.name === "USER"){
            return <Navigate to="/user" replace />
        }
    }

    return children;

}

export default PublicRoute;