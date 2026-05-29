import { createBrowserRouter, Navigate } from "react-router";
import { PATHS } from "../config/path";
import { Register } from "../pages/auth/register";
import { Login } from "../pages/auth/login";
import { AuthLayout } from "../layout/auth/auth-layout";
import { ProtectedRoute } from "./protected-route";
import { DashBoardLayout } from "../layout/dashboard/dashboard-layout";

export const ROUTES = createBrowserRouter([
    {
        path: '/',
        element: <Navigate to={`/auth/${PATHS.AUTH.login.path}`} replace/> 
    },
    {
        path: 'auth',
        element: <AuthLayout/>,
        children: [
            {
                path: PATHS.AUTH.register.path,
                element: <Register/>
            },
            {
                path: PATHS.AUTH.login.path,
                element: <Login/>
            }
        ]
    },
    {
        element: <ProtectedRoute />,
        children: [
            {   
                path: 'dashboard',
                element: <DashBoardLayout/>
            }
        ]
    }
])