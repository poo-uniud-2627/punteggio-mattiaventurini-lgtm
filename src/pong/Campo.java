// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
package pong;

/** Il rettangolo in cui si gioca. Non cambia mai. */
public class Campo {
    private final int larghezza;
    private final int altezza;

    public Campo(int larghezza, int altezza) {
        this.larghezza = larghezza;
        this.altezza = altezza;
    }

    public int getLarghezza() { return larghezza; }
    public int getAltezza() { return altezza; }
}
