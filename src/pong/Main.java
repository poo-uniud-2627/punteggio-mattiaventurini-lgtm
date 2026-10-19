// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
package pong;

import giocatori.GiocatoreAutomatico;
import java.awt.event.KeyEvent;

/** L'avvio: sceglie i giocatori e apre la finestra. In VS Code: «Run» sopra main. */
public class Main {

    public static void main(String[] args) {
        Tastiera tastiera = new Tastiera();
        Giocatore sinistra = new GiocatoreTastiera(tastiera, KeyEvent.VK_W, KeyEvent.VK_S);
        Giocatore destra = new GiocatoreTastiera(tastiera, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
        // Per giocare contro il giocatore automatico (a sinistra), togliete le due barre:
        // sinistra = new GiocatoreAutomatico();

        Partita partita = new Partita(new Campo(800, 500), sinistra, destra);
        Schermo.mostra(partita, tastiera);
    }
}
