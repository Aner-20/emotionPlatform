import {BrowserRouter, Routes, Route} from 'react-router-dom'

// BrowserRouter è il contenitore che abilita il sistema di routing

import LoginPage from '../pages/LoginPage.jsx'
import AdminPage from '../pages/AdminPage.jsx';

import ProtectedRoute from './ProtectedRoute.jsx';
import PublicRoute from './PublicRoute.jsx';

function AppRoutes(){
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={
                        <PublicRoute>
                            <LoginPage />
                        </PublicRoute>
                }/>
                <Route path="/admin" element={
                        <ProtectedRoute>
                           <AdminPage />
                        </ProtectedRoute>
                }/>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes;