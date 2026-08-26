import {BrowserRouter, Routes, Route} from 'react-router-dom'

// BrowserRouter è il contenitore che abilita il sistema di routing

import LoginPage from '../pages/LoginPage.jsx'
import AdminPage from '../pages/AdminPage.jsx';

function AppRoutes(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LoginPage />}></Route>
                <Route path="/admin" element={<AdminPage />}></Route>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes;