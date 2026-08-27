import { Link, useNavigate } from 'react-router-dom'
import { useContext } from 'react'
import logo from "../../assets/logo.png"

import Button from '../button/Button'

import './Navbar.css'

import AuthContext from '../../context/AuthContext'


function NavBar(){

    const { user, logout } = useContext(AuthContext);
    const navigate = useNavigate()

    const handleLogout = () => {
        console.log("Ok")
        logout();
        navigate("/", { replace: true })
    }

    // ? evita un errore se user è momentaneamente null
    return (
        <nav className="navbar">
            <Link className="navbar-logo-link" to="/"><img className="navbar-logo" src={logo} alt="Emotion Platform" /></Link>
            <div className="navbar-links">
                <Link to="/">Home</Link>
            
                {user?.role.name === "ADMIN" && (
                    <>
                        <Link to="/users">Users</Link>
                        <Button text= "Log out" onClick={handleLogout}></Button>
                    </>
                    
                    
                )}

                {user?.role.name === "USER" && (
                    <>
                        <Link to="/notes">My notes</Link>
                        <Button text="Log out" onClick={handleLogout}></Button>
                    </>
                    
                )}
            </div>
        </nav>
    )    
}

export default NavBar;