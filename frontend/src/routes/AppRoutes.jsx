import {BrowserRouter, Routes, Route} from 'react-router-dom'

// BrowserRouter è il contenitore che abilita il sistema di routing

import LoginPage from '../pages/LoginPage.jsx'
import RegisterPage from '../pages/RegisterPage.jsx';
import AdminPage from '../pages/AdminPage.jsx';
import UserPage from '../pages/UserPage.jsx';

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
                <Route path="/register" element={
                        <PublicRoute>
                            <RegisterPage />
                        </PublicRoute>
                }/>
                <Route path="/admin" element={
                        <ProtectedRoute>
                           <AdminPage />
                        </ProtectedRoute>
                }/>
                <Route path="/user" element={
                        <ProtectedRoute>
                            <UserPage />
                        </ProtectedRoute>
                }/>

            </Routes>
        </BrowserRouter>
    )
}

export default AppRoutes;