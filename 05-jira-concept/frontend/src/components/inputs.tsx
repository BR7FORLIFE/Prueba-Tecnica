import { Field, FieldLabel, FieldDescription } from "@/components/ui/field";
import { Input } from "@/components/ui/input";
import type { ChangeEvent } from "react";

export function InputForm({
  id,
  label,
  description,
  type,
  fn
}: {
  id: string;
  label: string;
  description: string;
  type: 'text' | 'email' | 'password'
  fn: (e: ChangeEvent<HTMLInputElement>) => void
}) {
  return (
    <Field>
      <FieldLabel htmlFor={id}>{label}</FieldLabel>
      <Input
        id={id}
        type={type} 
        placeholder="Enter your username"
        onChange={fn}
      />
      <FieldDescription>{description}</FieldDescription>
    </Field>
  );
}
