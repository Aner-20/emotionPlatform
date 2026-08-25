
import NavBar from '../components/navbar/NavBar.jsx';
import LoginCard from '../components/card/LoginCard.jsx'
import Footer from '../components/footer/Footer.jsx'



function LoginPage(){
    return (
        <div className="login-page">
            <NavBar />
            <main>
                <LoginCard />
            </main>
            <Footer />
        </div>
    )
}

export default LoginPage;