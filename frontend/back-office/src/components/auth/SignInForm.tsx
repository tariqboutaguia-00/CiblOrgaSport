import { useState } from "react";
import { useNavigate } from "react-router";
import { login } from "../../services/auth.service";
import { setToken } from "../../utils/token";

export default function SignInForm() {
  const [email, setEmail] = useState("admin@ciblorgasport.com");
  const [password, setPassword] = useState("admin123");
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    setError("");

    try {
      const response = await login({ email, password });

      // stocker le token
      setToken(response.token);

      // redirection vers dashboard
      navigate("/");
    } catch (err: any) {
      setError("Email ou mot de passe incorrect");
    }
  };

  return (
    <div className="w-full max-w-md rounded-2xl border border-gray-200 bg-white p-8 shadow-sm dark:border-gray-800 dark:bg-gray-900">
      <div className="mb-8">
        <p className="mb-2 text-sm font-medium text-brand-600">Back-office</p>
        <h1 className="text-2xl font-bold text-gray-800 dark:text-white">
          Connexion
        </h1>
      </div>

      {error && (
        <div className="mb-4 rounded-lg bg-red-100 p-3 text-sm text-red-600">
          {error}
        </div>
      )}

      <form onSubmit={handleSubmit} className="space-y-5">
        <div>
          <label className="mb-2 block text-sm font-medium text-gray-700">
            Email
          </label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="w-full rounded-xl border px-4 py-3"
          />
        </div>

        <div>
          <label className="mb-2 block text-sm font-medium text-gray-700">
            Mot de passe
          </label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="w-full rounded-xl border px-4 py-3"
          />
        </div>

        <button
          type="submit"
          className="w-full rounded-xl bg-brand-500 px-4 py-3 text-white"
        >
          Se connecter
        </button>
      </form>
    </div>
  );
}