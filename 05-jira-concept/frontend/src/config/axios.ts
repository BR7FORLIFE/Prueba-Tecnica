import axios from "axios";
import { API_BACKEND_URL } from "./env";

export const api = axios.create({
    baseURL: API_BACKEND_URL,
    headers: {
        "Content-Type": "application/json",
    },
    withCredentials: true,
});
export const API_PATHS = {
    AUTH: {
        REGISTER: "/auth/register",
        LOGIN: "/auth/login",
    },
};
