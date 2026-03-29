import React from "react";

export default function AuthLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="min-h-screen bg-gray-100 dark:bg-gray-950">
      <div className="grid min-h-screen lg:grid-cols-2">
        <div className="flex items-center justify-center px-6 py-10">
          {children}
        </div>

        <div className="hidden lg:flex items-center justify-center bg-brand-950 px-10 text-white">
          <div className="max-w-md">
            <p className="mb-4 inline-block rounded-full bg-white/10 px-4 py-2 text-sm">
              CiblOrgaSport
            </p>
            <h2 className="mb-4 text-3xl font-bold">
              Interface de gestion des compétitions sportives
            </h2>
            <p className="text-white/80">
              Cette application permet de gérer les compétitions, les épreuves,
              les participants, les résultats, les missions volontaires et les
              notifications depuis un back-office simple et professionnel.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}