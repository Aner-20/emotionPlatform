import { Link } from 'react-router-dom'
import logo from "../../assets/logo.png"
import './Navbar.css'
function NavBar(){
    return (
        <nav className="navbar">
            <Link to="/"><img src={logo} alt="Emotion Platform" /></Link>
            <div className="navbar-links">
                <Link to="/">Home</Link>
                <Link to="/">Account</Link>
            </div>
        </nav>
    )    
}

export default NavBar;