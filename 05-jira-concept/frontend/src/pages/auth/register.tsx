import { InputForm } from "../../components/inputs";
import { ButtonForm } from "../../components/buttons";
import { useState } from "react";
import { useMutation } from "@tanstack/react-query";
import type { Register } from "../../services/models/models";
import { registerApi } from "../../services/api";
import { useAuthStore } from "../../stores/user-store";
import { Loading } from "../../components/spinners";

//useMutation -> cuando quieres alterar el estado del backend ejemplo escrituras o peticiones de escrituras
// useQuery -> cuando quieres leer del backend ejemplo lecturas

export function Register() {
  const userAuth = useAuthStore();
  const [user, setUser] = useState<Register>({
    name: "",
    email: "",
    password: "",
  });

  const mutation = useMutation({
    mutationFn: registerApi,
  });

  const obtainInfoUser = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    const id = e.target.id;

    setUser((prev) => ({ ...prev, [id]: value }));
  };

  const sendRegister = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    mutation.mutate(user); //hacemos la mutacion llamando a la api y enviando los datos

    userAuth.setId(mutation.data.id);
    userAuth.setAccessToken(mutation.data.accessToken);
  };

  return (
    <section className="inline-flex flex-col justify-center items-center gap-4">
      <h1 className="text-2xl font-sans font-semibold">Task Flow Register</h1>
      <form className="flex flex-col gap-5 justify-center items-center">
        <InputForm
          id="name"
          label="name"
          description="choose the username"
          type="text"
          fn={obtainInfoUser}
        />

        <InputForm
          id="email"
          label="email"
          description="insert the email for login in your account"
          type="email"
          fn={obtainInfoUser}
        />
        <InputForm
          id="password"
          label="password"
          description="insert the correct password!"
          type="password"
          fn={obtainInfoUser}
        />

        <ButtonForm content="register" fn={sendRegister} />

        {mutation.isPending && <Loading data="loading..." />}
      </form>
    </section>
  );
}
