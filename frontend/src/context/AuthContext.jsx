import { createContext } from "react";
import { useState, useEffect } from "react";
// AuthContext gestisce e condivide lo stato di autenticazione dell'utente in tutta l'app
// Così facendo si elimina il bisogno di dover usare props per passare le informazioni da un componente all'altro
// React deve sapere se l'utente è autenticato e quali info si hanno sull'utente

const AuthContext = createContext(null); // crea il contenitore del context

// Provider è il componente che permette alle varie parti della tua app di accedere allo stato di autenticazione

export function AuthProvider({ children }) {

    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);
    // loading = true: sta verificando il JWT
    // loading = false: ho finito di verificare
    const [token, setToken] = useState(() => {
        return localStorage.getItem("token")
    })


     useEffect(() => {
        if (!token){
            setLoading(false);
            return;
        }

        const fetchUser = async () => {
            try {
                const response = await fetch(
                "http://localhost:8080/api/users/me",
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            if (!response.ok) {
                throw new Error("Unable to retrieve user");
            }

            const data = await response.json();
            
            setUser(data);

            } catch (error) {
                console.error("Errore nel recupero dell'utente:", error);

                localStorage.removeItem("token");
                setToken(null);
                setUser(null);
            }
            finally {
                setLoading(false);
            }
        };

        fetchUser();

    }, [token]) // [token] esegui questo effetto quando token cambia

    const isAuthenticated = user !== null; // se null allora: false altrimenti è true

    const login = async (email, password) => {
         const response = await fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email,
                password
            })
        });

        if (!response.ok) {
            throw new Error("Login failed")
        }

        const data = await response.json();

        localStorage.setItem("token", data.token)

        console.log("Login response: ", data);
        setToken(data.token);
        setUser(data.user);
    }
    
    const logout = () => {
        localStorage.removeItem("token")
        setToken(null);
        setUser(null);
    }

    return (
        <AuthContext.Provider value={{user, token, isAuthenticated, loading, login, logout, }}>
            {children}
        </AuthContext.Provider>
    );
}

export default AuthContext;
