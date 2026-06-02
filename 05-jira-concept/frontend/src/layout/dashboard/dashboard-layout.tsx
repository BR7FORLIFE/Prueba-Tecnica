import { AdminDashboard } from "../../pages/dashboard/dashboard-admin";
import { UserDashboard } from "../../pages/dashboard/dashboard-user";
import { useAuthStore } from "../../stores/user-store";
import { Header } from "./header";

export function DashBoardLayout() {
  const { id , role } = useAuthStore();

  return (
    <section className="w-screen h-dvh flex flex-col justify-center items-center">
      <Header id={id} role={role} />
      <article className="flex flex-1">
        {role === "ADMIN" ? <AdminDashboard /> : <UserDashboard />}
      </article>
    </section>
  );
}
