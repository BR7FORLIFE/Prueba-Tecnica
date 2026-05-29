export interface Register {
    name: string;
    email: string;
    password: string;
}

export interface RegisterResponse {
    id: string;
    accessToken: string;
}

export interface Login {
    email: string;
    password: string;
}

export interface LoginResponse {
    accessToken: string;
}
