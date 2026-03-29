import React from 'react';
import { Link } from 'react-router-dom';
import './not-found.css';

const NotFound = () => {
  return (
    <div className="not-found-page">
      <div className="not-found-card">
        <p className="not-found-code">404</p>
        <h1>Page introuvable</h1>
        <p>
          La page demandée n’existe pas ou a été déplacée.
        </p>
        <Link to="/" className="not-found-button">
          Retour à l’accueil
        </Link>
      </div>
    </div>
  );
};

export default NotFound;