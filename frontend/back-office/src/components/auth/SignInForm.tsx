import { useState } from "react";
import { useNavigate } from "react-router";
import { login } from "../../services/auth.service";
import { setToken } from "../../utils/token";
import { useAuth } from "../../context/AuthContext";

export default function SignInForm() {
  const [email, setEmail] = useState("admin@ciblorgasport.com");
  const [password, setPassword] = useState("admin123");
  const [error, setError] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  const navigate = useNavigate();
  const { refreshUser } = useAuth();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setError("");
    setIsSubmitting(true);

    try {
      const response = await login({ email, password });
      setToken(response.token);
      await refreshUser();
      navigate("/");
    } catch (err) {
      setError("Email ou mot de passe incorrect.");
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="w-full max-w-md rounded-2xl border border-gray-200 bg-white p-8 shadow-sm dark:border-gray-800 dark:bg-gray-900">
      <div className="mb-8">
        <p className="mb-2 text-sm font-medium text-brand-600">Back-office</p>
        <h1 className="text-2xl font-bold text-gray-800 dark:text-white">
          Connexion
        </h1>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          Connectez-vous pour accéder à l’espace de gestion.
        </p>
      </div>

      {error && (
        <div className="mb-4 rounded-lg bg-red-100 p-3 text-sm text-red-700 dark:bg-red-500/10 dark:text-red-300">
          {error}
        </div>
      )}

      <form onSubmit={handleSubmit} className="space-y-5">
        <div>
          <label
            htmlFor="email"
            className="mb-2 block text-sm font-medium text-gray-700 dark:text-gray-300"
          >
            Email
          </label>
          <input
            id="email"
            type="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            className="w-full rounded-xl border border-gray-300 px-4 py-3 text-sm outline-none focus:border-brand-500 dark:border-gray-700 dark:bg-gray-800 dark:text-white"
            placeholder="admin@ciblorgasport.com"
          />
        </div>

        <div>
          <label
            htmlFor="password"
            className="mb-2 block text-sm font-medium text-gray-700 dark:text-gray-300"
          >
            Mot de passe
          </label>
          <input
            id="password"
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            className="w-full rounded-xl border border-gray-300 px-4 py-3 text-sm outline-none focus:border-brand-500 dark:border-gray-700 dark:bg-gray-800 dark:text-white"
            placeholder="Votre mot de passe"
          />
        </div>

        <button
          type="submit"
          disabled={isSubmitting}
          className="w-full rounded-xl bg-brand-500 px-4 py-3 text-sm font-semibold text-white hover:bg-brand-600 disabled:cursor-not-allowed disabled:opacity-70"
        >
          {isSubmitting ? "Connexion..." : "Se connecter"}
        </button>
      </form>

      <div className="mt-6 rounded-xl bg-gray-50 p-4 text-sm text-gray-600 dark:bg-gray-800 dark:text-gray-300">
        <p className="font-medium">Compte de test</p>
        <p>Email : admin@ciblorgasport.com</p>
        <p>Mot de passe : admin123</p>
      </div>
    </div>
  );
}