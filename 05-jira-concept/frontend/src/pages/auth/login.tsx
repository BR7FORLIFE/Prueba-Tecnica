import { useState } from "react";
import { ButtonForm } from "../../components/buttons";
import { InputForm } from "../../components/inputs";
import { useAuthStore } from "../../stores/user-store";
import type { ApiError, Login } from "../../services/models/models";
import { useMutation } from "@tanstack/react-query";
import { loginApi } from "../../services/api";
import { Link, useNavigate } from "react-router";
import type { AxiosError } from "axios";

export function Login() {
  const userAuth = useAuthStore();
  const navigate = useNavigate();

  const [login, setLogin] = useState<Login>({
    email: "",
    password: "",
  });

  const [error, SetError] = useState<string>("")

  const mutation = useMutation({
    mutationFn: loginApi,
    onSuccess: (data) =>{
      userAuth.setId(data.userId)
      navigate("/dashboard")
    },
    onError: (error: AxiosError<ApiError>) => {
      SetError(error.response.data.message)
    }
  });

  const obtainInfo = (e: React.ChangeEvent<HTMLInputElement>) => {
    const id = e.target.id;
    const value = e.target.value;

    setLogin((prev) => ({ ...prev, [id]: value }));
  };

  const sendInfo = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    mutation.mutate(login); //hacemos la mutacion llamando a la api y enviando los datos
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
        {error && <p className="text-neutral-600 text-sm">{error}</p>}
      </form>
      <Link to='/auth/register' replace className="text-neutral-700/70 text-sm">Not Have Account? Register Here</Link>
    </section>
  );
}
