import { Link } from 'react-router-dom';
import './LoginCard.css'

function LoginCard(){
    return (
        <div className="login-card">
            <p>Bentornato</p>
            <input type="email"  placeholder='email' required/>
            <input type="password" placeholder='password' required/>
            <button>Login</button>
            <Link to="/">Non hai un account? Registrati</Link>
        </div>
    )
}

export default LoginCard;