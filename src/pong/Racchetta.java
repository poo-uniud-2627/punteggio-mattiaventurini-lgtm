// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
package pong;

/** Una racchetta: sta a un'altezza fissa in orizzontale e si muove solo in verticale. */
public class Racchetta {
    static final int LARGHEZZA = 12;
    static final int ALTEZZA = 80;
    /** Quanti pixel si sposta la racchetta a ogni fotogramma, quando si muove. */
    static final int VELOCITA = 6;

    private final int x;
    private double y;

    Racchetta(int x, double y) {
        this.x = x;
        this.y = y;
    }

    // ---- quello che chiunque può chiedere alla racchetta

    public int getX() { return x; }
    /** Il bordo alto della racchetta (le y crescono verso il basso). */
    public double getY() { return y; }
    public double getCentroY() { return y + ALTEZZA / 2.0; }
    public int getLarghezza() { return LARGHEZZA; }
    public int getAltezza() { return ALTEZZA; }

    // ---- quello che può chiedere solo il codice del pacchetto pong (la Partita)

    /**
     * Esegue una mossa.
     * Pretende: campo non è null ed è alto almeno quanto la racchetta; la racchetta è dentro il campo.
     * Promette: con SU sale di VELOCITA pixel, con GIU scende, con FERMO (o null) resta dov'è;
     *           alla fine sta tutta dentro il campo.
     */
    void muovi(Mossa mossa, Campo campo) {
        if (mossa == Mossa.SU) y -= VELOCITA;
        if (mossa == Mossa.GIU) y += VELOCITA;
        if (y < 0) y = 0;
        if (y > campo.getAltezza() - ALTEZZA) y = campo.getAltezza() - ALTEZZA;
    }
}
