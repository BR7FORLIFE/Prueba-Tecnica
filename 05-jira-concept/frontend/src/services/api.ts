import { api } from "../config/axios";
import type {
    Register,
    RegisterResponse,
    Login,
    LoginResponse,
} from "./models/models";
import { API_PATHS } from "../config/axios";

export async function registerApi(data: Register) {
    const { data: dataFetch } = await api.post<RegisterResponse>(
        API_PATHS.AUTH.REGISTER,
        data,
    );
    console.log(dataFetch);
    return dataFetch;
}

export async function loginApi(data: Login) {
    const { data: dataFetch } = await api.post<LoginResponse>(
        API_PATHS.AUTH.LOGIN,
        data,
    );

    return dataFetch;
}
