import {BrowserRouter, Routes, Route} from 'react-router-dom'

// BrowserRouter è il contenitore che abilita il sistema di routing

import LoginPage from '../pages/LoginPage'

function AppRoutes(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/login" element={<LoginPage />}></Route>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes;