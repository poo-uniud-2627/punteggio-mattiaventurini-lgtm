// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
package pong;

import java.util.Random;

/** Una partita: due giocatori, due racchette, una palla, e i conti di chi vince. */
class Partita {
    /** Si gioca a 11, con almeno due punti di scarto (11 a 10 non basta, 12 a 10 sì). */
    static final int PUNTI_PER_VINCERE = 11;
    static final int SCARTO_MINIMO = 2;

    private final Campo campo;
    private final Giocatore giocatoreSinistra;
    private final Giocatore giocatoreDestra;
    private final Racchetta racchettaSinistra;
    private final Racchetta racchettaDestra;
    private final Palla palla;
    private boolean iniziata;
    private final Punteggio punteggio = new Punteggio(PUNTI_PER_VINCERE, SCARTO_MINIMO);

    Partita(Campo campo, Giocatore giocatoreSinistra, Giocatore giocatoreDestra) {
        this.campo = campo;
        this.giocatoreSinistra = giocatoreSinistra;
        this.giocatoreDestra = giocatoreDestra;
        double centroY = campo.getAltezza() / 2.0 - Racchetta.ALTEZZA / 2.0;
        this.racchettaSinistra = new Racchetta(30, centroY);
        this.racchettaDestra = new Racchetta(campo.getLarghezza() - 30 - Racchetta.LARGHEZZA, centroY);
        this.palla = new Palla(campo.getLarghezza() / 2.0, campo.getAltezza() / 2.0);
    }

    // Quello che lo Schermo può chiedere sui conti: solo leggere. Il Punteggio resta qui dentro.

    /** I punti del lato chi. */
    int getPunti(Lato chi) { return punteggio.getPunti(chi); }

    /** Chi ha vinto; null se la partita non è finita. */
    Lato getVincitore() { return punteggio.getVincitore(); }

    /** Vero se si sta giocando: la partita è cominciata e non è finita. */
    boolean isInCorso() { return iniziata && !punteggio.isFinita(); }

    /** Rimette a zero i conti e lancia la palla. */
    void avvia() {
        punteggio.azzera();
        iniziata = true;
        palla.lancia(campo.getLarghezza() / 2.0, campo.getAltezza() / 2.0, new Random().nextBoolean());
    }

    /** Un passo del gioco: viene chiamato 60 volte al secondo. */
    void aggiorna() {
        if (!isInCorso()) return;
        muoviRacchette();
        palla.muovi();
        controllaRimbalzi();
        controllaPunto();
    }

    /** Chiede a ciascun giocatore cosa vuole fare, e lo fa. Non sa chi sono i giocatori. */
    private void muoviRacchette() {
        Mossa mossaSinistra = giocatoreSinistra.decidiMossa(palla, racchettaSinistra);
        Mossa mossaDestra = giocatoreDestra.decidiMossa(palla, racchettaDestra);
        racchettaSinistra.muovi(mossaSinistra, campo);
        racchettaDestra.muovi(mossaDestra, campo);
    }

    private void controllaRimbalzi() {
        double y = palla.getPosizione().y();
        boolean toccaAlto = y - palla.getRaggio() <= 0 && palla.getVelocitaY() < 0;
        boolean toccaBasso = y + palla.getRaggio() >= campo.getAltezza() && palla.getVelocitaY() > 0;
        if (toccaAlto || toccaBasso) palla.rimbalzaVerticale();

        if (palla.tocca(racchettaSinistra) && palla.getVelocitaX() < 0) palla.rimbalzaSu(racchettaSinistra);
        if (palla.tocca(racchettaDestra) && palla.getVelocitaX() > 0) palla.rimbalzaSu(racchettaDestra);
    }

    /** Se la palla è uscita da un lato, il punto va all'altro lato. */
    private void controllaPunto() {
        double x = palla.getPosizione().x();
        if (x < 0) {
            punteggio.segnaPunto(Lato.DESTRA);
            dopoIlPunto(true);
        } else if (x > campo.getLarghezza()) {
            punteggio.segnaPunto(Lato.SINISTRA);
            dopoIlPunto(false);
        }
    }

    /** Dopo un punto: se la partita non è finita, si serve verso chi ha subito il punto. */
    private void dopoIlPunto(boolean versoSinistra) {
        if (!punteggio.isFinita()) {
            palla.lancia(campo.getLarghezza() / 2.0, campo.getAltezza() / 2.0, !versoSinistra);
        }
    }

    boolean isIniziata() { return iniziata; }
    Campo getCampo() { return campo; }
    Palla getPalla() { return palla; }
    Racchetta getRacchettaSinistra() { return racchettaSinistra; }
    Racchetta getRacchettaDestra() { return racchettaDestra; }
}
