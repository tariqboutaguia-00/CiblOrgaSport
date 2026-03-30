import React, { useEffect, useState } from 'react';

import Navigation from '../components/navigation';
import Footer from '../components/footer';
import { getPublicSchedule } from '../services/public-schedule.service';
import './home.css';

const BACK_OFFICE_LOGIN_URL = 'https://cibl-orga-sport-5iyd.vercel.app/login';

function formatValue(value) {
  if (value === null || value === undefined || value === '') {
    return '-';
  }

  if (typeof value === 'string') {
    const trimmed = value.trim();

    if (!trimmed) {
      return '-';
    }

    const parsedDate = new Date(trimmed);
    if (!Number.isNaN(parsedDate.getTime()) && trimmed.includes('-')) {
      return parsedDate.toLocaleString('fr-FR');
    }

    return trimmed;
  }

  if (typeof value === 'number' || typeof value === 'boolean') {
    return String(value);
  }

  if (Array.isArray(value)) {
    return value.length > 0 ? `${value.length} élément(s)` : '-';
  }

  if (typeof value === 'object') {
    if ('name' in value && typeof value.name === 'string') {
      return value.name;
    }

    if ('title' in value && typeof value.title === 'string') {
      return value.title;
    }

    if ('location' in value && typeof value.location === 'string') {
      return value.location;
    }

    if ('id' in value && value.id !== null && value.id !== undefined) {
      return `#${String(value.id)}`;
    }

    return 'Objet lié';
  }

  return String(value);
}

function getCardTitle(item) {
  return (
    item.name ||
    item.title ||
    item.eventName ||
    item.competitionName ||
    'Épreuve publique'
  );
}

function getCardLocation(item) {
  return (
    item.location ||
    item.place ||
    item.venue ||
    item.pool ||
    'Lieu non renseigné'
  );
}

function getCardDate(item) {
  return (
    formatValue(item.startDate) ||
    formatValue(item.date) ||
    formatValue(item.time) ||
    '-'
  );
}

const Home = () => {
  const [publicSchedule, setPublicSchedule] = useState([]);
  const [isLoadingSchedule, setIsLoadingSchedule] = useState(true);
  const [scheduleError, setScheduleError] = useState('');

  useEffect(() => {
    const fetchPublicSchedule = async () => {
      try {
        setIsLoadingSchedule(true);
        setScheduleError('');

        const data = await getPublicSchedule();
        setPublicSchedule(Array.isArray(data) ? data : []);
      } catch (error) {
        setScheduleError(
          'Impossible de charger les horaires publics pour le moment.'
        );
      } finally {
        setIsLoadingSchedule(false);
      }
    };

    fetchPublicSchedule();
  }, []);

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
              <h2>Programme public des épreuves</h2>
              <p className="section-description">
                Cette section affiche les horaires et lieux accessibles au public
                depuis le backend.
              </p>
            </div>

            {isLoadingSchedule && (
              <div className="status-card">
                Chargement des horaires publics...
              </div>
            )}

            {!isLoadingSchedule && scheduleError && (
              <div className="status-card status-card-error">
                {scheduleError}
              </div>
            )}

            {!isLoadingSchedule &&
              !scheduleError &&
              publicSchedule.length === 0 && (
                <div className="status-card">
                  Aucun horaire public n’est disponible pour le moment.
                </div>
              )}

            {!isLoadingSchedule &&
              !scheduleError &&
              publicSchedule.length > 0 && (
                <div className="schedule-grid">
                  {publicSchedule.map((item, index) => (
                    <article
                      className="schedule-card"
                      key={String(item.id || index)}
                    >
                      <p className="schedule-date">{getCardDate(item)}</p>
                      <h3>{getCardTitle(item)}</h3>
                      <p>{getCardLocation(item)}</p>
                      <span>
                        {item.status
                          ? formatValue(item.status)
                          : item.time
                            ? formatValue(item.time)
                            : 'Programme public'}
                      </span>
                    </article>
                  ))}
                </div>
              )}
          </div>
        </section>

        <section className="section section-alt" id="notifications">
          <div className="container subscribe-grid">
            <div>
              <p className="section-tag">Notifications</p>
              <h2>Recevoir les informations importantes</h2>
              <p className="section-description">
                Les abonnements aux notifications sont liés à un compte
                utilisateur. Pour recevoir les alertes sportives, sécurité ou fan
                zone, connectez-vous à l’espace de gestion.
              </p>
            </div>

            <div className="subscribe-panel">
              <div className="subscribe-info-list">
                <div className="subscribe-info-item">
                  <span className="subscribe-info-title">Types disponibles</span>
                  <span className="subscribe-info-value">
                    Sport, Sécurité, Fan zone
                  </span>
                </div>

                <div className="subscribe-info-item">
                  <span className="subscribe-info-title">Accès</span>
                  <span className="subscribe-info-value">
                    Compte utilisateur requis
                  </span>
                </div>

                <div className="subscribe-info-item">
                  <span className="subscribe-info-title">Gestion</span>
                  <span className="subscribe-info-value">
                    Depuis le back-office
                  </span>
                </div>
              </div>

              <a
                href={BACK_OFFICE_LOGIN_URL}
                className="btn btn-primary subscribe-cta"
                target="_blank"
                rel="noreferrer"
              >
                Se connecter pour s’abonner
              </a>
            </div>
          </div>
        </section>
      </main>

      <Footer />
    </div>
  );
};

export default Home;