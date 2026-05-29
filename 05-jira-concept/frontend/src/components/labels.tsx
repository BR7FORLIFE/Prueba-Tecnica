import { Label } from "@/components/ui/label";

export function Title({ content }: { content: string }) {
  return <Label className="text-lg font-semibold font-sans" >{content}</Label>;
}
