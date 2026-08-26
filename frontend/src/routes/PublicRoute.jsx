import { Navigate } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../context/AuthContext.jsx";

// PublicRoute fa in modo che l'utente autenticato non possa nuovamente visualizzare nuovamente la pagina di login

function PublicRoute({ children }){
    const { isAuthenticated, loading } = useContext(AuthContext);

    if (loading){
        return <p>Caricamento...</p>
    }

    if (isAuthenticated){
        // replace dice a React Router di sostituire la pagina corrente nella cronologia del browser, invece di aggiungere una nuova voce
        // replace fa in modo che l'uteten non possa tornare ad esempio in questo caso a / (pagina di login)
        return <Navigate to="/admin" replace />
    }

    return children;

}

export default PublicRoute;