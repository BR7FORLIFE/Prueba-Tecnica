import { useState } from "react";
import { ButtonForm } from "../../components/buttons";
import { InputForm } from "../../components/inputs";
import { useAuthStore } from "../../stores/user-store";
import type { Login } from "../../services/models/models";
import { useMutation } from "@tanstack/react-query";
import { loginApi } from "../../services/api";

export function Login() {
  const userAuth = useAuthStore();

  const [login, setLogin] = useState<Login>({
    email: "",
    password: "",
  });

  const mutation = useMutation({
    mutationFn: loginApi,
  });

  const obtainInfo = (e: React.ChangeEvent<HTMLInputElement>) => {
    const id = e.target.id;
    const value = e.target.value;

    setLogin((prev) => ({ ...prev, [id]: value }));
  };

  const sendInfo = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    mutation.mutate(login); //hacemos la mutacion llamando a la api y enviando los datos

    userAuth.setAccessToken(mutation.data.accessToken);
  };

  return (
    <section className="inline-flex flex-col justify-center items-center gap-4">
      <h1 className="text-2xl font-sans font-semibold">Task Flow Login</h1>
      <form className="flex flex-col gap-5 justify-center items-center">
        <InputForm
          id="email"
          label="email"
          description="insert the email for login in your account"
          type="email"
          fn={obtainInfo}
        />
        <InputForm
          id="password"
          label="password"
          description="insert the correct password!"
          type="password"
          fn={obtainInfo}
        />

        <ButtonForm content="login" fn={sendInfo} />
      </form>
    </section>
  );
}
