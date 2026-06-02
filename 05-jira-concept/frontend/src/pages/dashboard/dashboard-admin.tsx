import {
  Home,
  Layers,
  ClipboardList,
  Settings,
  ShieldCheck,
  CheckCircle2,
  Activity,
  Plus,
  Edit3,
  Trash2,
  LogOut,
} from "lucide-react";

const metricCards = [
  {
    label: "Total Projects",
    value: 18,
    icon: Layers,
    accent: "from-sky-500 to-indigo-500",
  },
  {
    label: "Total Tasks",
    value: 124,
    icon: ClipboardList,
    accent: "from-emerald-500 to-teal-500",
  },
  {
    label: "Tasks In Progress",
    value: 38,
    icon: Activity,
    accent: "from-amber-500 to-orange-500",
  },
  {
    label: "Completed Tasks",
    value: 82,
    icon: CheckCircle2,
    accent: "from-violet-500 to-fuchsia-500",
  },
];

const projects = [
  {
    name: "Launch Portal Refresh",
    description: "Rediseño completo del panel de usuario y mejoras de accesibilidad.",
    tasks: 24,
  },
  {
    name: "Sprint Automation",
    description: "Configurar flujos automáticos para la asignación y validación de tareas.",
    tasks: 18,
  },
  {
    name: "Integración API Externa",
    description: "Conectar servicios de calendario y notificaciones en tiempo real.",
    tasks: 12,
  },
  {
    name: "Sistema de Reportes",
    description: "Panel de métricas con filtros avanzados y exportación de datos.",
    tasks: 10,
  },
];

const recentTasks = [
  {
    title: "Revisar backlog de sprint",
    status: "TODO",
    priority: "HIGH",
    project: "Launch Portal Refresh",
  },
  {
    title: "Validar endpoint de notificaciones",
    status: "IN_PROGRESS",
    priority: "MEDIUM",
    project: "Integración API Externa",
  },
  {
    title: "Diseño del modal de invitaciones",
    status: "DONE",
    priority: "LOW",
    project: "Sprint Automation",
  },
  {
    title: "Optimizar consultas de reportes",
    status: "IN_PROGRESS",
    priority: "HIGH",
    project: "Sistema de Reportes",
  },
  {
    title: "Crear plantilla de estado semanal",
    status: "TODO",
    priority: "MEDIUM",
    project: "Launch Portal Refresh",
  },
];

const statusStyles: Record<string, string> = {
  TODO: "bg-slate-700 text-slate-100",
  IN_PROGRESS: "bg-amber-600 text-amber-100",
  DONE: "bg-emerald-600 text-emerald-100",
};

const priorityStyles: Record<string, string> = {
  LOW: "bg-slate-700 text-slate-100",
  MEDIUM: "bg-blue-600 text-blue-100",
  HIGH: "bg-rose-600 text-rose-100",
};

