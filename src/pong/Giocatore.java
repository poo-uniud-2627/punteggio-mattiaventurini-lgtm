// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
package pong;

/**
 * L'accordo: un giocatore è un oggetto che sa rispondere alla domanda «su, giù o fermo?».
 * La Partita non sa chi c'è dall'altra parte: le basta questo.
 */
public interface Giocatore {
    Mossa decidiMossa(Palla palla, Racchetta racchetta);
}
