import { Link } from 'react-router-dom'
import logo from "../../assets/logo.png"
import './Navbar.css'

import AuthContext from '../../context/AuthContext'
import { useContext } from 'react'

function NavBar(){

    const { user } = useContext(AuthContext);
    // ? evita un errore se user è momentaneamente null
    return (
        <nav className="navbar">
            <Link to="/"><img src={logo} alt="Emotion Platform" /></Link>
            <div className="navbar-links">
                <Link to="/">Home</Link>
            
                {user?.role.name === "ADMIN" && (
                    <>
                        <Link to="/users">Users</Link>
                        <button>Log out </button>
                    </>
                    
                    
                )}

                {user?.role.name === "USER" && (
                    <>
                        <Link to="/notes">My notes</Link>
                        <button>Log out</button>
                    </>
                    
                )}
            </div>
        </nav>
    )    
}

export default NavBar;