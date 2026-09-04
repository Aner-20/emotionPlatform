import { useContext, useState } from 'react'

import NavBar from "../components/navbar/Navbar";
import Footer from "../components/footer/Footer";
import UserGreetingSection from "../components/section/UserGreetingSection";
import UserNotesSection from '../components/section/UserNotesSection';

import "./UserPage.css"

import AuthContext from '../context/AuthContext';

function UserPage(){

    const { user } = useContext(AuthContext);
    console.log(user);

    return (
        <div className="user-page">
            <NavBar />
            <main className="user-main">
                <UserGreetingSection user={user} />
                <UserNotesSection />
            </main>
            <Footer />
        </div>
    )
}

export default UserPage;