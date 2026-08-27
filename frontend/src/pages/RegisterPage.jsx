import NavBar from '../components/navbar/Navbar.jsx'
import RegisterCard from '../components/card/RegisterCard.jsx'
import Footer from '../components/footer/Footer.jsx'

import "./RegisterPage.css"

function RegisterPage(){
    return (
        <div className="register-page">
            <NavBar></NavBar>
            <main className="register-main">
                <RegisterCard></RegisterCard>
            </main>
            <Footer></Footer>
        </div>
    )
}

export default RegisterPage;