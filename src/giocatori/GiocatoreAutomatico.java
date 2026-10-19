// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
package giocatori;

import pong.Giocatore;
import pong.Mossa;
import pong.Palla;
import pong.Racchetta;

/** La soluzione più semplice della lezione 1: la racchetta insegue la palla. */
public class GiocatoreAutomatico implements Giocatore {

    @Override
    public Mossa decidiMossa(Palla palla, Racchetta racchetta) {
        if (palla.getPosizione().y() < racchetta.getCentroY()) return Mossa.SU;
        if (palla.getPosizione().y() > racchetta.getCentroY()) return Mossa.GIU;
        return Mossa.FERMO;
    }
}
