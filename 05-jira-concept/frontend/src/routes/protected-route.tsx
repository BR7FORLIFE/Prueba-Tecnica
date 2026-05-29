import { Navigate, Outlet } from "react-router";
import { useAuthStore } from "../stores/user-store";

export function ProtectedRoute(){
    const { accessToken } = useAuthStore()

    if(!accessToken){
        return <Navigate to='/auth/login' replace/>
    }

    return <Outlet/>
}