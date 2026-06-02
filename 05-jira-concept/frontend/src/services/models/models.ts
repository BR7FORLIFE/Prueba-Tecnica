export interface Register {
    name: string;
    email: string;
    password: string;
}

export interface RegisterResponse {
    id: string;
    role: string;
    message: string;
}

export interface Login {
    email: string;
    password: string;
}

export interface LoginResponse {
    userId: string;
    message: string;
}

export interface ApiError {
    path: string;
    message: string;
    statusCode: number;
    time: Date;
}
    