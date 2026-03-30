import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import './navigation.css';

const BACK_OFFICE_LOGIN_URL = 'https://cibl-orga-sport-5iyd.vercel.app/login';

const Navigation = () => {
  const [isOpen, setIsOpen] = useState(false);

  const closeMenu = () => {
    setIsOpen(false);
  };

  return (
    <header className="site-header">
      <div className="container nav-wrapper">
        <Link to="/" className="brand">
          <span className="brand-badge">C</span>
          <span className="brand-name">CiblOrgaSport</span>
        </Link>

        <nav className={`nav-menu ${isOpen ? 'nav-menu-open' : ''}`}>
          <a href="#accueil" onClick={closeMenu}>Accueil</a>
          <a href="#apropos" onClick={closeMenu}>À propos</a>
          <a href="#fonctionnalites" onClick={closeMenu}>Fonctionnalités</a>
          <a href="#horaires" onClick={closeMenu}>Horaires publics</a>
          <a href="#notifications" onClick={closeMenu}>Notifications</a>
        </nav>

        <div className="nav-actions">
          <a
            href={BACK_OFFICE_LOGIN_URL}
            className="btn btn-secondary"
            target="_blank"
            rel="noreferrer"
          >
            Connexion
          </a>
        </div>

        <button
          type="button"
          className="mobile-toggle"
          onClick={() => setIsOpen(!isOpen)}
          aria-label="Ouvrir le menu"
        >
          ☰
        </button>
      </div>
    </header>
  );
};

export default Navigation;