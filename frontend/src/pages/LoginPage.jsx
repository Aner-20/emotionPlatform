
import NavBar from '../components/navbar/NavBar.jsx';
import LoginCard from '../components/card/LoginCard.jsx'
import Footer from '../components/footer/Footer.jsx'

import "./LoginPage.css"


function LoginPage(){
    return (
        <div className="login-page">
            <NavBar />
            <main className='login-main'>
                <LoginCard />
            </main>
            <Footer />
        </div>
    )
}

export default LoginPage;