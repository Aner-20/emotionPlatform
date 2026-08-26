import { Link, Navigate, useNavigate } from 'react-router-dom';
import { useState, useContext } from 'react';
import AuthContext from '../../context/AuthContext';
import './LoginCard.css'

function LoginCard(){

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const { login } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            await login(email, password);
            console.log("Login effettuato!");
            navigate("/admin", { replace: true})
        } catch (error) {
            console.error("Errore durante il login:", error);
        }
    }

    return (
        <div className="login-card">
            <p>Bentornato</p>
            <input type="email"  placeholder='email' value={email} onChange={(e) => setEmail(e.target.value)} required/>
            <input type="password" placeholder='password' value={password} onChange={(e) => setPassword(e.target.value)} required/>
            <button onClick={handleLogin}>Login</button>
            <Link to="/">Non hai un account? Registrati</Link>
        </div>
    )
}

export default LoginCard;