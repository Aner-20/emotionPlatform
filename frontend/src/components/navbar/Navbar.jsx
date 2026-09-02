import { Link, useNavigate } from 'react-router-dom'
import { useContext, useState } from 'react'
import logo from "../../assets/logo.png"

import Button from '../button/Button'
import AccountModal from '../modal/AccountModal'

import './Navbar.css'

import AuthContext from '../../context/AuthContext'

function NavBar(){

    const { user, logout } = useContext(AuthContext);
    const navigate = useNavigate()
    console.log(user)

    const [showAccountModal, setShowAccountModal] = useState(false);

    const handleLogout = () => {
        console.log("Ok")
        logout();
        navigate("/", { replace: true })
    }

    // ? evita un errore se user è momentaneamente null
    return (
        <>
        
            <nav className="navbar">
                <Link className="navbar-logo-link" to="/"><img className="navbar-logo" src={logo} alt="Emotion Platform" /></Link>
                <div className="navbar-links">
                    
                
                    {user?.role.name === "ADMIN" && (
                        <>
                            <Link to="">Users</Link>
                            <Button text= "Log out" onClick={handleLogout}></Button>
                        </>
                        
                        
                    )}

                    {user?.role.name === "USER" && (
                        <>
                            <Button text="Account" onClick={() => setShowAccountModal(true)} className="account-button"></Button>
                            <Link to="">My notes</Link>
                            <Button text="Log out" onClick={handleLogout}></Button>
                        </>
                        
                    )}
                </div>
            </nav>

        {showAccountModal && (
            
            <AccountModal 
                user={user}
                onClose={() => setShowAccountModal(false)}

            />
              
                
            )}

        </>
    )    
}

export default NavBar;