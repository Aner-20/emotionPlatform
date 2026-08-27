import { Link, useNavigate } from 'react-router-dom'
import logo from "../../assets/logo.png"
import './Navbar.css'

import AuthContext from '../../context/AuthContext'
import { useContext } from 'react'

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
                        <button onClick={handleLogout}>Log out </button>
                    </>
                    
                    
                )}

                {user?.role.name === "USER" && (
                    <>
                        <Link to="/notes">My notes</Link>
                        <button onClick={handleLogout}>Log out</button>
                    </>
                    
                )}
            </div>
        </nav>
    )    
}

export default NavBar;