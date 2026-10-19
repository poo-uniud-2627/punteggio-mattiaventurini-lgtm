// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
package pong;

/** Il giocatore che legge due tasti della tastiera. */
class GiocatoreTastiera implements Giocatore {
    private final Tastiera tastiera;
    private final int tastoSu;
    private final int tastoGiu;

    GiocatoreTastiera(Tastiera tastiera, int tastoSu, int tastoGiu) {
        this.tastiera = tastiera;
        this.tastoSu = tastoSu;
        this.tastoGiu = tastoGiu;
    }

    @Override
    public Mossa decidiMossa(Palla palla, Racchetta racchetta) {
        if (tastiera.premuto(tastoSu)) return Mossa.SU;
        if (tastiera.premuto(tastoGiu)) return Mossa.GIU;
        return Mossa.FERMO;
    }
}
