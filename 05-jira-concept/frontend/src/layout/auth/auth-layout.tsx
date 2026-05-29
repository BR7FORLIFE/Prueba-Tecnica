import { Outlet } from "react-router";


export function AuthLayout(){
    return (
        <section className="w-screen h-dvh flex justify-center items-center">
            <Outlet />
        </section>
    )
}