import { api } from "../config/axios";
import type {
    Register,
    RegisterResponse,
    Login,
    LoginResponse,
} from "./models/models";
import { API_PATHS } from "../config/axios";

export async function registerApi(data: Register) {
    try {
        const { data: dataFetch } = await api.post<RegisterResponse>(
            API_PATHS.AUTH.REGISTER,
            data,
        );

        return dataFetch;
    } catch {
        throw new Error("error to apply the register");
    }
}

export async function loginApi(data: Login) {
    try {
        const { data: dataFetch } = await api.post<LoginResponse>(
            API_PATHS.AUTH.LOGIN,
            data,
        );

        return dataFetch;
    } catch {
        throw new Error("error to apply the login");
    }
}
