import React from 'react';

import Navigation from '../components/navigation';
import Footer from '../components/footer';
import './home.css';

const Home = () => {
  return (
    <div className="home-page">
      <Navigation />

      <main>
        <section className="hero-section" id="accueil">
          <div className="container hero-grid">
            <div className="hero-content">
              <p className="section-tag">Organisation sportive</p>
              <h1>
                La plateforme de référence pour l’organisation des compétitions
                sportives
              </h1>
              <p className="hero-text">
                CiblOrgaSport est une application dédiée à la gestion des
                compétitions de natation. Elle centralise les informations
                utiles pour les organisateurs, les athlètes, les volontaires
                et les spectateurs.
              </p>

              <div className="hero-actions">
                <a href="#horaires" className="btn btn-primary">
                  Voir les horaires
                </a>
                <a href="#apropos" className="btn btn-secondary">
                  Découvrir la plateforme
                </a>
              </div>
            </div>

            <div className="hero-panel">
              <h2>Vue d’ensemble</h2>
              <div className="hero-panel-grid">
                <div className="mini-card">
                  <span className="mini-card-number">12</span>
                  <span className="mini-card-label">Compétitions</span>
                </div>
                <div className="mini-card">
                  <span className="mini-card-number">48</span>
                  <span className="mini-card-label">Épreuves</span>
                </div>
                <div className="mini-card">
                  <span className="mini-card-number">320</span>
                  <span className="mini-card-label">Participants</span>
                </div>
                <div className="mini-card">
                  <span className="mini-card-number">85</span>
                  <span className="mini-card-label">Volontaires</span>
                </div>
              </div>
            </div>
          </div>
        </section>

        <section className="section" id="apropos">
          <div className="container">
            <div className="section-header">
              <p className="section-tag">À propos</p>
              <h2>Une plateforme pensée pour la gestion des événements sportifs</h2>
              <p className="section-description">
                CiblOrgaSport facilite l’organisation des compétitions en
                centralisant la gestion des compétitions, des épreuves, des
                participants, des résultats, des missions volontaires et des
                notifications.
              </p>
            </div>

            <div className="about-grid">
              <article className="info-card">
                <h3>Organisation centralisée</h3>
                <p>
                  Une seule interface pour suivre les compétitions, les épreuves
                  et la coordination générale.
                </p>
              </article>

              <article className="info-card">
                <h3>Suivi des acteurs</h3>
                <p>
                  Gestion des athlètes, des volontaires, des responsables et des
                  spectateurs selon les besoins.
                </p>
              </article>

              <article className="info-card">
                <h3>Information en temps réel</h3>
                <p>
                  Résultats, notifications et données utiles sont accessibles de
                  manière claire et structurée.
                </p>
              </article>
            </div>
          </div>
        </section>

        <section className="section section-alt" id="fonctionnalites">
          <div className="container">
            <div className="section-header">
              <p className="section-tag">Fonctionnalités</p>
              <h2>Les services proposés par CiblOrgaSport</h2>
            </div>

            <div className="features-grid">
              <article className="feature-card">
                <h3>Gestion des compétitions</h3>
                <p>Suivi simple et structuré des compétitions sportives.</p>
              </article>

              <article className="feature-card">
                <h3>Gestion des épreuves</h3>
                <p>Consultation des horaires, lieux et informations importantes.</p>
              </article>

              <article className="feature-card">
                <h3>Résultats sportifs</h3>
                <p>Affichage des performances et des résultats des participants.</p>
              </article>

              <article className="feature-card">
                <h3>Planning volontaire</h3>
                <p>Organisation des missions et suivi des affectations.</p>
              </article>

              <article className="feature-card">
                <h3>Notifications</h3>
                <p>Abonnement et réception d’informations ciblées selon le profil.</p>
              </article>

              <article className="feature-card">
                <h3>Horaires publics</h3>
                <p>Informations utiles pour les visiteurs et spectateurs.</p>
              </article>
            </div>
          </div>
        </section>

        <section className="section" id="horaires">
          <div className="container">
            <div className="section-header">
              <p className="section-tag">Horaires publics</p>
              <h2>Exemples d’épreuves accessibles au public</h2>
              <p className="section-description">
                Cette zone sera reliée au backend plus tard avec l’endpoint
                public des horaires.
              </p>
            </div>

            <div className="schedule-grid">
              <article className="schedule-card">
                <p className="schedule-date">10 avril 2026</p>
                <h3>100m nage libre</h3>
                <p>Piscine Olympique de Lille</p>
                <span>09:00</span>
              </article>

              <article className="schedule-card">
                <p className="schedule-date">10 avril 2026</p>
                <h3>200m brasse</h3>
                <p>Piscine Olympique de Lille</p>
                <span>11:00</span>
              </article>

              <article className="schedule-card">
                <p className="schedule-date">11 avril 2026</p>
                <h3>Relais 4x100</h3>
                <p>Centre Aquatique Métropolitain</p>
                <span>14:30</span>
              </article>
            </div>
          </div>
        </section>

        <section className="section section-alt" id="notifications">
          <div className="container subscribe-grid">
            <div>
              <p className="section-tag">Notifications</p>
              <h2>Restez informé des prochaines compétitions</h2>
              <p className="section-description">
                Les visiteurs pourront s’abonner pour recevoir les principales
                notifications sportives.
              </p>
            </div>

            <form className="subscribe-form">
              <div className="form-group">
                <label htmlFor="email">Adresse email</label>
                <input
                  id="email"
                  type="email"
                  placeholder="exemple@ciblorgasport.com"
                />
              </div>

              <div className="form-group">
                <label htmlFor="type">Type d’abonnement</label>
                <select id="type">
                  <option>Sport</option>
                  <option>Sécurité</option>
                  <option>Fan zone</option>
                </select>
              </div>

              <button type="button" className="btn btn-primary">
                S’abonner
              </button>
            </form>
          </div>
        </section>
      </main>

      <Footer />
    </div>
  );
};

export default Home;