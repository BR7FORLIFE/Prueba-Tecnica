import { create } from "zustand";

type ROLE = 'ADMIN' | 'USER'

interface AuthInfo {
    id: string | null;
    accessToken: string | null;
    role: ROLE | null;
    setId: (id: string) => void;
    setAccessToken: (accessToken: string) => void;
    setRole: (role: string) => void;
    logout: () => void;
}

export const useAuthStore = create<AuthInfo>((set) => ({
    id: null,
    accessToken: null,
    role: null,

    setId: (id: string) => {
        set({
            id,
        });
    },

    setAccessToken: (accessToken: string) => {
        set({
            accessToken,
        });
    },

    setRole(role: ROLE) {
        set({
            role,
        });
    },

    logout: () => {},
}));
