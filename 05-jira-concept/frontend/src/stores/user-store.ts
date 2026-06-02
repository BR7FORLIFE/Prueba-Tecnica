import { create } from "zustand";

type ROLE = "ADMIN" | "USER";

interface AuthInfo {
    id: string | null;
    role: ROLE | null;
    setId: (id: string) => void;
    setRole: (role: string) => void;
    logout: () => void;
}

export const useAuthStore = create<AuthInfo>((set) => ({
    id: null,
    role: null,

    setId: (id: string) => {
        set({
            id,
        });
    },

    setRole(role: ROLE) {
        set({
            role,
        });
    },

    logout: () => {},
}));
