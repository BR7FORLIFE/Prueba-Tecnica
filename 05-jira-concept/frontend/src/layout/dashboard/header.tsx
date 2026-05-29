import { Title } from "../../components/labels";

const ButtonHeader = ({ content }: { content: string }) => (
  <button className="px-4 py-2 text-sm font-sans text-black rounded-lg transition-colors hover:bg-black hover:text-white duration-200 ease-in-out">
    {content}
  </button>
);

function AdminOptions() {
  return (
    <ul className="inline-flex gap-3">
      <ButtonHeader content="Projects" />
      <ButtonHeader content="Task" />
    </ul>
  );
}

export function Header({ id, role }: { id: string; role: string }) {
  return (
    <header className="w-full h-12 inline-flex justify-evenly items-center border-b border-black">
      <Title content="Task Flow Dashboard" />
      <div>{role === "ADMIN" && <AdminOptions /> }</div>
      <span>{id}</span>
    </header>
  );
}
