import { Link, Navigate, useNavigate } from 'react-router-dom';
import { useState, useContext } from 'react';

import AuthContext from '../../context/AuthContext';


import Modal from '../modal/Modal';
import Button from '../button/Button';

import './LoginCard.css'

function LoginCard(){

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [showModal, setShowModal] = useState(false);

    const { login } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            
            //await login(email, password);
            const loggedUser = await login(email, password);
            console.log(loggedUser.role.name);
            console.log("Login effettuato!");

            if (loggedUser.role.name === "ADMIN"){
                navigate("/admin", { replace: true}) // per fare in modo che non si possa tornare indietro con la freccia del browser
            }

            else if (loggedUser.role.name === "USER"){
                navigate("/user", { replace: true })
            }

            
        } catch (error) {
            console.error("Errore durante il login:", error);
            setShowModal(true)
        }
    }


    // <> </> permette a React di avere più elementi senza aggiungere un altro <div> al DOM
    return (
        <>
            <div className="login-card">
                <p>Bentornato</p>
                <input type="email"  placeholder='email' value={email} onChange={(e) => setEmail(e.target.value)} required/>
                <input type="password" placeholder='password' value={password} onChange={(e) => setPassword(e.target.value)} required/>
                <Button text="Login" onClick={handleLogin}></Button>
                <Link to="/">Non hai un account? Registrati</Link>
            </div>

            {showModal && (
                <Modal
                    title="Login fallito"
                    message="Email o password non corrette"
                    onClose={() => setShowModal(false)}
                />
            )}

        </>
    )
}

export default LoginCard;