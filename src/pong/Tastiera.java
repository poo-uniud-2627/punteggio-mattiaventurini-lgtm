// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
package pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

/** Ricorda quali tasti sono premuti in questo momento. */
class Tastiera extends KeyAdapter {
    private final Set<Integer> premuti = new HashSet<>();

    boolean premuto(int tasto) { return premuti.contains(tasto); }

    @Override public void keyPressed(KeyEvent e) { premuti.add(e.getKeyCode()); }
    @Override public void keyReleased(KeyEvent e) { premuti.remove(e.getKeyCode()); }
}