export function AdminDashboard() {
  return (
    <div className="h-full bg-slate-950 text-slate-100">
      <div className="mx-auto flex min-h-screen max-w-400 flex-col gap-6 px-4 py-6 sm:px-6 lg:px-8">
        <header className="flex flex-col gap-4 rounded-3xl border border-white/10 bg-slate-900/80 p-5 shadow-[0_25px_60px_-30px_rgba(15,23,42,0.95)] backdrop-blur-sm sm:flex-row sm:items-center sm:justify-between">
          <div className="flex items-center gap-4">
            <div className="flex h-12 w-12 items-center justify-center rounded-2xl bg-linear-to-br from-sky-500 to-indigo-500 text-white shadow-lg shadow-sky-500/20">
              <ShieldCheck className="h-6 w-6" />
            </div>
            <div>
              <p className="text-sm uppercase tracking-[0.3em] text-slate-400">TaskFlow Admin</p>
              <h1 className="text-2xl font-semibold text-white">Bienvenido, Adrián</h1>
            </div>
          </div>

          <div className="flex flex-wrap items-center gap-3 sm:gap-4">
            <span className="rounded-full bg-emerald-600/15 px-3 py-1.5 text-sm font-semibold uppercase tracking-[0.18em] text-emerald-300">
              ADMIN
            </span>
            <div className="flex items-center gap-3 rounded-2xl border border-white/10 bg-slate-950/80 px-3 py-2 shadow-sm">
              <div className="flex h-11 w-11 items-center justify-center rounded-2xl bg-slate-800 text-slate-200">
                <span className="text-xl">AA</span>
              </div>
              <div className="min-w-30">
                <p className="text-sm text-slate-400">Adrián Castillo</p>
                <p className="text-xs text-slate-500">Administrador</p>
              </div>
            </div>
            <button className="inline-flex items-center gap-2 rounded-2xl bg-slate-800 px-4 py-2 text-sm font-medium text-slate-100 transition hover:bg-slate-700">
              <LogOut className="h-4 w-4" />
              Logout
            </button>
          </div>
        </header>

        <div className="grid grid-cols-1 gap-6 xl:grid-cols-[260px_minmax(0,1fr)]">
          <aside className="space-y-6 rounded-3xl border border-white/10 bg-slate-900/80 p-5 shadow-[0_20px_50px_-30px_rgba(15,23,42,0.95)]">
            <div className="mb-4 flex items-center gap-3 border-b border-white/10 pb-4 text-slate-300">
              <Home className="h-5 w-5 text-sky-400" />
              <span className="text-sm uppercase tracking-[0.2em] text-slate-400">Navegación</span>
            </div>
            <nav className="space-y-2">
              {[
                { label: "Dashboard", icon: Home, active: true },
                { label: "Projects", icon: Layers },
                { label: "Tasks", icon: ClipboardList },
                { label: "Settings", icon: Settings },
              ].map((item) => (
                <button
                  key={item.label}
                  className={`flex w-full items-center gap-3 rounded-2xl px-4 py-3 text-left text-sm font-medium transition ${
                    item.active
                      ? "bg-slate-800 text-slate-50 shadow-sm shadow-slate-900/40"
                      : "text-slate-400 hover:bg-slate-800 hover:text-slate-100"
                  }`}
                >
                  <item.icon className="h-5 w-5" />
                  {item.label}
                </button>
              ))}
            </nav>
          </aside>

          <main className="space-y-6">
            <section className="grid gap-4 md:grid-cols-2 xl:grid-cols-4">
              {metricCards.map((card) => {
                const Icon = card.icon;
                return (
                  <div
                    key={card.label}
                    className="rounded-3xl border border-white/10 bg-linear-to-br from-slate-900 to-slate-950 p-5 shadow-[0_18px_50px_-30px_rgba(15,23,42,0.9)]"
                  >
                    <div className={`mb-5 inline-flex h-12 w-12 items-center justify-center rounded-2xl bg-linear-to-br ${card.accent} text-white shadow-lg shadow-slate-900/30`}>
                      <Icon className="h-6 w-6" />
                    </div>
                    <p className="text-sm uppercase tracking-[0.24em] text-slate-400">{card.label}</p>
                    <p className="mt-3 text-3xl font-semibold text-white">{card.value}</p>
                  </div>
                );
              })}
            </section>

            <section className="space-y-4 rounded-3xl border border-white/10 bg-slate-900/80 p-5 shadow-[0_18px_50px_-30px_rgba(15,23,42,0.9)]">
              <div className="flex flex-col gap-4 sm:flex-row sm:items-center sm:justify-between">
                <div>
                  <p className="text-sm uppercase tracking-[0.24em] text-slate-400">Projects</p>
                  <h2 className="mt-2 text-2xl font-semibold text-white">Project overview</h2>
                </div>
                <button className="inline-flex items-center gap-2 rounded-2xl bg-sky-500 px-4 py-2 text-sm font-semibold text-white transition hover:bg-sky-400">
                  <Plus className="h-4 w-4" />
                  Create Project
                </button>
              </div>

              <div className="overflow-hidden rounded-3xl border border-white/10 bg-slate-950">
                <table className="min-w-full border-separate border-spacing-0 text-left text-sm">
                  <thead className="bg-slate-900 text-slate-400">
                    <tr>
                      <th className="px-5 py-4">Project Name</th>
                      <th className="px-5 py-4">Description</th>
                      <th className="px-5 py-4">Tasks Count</th>
                      <th className="px-5 py-4 text-right">Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    {projects.map((project, index) => (
                      <tr
                        key={project.name}
                        className={index % 2 === 0 ? "bg-slate-950" : "bg-slate-900"}
                      >
                        <td className="whitespace-nowrap px-5 py-4 font-semibold text-white">{project.name}</td>
                        <td className="px-5 py-4 text-slate-400">{project.description}</td>
                        <td className="px-5 py-4 text-slate-200">{project.tasks}</td>
                        <td className="px-5 py-4 text-right">
                          <div className="inline-flex items-center gap-2">
                            <button className="inline-flex items-center gap-2 rounded-2xl bg-slate-800 px-3 py-2 text-xs font-medium text-slate-100 transition hover:bg-slate-700">
                              <Edit3 className="h-3.5 w-3.5" />
                              Edit
                            </button>
                            <button className="inline-flex items-center gap-2 rounded-2xl bg-rose-600/10 px-3 py-2 text-xs font-medium text-rose-200 transition hover:bg-rose-600/20">
                              <Trash2 className="h-3.5 w-3.5" />
                              Delete
                            </button>
                          </div>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </section>

            <section className="space-y-4 rounded-3xl border border-white/10 bg-slate-900/80 p-5 shadow-[0_18px_50px_-30px_rgba(15,23,42,0.9)]">
              <div>
                <p className="text-sm uppercase tracking-[0.24em] text-slate-400">Recent Tasks</p>
                <h2 className="mt-2 text-2xl font-semibold text-white">Actividad reciente</h2>
              </div>

              <div className="overflow-hidden rounded-3xl border border-white/10 bg-slate-950">
                <table className="min-w-full border-separate border-spacing-0 text-left text-sm">
                  <thead className="bg-slate-900 text-slate-400">
                    <tr>
                      <th className="px-5 py-4">Title</th>
                      <th className="px-5 py-4">Status</th>
                      <th className="px-5 py-4">Priority</th>
                      <th className="px-5 py-4">Project</th>
                    </tr>
                  </thead>
                  <tbody>
                    {recentTasks.map((task, index) => (
                      <tr
                        key={`${task.title}-${index}`}
                        className={index % 2 === 0 ? "bg-slate-950" : "bg-slate-900"}
                      >
                        <td className="px-5 py-4 font-medium text-white">{task.title}</td>
                        <td className="px-5 py-4">
                          <span className={`inline-flex rounded-full px-3 py-1 text-xs font-semibold uppercase tracking-[0.18em] ${statusStyles[task.status]}`}>
                            {task.status.replace("_", " ")}
                          </span>
                        </td>
                        <td className="px-5 py-4">
                          <span className={`inline-flex rounded-full px-3 py-1 text-xs font-semibold uppercase tracking-[0.18em] ${priorityStyles[task.priority]}`}>
                            {task.priority}
                          </span>
                        </td>
                        <td className="px-5 py-4 text-slate-400">{task.project}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </section>
          </main>
        </div>
      </div>
    </div>
  );
}
