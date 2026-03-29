import {
    createContext,
    useCallback,
    useContext,
    useEffect,
    useMemo,
    useState,
} from "react";
import { getCurrentUser, type CurrentUser } from "../services/user.service";
import { getToken, removeToken } from "../utils/token";

interface AuthContextType {
    user: CurrentUser | null;
    isAuthenticated: boolean;
    isLoading: boolean;
    refreshUser: () => Promise<void>;
    logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: React.ReactNode }) {
    const [user, setUser] = useState<CurrentUser | null>(null);
    const [isLoading, setIsLoading] = useState(true);

    const refreshUser = useCallback(async () => {
        try {
            const currentUser = await getCurrentUser();
            setUser(currentUser);
        } catch (error) {
            removeToken();
            setUser(null);
        } finally {
            setIsLoading(false);
        }
    }, []);

    const logout = useCallback(() => {
        removeToken();
        setUser(null);
    }, []);

    useEffect(() => {
        const token = getToken();

        if (!token) {
            setIsLoading(false);
            return;
        }

        refreshUser();
    }, [refreshUser]);

    const value = useMemo(
        () => ({
            user,
            isAuthenticated: !!user,
            isLoading,
            refreshUser,
            logout,
        }),
        [user, isLoading, refreshUser, logout]
    );

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
    const context = useContext(AuthContext);

    if (!context) {
        throw new Error("useAuth must be used inside AuthProvider");
    }

    return context;
}