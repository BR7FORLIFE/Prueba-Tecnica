import { AdminDashboard } from "../../pages/dashboard/dashboard-admin";
import { UserDashboard } from "../../pages/dashboard/dashboard-user";
import { useAuthStore } from "../../stores/user-store";
export function DashBoardLayout() {
  const { role } = useAuthStore();

  return (
    <section className=" min-h-screen flex flex-col justify-center items-center">
      <article className="flex flex-1">
        {role === "ADMIN" ? <AdminDashboard /> : <UserDashboard />}
      </article>
    </section>
  );
}
