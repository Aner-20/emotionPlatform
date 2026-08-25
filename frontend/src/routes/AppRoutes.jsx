import {BrowserRouter, Routes, Route} from 'react-router-dom'

// BrowserRouter è il contenitore che abilita il sistema di routing

import LoginPage from '../pages/LoginPage.jsx'

function AppRoutes(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<LoginPage />}></Route>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes;