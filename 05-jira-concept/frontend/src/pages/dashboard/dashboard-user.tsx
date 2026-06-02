import { Activity, CheckCircle2, ClipboardList, Layers, User, Zap, Home } from "lucide-react";

const metrics = [
  {
    label: "My Tasks",
    value: 28,
    icon: ClipboardList,
    accent: "from-sky-500 to-indigo-500",
  },
  {
    label: "Pending Tasks",
    value: 14,
    icon: Activity,
    accent: "from-amber-500 to-orange-500",
  },
  {
    label: "In Progress",
    value: 8,
    icon: Zap,
    accent: "from-emerald-500 to-teal-500",
  },
  {
    label: "Completed",
    value: 6,
    icon: CheckCircle2,
    accent: "from-violet-500 to-fuchsia-500",
  },
];

const kanbanColumns = [
  {
    status: "TODO",
    title: "To Do",
    tasks: [
      {
        title: "Refinar especificaciones de diseño",
        description: "Ajustar las cards del tablero y revisar comentarios de UX.",
        priority: "HIGH",
        project: "Portal Marketing",
      },
      {
        title: "Crear esquema de datos para tareas",
        description: "Definir campos clave y relaciones entre proyectos y tareas.",
        priority: "MEDIUM",
        project: "TaskFlow Core",
      },
      {
        title: "Validar primer flujo de onboarding",
        description: "Revisar pasos y mensajes de bienvenida para nuevos usuarios.",
        priority: "LOW",
        project: "User Experience",
      },
    ],
  },
  {
    status: "IN_PROGRESS",
    title: "In Progress",
    tasks: [
      {
        title: "Implementar filtros del dashboard",
        description: "Agregar búsqueda por estado y prioridad en la vista principal.",
        priority: "MEDIUM",
        project: "Portal Marketing",
      },
      {
        title: "Ajustar diseño mobile",
        description: "Optimizar el tablero para pantallas pequeñas y tablets.",
        priority: "HIGH",
        project: "TaskFlow Core",
      },
      {
        title: "Configurar notificaciones internas",
        description: "Crear notificaciones para tareas asignadas y cambios de estado.",
        priority: "MEDIUM",
        project: "User Experience",
      },
    ],
  },
  {
    status: "DONE",
    title: "Done",
    tasks: [
      {
        title: "Revisar roadmap trimestral",
        description: "Actualizar prioridades y definir próximos milestones.",
        priority: "LOW",
        project: "TaskFlow Core",
      },
      {
        title: "Establecer etiquetas de prioridad",
        description: "Definir colores y niveles para la gestión rápida de tareas.",
        priority: "LOW",
        project: "Portal Marketing",
      },
    ],
  },
];

const priorityStyles: Record<string, string> = {
  LOW: "bg-slate-700 text-slate-100",
  MEDIUM: "bg-sky-600 text-sky-100",
  HIGH: "bg-rose-600 text-rose-100",
};

export function UserDashboard() {
  return (
    <div className="h-full bg-slate-950 text-slate-100">
      <div className="mx-auto flex min-h-screen max-w-400 flex-col gap-6 px-4 py-6 sm:px-6 lg:px-8">
        <header className="flex flex-col gap-5 rounded-3xl border border-white/10 bg-slate-900/80 p-5 shadow-[0_25px_60px_-30px_rgba(15,23,42,0.95)] backdrop-blur-sm sm:flex-row sm:items-center sm:justify-between">
          <div className="flex flex-col gap-3 sm:flex-row sm:items-center">
            <div className="flex h-14 w-14 items-center justify-center rounded-3xl bg-linear-to-br from-sky-500 to-indigo-500 text-white shadow-lg shadow-sky-500/25">
              <User className="h-7 w-7" />
            </div>
            <div>
              <p className="text-sm uppercase tracking-[0.3em] text-slate-400">Member Dashboard</p>
              <h1 className="text-2xl font-semibold text-white">Hola, Valeria</h1>
            </div>
          </div>

          <div className="flex flex-wrap items-center gap-3">
            <span className="rounded-full bg-slate-800/80 px-3 py-1.5 text-sm font-semibold uppercase tracking-[0.18em] text-slate-100">
              MEMBER
            </span>
            <div className="flex h-11 w-11 items-center justify-center rounded-2xl bg-slate-800 text-slate-100 shadow-sm">
              <span className="text-base font-semibold">V</span>
            </div>
          </div>
        </header>

        <div className="grid grid-cols-1 gap-6 xl:grid-cols-[220px_minmax(0,1fr)]">
          <aside className="space-y-6 rounded-3xl border border-white/10 bg-slate-900/80 p-5 shadow-[0_20px_50px_-30px_rgba(15,23,42,0.95)]">
            <div className="mb-4 flex items-center gap-3 border-b border-white/10 pb-4 text-slate-300">
              <Layers className="h-5 w-5 text-sky-400" />
              <span className="text-sm uppercase tracking-[0.2em] text-slate-400">Menu</span>
            </div>
            <nav className="space-y-2">
              {[
                { label: "Dashboard", icon: Home, active: true },
                { label: "My Tasks", icon: ClipboardList },
                { label: "Projects", icon: Layers },
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
              {metrics.map((metric) => {
                const Icon = metric.icon;
                return (
                  <div key={metric.label} className="rounded-3xl border border-white/10 bg-slate-900/80 p-5 shadow-[0_18px_50px_-30px_rgba(15,23,42,0.9)]">
                    <div className={`mb-4 inline-flex h-12 w-12 items-center justify-center rounded-2xl bg-linear-to-br ${metric.accent} text-white shadow-lg shadow-slate-900/30`}>
                      <Icon className="h-6 w-6" />
                    </div>
                    <p className="text-sm uppercase tracking-[0.24em] text-slate-400">{metric.label}</p>
                    <p className="mt-3 text-3xl font-semibold text-white">{metric.value}</p>
                  </div>
                );
              })}
            </section>

            <section className="rounded-3xl border border-white/10 bg-slate-900/80 p-5 shadow-[0_18px_50px_-30px_rgba(15,23,42,0.9)]">
              <div className="mb-6 flex flex-col gap-3 sm:flex-row sm:items-end sm:justify-between">
                <div>
                  <p className="text-sm uppercase tracking-[0.24em] text-slate-400">Kanban board</p>
                  <h2 className="mt-2 text-2xl font-semibold text-white">Tu flujo de trabajo</h2>
                </div>
                <div className="rounded-3xl bg-slate-950 p-3 text-sm text-slate-400 shadow-inner shadow-slate-900/20">
                  Actualizado hace 2 minutos
                </div>
              </div>

              <div className="space-y-4 lg:space-y-0 lg:grid lg:grid-cols-3 lg:gap-4">
                {kanbanColumns.map((column) => (
                  <div key={column.status} className="rounded-3xl bg-slate-950 p-4 shadow-[0_18px_50px_-30px_rgba(15,23,42,0.9)]">
                    <div className="mb-4 flex items-center justify-between">
                      <div>
                        <p className="text-sm uppercase tracking-[0.24em] text-slate-400">{column.title}</p>
                        <p className="text-lg font-semibold text-white">{column.tasks.length} items</p>
                      </div>
                      <span className="rounded-full bg-slate-800 px-3 py-1 text-xs uppercase tracking-[0.18em] text-slate-300">
                        {column.status.replace("_", " ")}
                      </span>
                    </div>
                    <div className="space-y-4">
                      {column.tasks.map((task) => (
                        <article key={task.title} className="rounded-3xl border border-white/10 bg-slate-900 p-4 shadow-sm transition hover:-translate-y-0.5 hover:bg-slate-800/90">
                          <div className="mb-3 flex items-start justify-between gap-3">
                            <div>
                              <h3 className="text-base font-semibold text-white">{task.title}</h3>
                              <p className="mt-2 text-sm leading-6 text-slate-400">{task.description}</p>
                            </div>
                            <span className={`inline-flex rounded-full px-3 py-1 text-xs font-semibold uppercase tracking-[0.18em] ${priorityStyles[task.priority]}`}>
                              {task.priority}
                            </span>
                          </div>
                          <div className="flex items-center gap-2 text-sm text-slate-400">
                            <Layers className="h-4 w-4 text-slate-400" />
                            <span>{task.project}</span>
                          </div>
                        </article>
                      ))}
                    </div>
                  </div>
                ))}
              </div>
            </section>
          </main>
        </div>
      </div>
    </div>
  );
}
