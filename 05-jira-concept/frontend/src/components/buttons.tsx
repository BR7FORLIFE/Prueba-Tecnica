import { Button } from "@/components/ui/button";

export function ButtonForm({
  content,
  fn,
}: {
  content: string;
  fn: (e: React.MouseEvent<HTMLButtonElement>) => void;
}) {
  return (
    <div className="flex flex-wrap items-center gap-2 md:flex-row">
      <Button
        variant="outline"
        aria-label="Submit"
        className="bg-black text-white p-5"
        onClick={(e) => fn(e)}
      >
        {content}
      </Button>
    </div>
  );
}
