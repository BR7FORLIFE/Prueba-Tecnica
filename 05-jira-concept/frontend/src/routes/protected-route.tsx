import { Navigate, Outlet } from "react-router";
import { useAuthStore } from "../stores/user-store";

export function ProtectedRoute(){
    const { id } = useAuthStore()

    if(!id){
        return <Navigate to='/auth/login' replace/>
    }

    return <Outlet/>
}