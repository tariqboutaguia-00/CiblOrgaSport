import React from 'react';
import './footer.css';

const Footer = () => {
  return (
    <footer className="site-footer" id="contact">
      <div className="container footer-grid">
        <div>
          <h3>CiblOrgaSport</h3>
          <p>
            Plateforme de gestion d’événements sportifs de natation.
          </p>
        </div>

        <div>
          <h4>Navigation</h4>
          <ul className="footer-list">
            <li><a href="#accueil">Accueil</a></li>
            <li><a href="#apropos">À propos</a></li>
            <li><a href="#fonctionnalites">Fonctionnalités</a></li>
            <li><a href="#horaires">Horaires publics</a></li>
          </ul>
        </div>

        <div>
          <h4>Contact</h4>
          <ul className="footer-list">
            <li>contact@ciblorgasport.com</li>
            <li>+33 3 20 00 00 00</li>
            <li>Lille, France</li>
          </ul>
        </div>
      </div>

      <div className="footer-bottom">
        <div className="container">
          <p>© 2026 CiblOrgaSport - Tous droits réservés.</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;