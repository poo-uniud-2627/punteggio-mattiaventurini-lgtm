// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
package geometria;

/**
 * Un punto del piano, in pixel: x verso destra, y verso il basso.
 * È un record: i suoi valori non cambiano mai. Spostare un punto vuol dire crearne uno nuovo.
 */
public record Posizione(double x, double y) {

    /** Un punto nuovo, spostato di dx in orizzontale e di dy in verticale. Questo non cambia. */
    public Posizione spostata(double dx, double dy) {
        return new Posizione(x + dx, y + dy);
    }
}
