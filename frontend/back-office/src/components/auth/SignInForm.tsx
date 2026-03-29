import { useState } from "react";
import { Link } from "react-router";

export default function SignInForm() {
  const [email, setEmail] = useState("admin@ciblorgasport.com");
  const [password, setPassword] = useState("admin123");

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    alert(
      "Étape de nettoyage terminée. La vraie connexion JWT sera branchée à l’étape suivante."
    );
  };

  return (
    <div className="w-full max-w-md rounded-2xl border border-gray-200 bg-white p-8 shadow-sm dark:border-gray-800 dark:bg-gray-900">
      <div className="mb-8">
        <p className="mb-2 text-sm font-medium text-brand-600">Back-office</p>
        <h1 className="text-2xl font-bold text-gray-800 dark:text-white">
          Connexion
        </h1>
        <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
          Base propre avant intégration de l’authentification JWT.
        </p>
      </div>

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
          className="w-full rounded-xl bg-brand-500 px-4 py-3 text-sm font-semibold text-white hover:bg-brand-600"
        >
          Se connecter
        </button>
      </form>

      <div className="mt-6 rounded-xl bg-gray-50 p-4 text-sm text-gray-600 dark:bg-gray-800 dark:text-gray-300">
        <p className="font-medium">Compte de test backend</p>
        <p>Email : admin@ciblorgasport.com</p>
        <p>Mot de passe : admin123</p>
      </div>

      <div className="mt-6 text-center">
        <Link
          to="/"
          className="text-sm font-medium text-brand-600 hover:underline"
        >
          Retour au dashboard de base
        </Link>
      </div>
    </div>
  );
}