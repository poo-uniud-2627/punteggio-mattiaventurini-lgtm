// Pong — Programmazione Orientata agli Oggetti e Laboratori, Udine, a.a. 2026-27
// Consegna 1: i test ufficiali del Punteggio. Ogni test è un caso del contratto.
package pong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class PunteggioTest {

    // ---- attrezzi

    private static void punti(Punteggio p, Lato chi, int quanti) {
        for (int i = 0; i < quanti; i++) p.segnaPunto(chi);
    }

    private static void assertPunteggio(Punteggio p, int sinistra, int destra) {
        assertEquals(sinistra, p.getPunti(Lato.SINISTRA), "punti di sinistra");
        assertEquals(destra, p.getPunti(Lato.DESTRA), "punti di destra");
    }

    private static void assertInCorso(Punteggio p) {
        assertFalse(p.isFinita(), "la partita non deve essere finita");
        assertNull(p.getVincitore(), "a partita in corso non c'è vincitore");
    }

    private static void assertVince(Punteggio p, Lato chi) {
        assertTrue(p.isFinita(), "la partita deve essere finita");
        assertEquals(chi, p.getVincitore(), "vincitore");
    }

    /** Prova a cambiare una lista ricevuta; se la lista non si lascia cambiare, va bene lo stesso. */
    private static void provaACambiare(List<Lato> lista) {
        try { lista.add(Lato.DESTRA); } catch (UnsupportedOperationException e) { /* non si lascia cambiare */ }
        try { lista.add(Lato.DESTRA); } catch (UnsupportedOperationException e) { /* idem */ }
        try { lista.set(0, Lato.DESTRA); } catch (UnsupportedOperationException | IndexOutOfBoundsException e) { /* idem */ }
    }

    // ---- all'inizio

    @Test
    void allInizioZeroAZeroInCorsoStoriaVuota() {
        Punteggio p = new Punteggio(11, 2);
        assertPunteggio(p, 0, 0);
        assertInCorso(p);
        assertTrue(p.getStoria().isEmpty(), "la storia deve essere vuota");
    }

    // ---- segnaPunto e storia

    @Test
    void unPuntoASinistra() {
        Punteggio p = new Punteggio(11, 2);
        p.segnaPunto(Lato.SINISTRA);
        assertPunteggio(p, 1, 0);
        assertEquals(List.of(Lato.SINISTRA), p.getStoria());
    }

    @Test
    void laStoriaTieneLOrdineDeiPunti() {
        Punteggio p = new Punteggio(11, 2);
        p.segnaPunto(Lato.DESTRA);
        p.segnaPunto(Lato.SINISTRA);
        p.segnaPunto(Lato.DESTRA);
        assertPunteggio(p, 1, 2);
        assertEquals(List.of(Lato.DESTRA, Lato.SINISTRA, Lato.DESTRA), p.getStoria());
    }

    // ---- la regola per vincere

    @Test
    void a11ConScarto1DieciAZeroContinua() {
        Punteggio p = new Punteggio(11, 1);
        punti(p, Lato.SINISTRA, 10);
        assertPunteggio(p, 10, 0);
        assertInCorso(p);
    }

    @Test
    void a11ConScarto1UndiciAZeroVinceSinistra() {
        Punteggio p = new Punteggio(11, 1);
        punti(p, Lato.SINISTRA, 11);
        assertVince(p, Lato.SINISTRA);
    }

    @Test
    void a11ConScarto1DieciADieciPoiDestraVinceDestra() {
        Punteggio p = new Punteggio(11, 1);
        punti(p, Lato.SINISTRA, 10);
        punti(p, Lato.DESTRA, 10);
        assertInCorso(p);
        p.segnaPunto(Lato.DESTRA);
        assertPunteggio(p, 10, 11);
        assertVince(p, Lato.DESTRA);
    }

    @Test
    void a11ConScarto2UndiciANoveVinceSinistra() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.DESTRA, 9);
        punti(p, Lato.SINISTRA, 11);
        assertPunteggio(p, 11, 9);
        assertVince(p, Lato.SINISTRA);
    }

    @Test
    void a11ConScarto2UndiciADieciContinua() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.SINISTRA, 10);
        punti(p, Lato.DESTRA, 10);
        p.segnaPunto(Lato.SINISTRA);
        assertPunteggio(p, 11, 10);
        assertInCorso(p);
    }

    @Test
    void a11ConScarto2DodiciADieciVinceSinistra() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.SINISTRA, 10);
        punti(p, Lato.DESTRA, 10);
        punti(p, Lato.SINISTRA, 2);
        assertPunteggio(p, 12, 10);
        assertVince(p, Lato.SINISTRA);
    }

    @Test
    void a11ConScarto2SiVaAvantiFinoAlloScarto() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.SINISTRA, 10);
        punti(p, Lato.DESTRA, 10);
        for (int i = 0; i < 5; i++) {
            p.segnaPunto(Lato.SINISTRA);
            p.segnaPunto(Lato.DESTRA);
        }
        assertPunteggio(p, 15, 15);
        assertInCorso(p);
        punti(p, Lato.DESTRA, 2);
        assertPunteggio(p, 15, 17);
        assertVince(p, Lato.DESTRA);
    }

    @Test
    void a3ConScarto2TreADueContinuaQuattroADueVince() {
        Punteggio p = new Punteggio(3, 2);
        punti(p, Lato.SINISTRA, 2);
        punti(p, Lato.DESTRA, 2);
        p.segnaPunto(Lato.SINISTRA);
        assertPunteggio(p, 3, 2);
        assertInCorso(p);
        p.segnaPunto(Lato.SINISTRA);
        assertVince(p, Lato.SINISTRA);
    }

    @Test
    void a5ConScarto3CinqueATreContinuaSeiATreVince() {
        Punteggio p = new Punteggio(5, 3);
        punti(p, Lato.DESTRA, 3);
        punti(p, Lato.SINISTRA, 5);
        assertPunteggio(p, 5, 3);
        assertInCorso(p);
        p.segnaPunto(Lato.SINISTRA);
        assertPunteggio(p, 6, 3);
        assertVince(p, Lato.SINISTRA);
    }

    @Test
    void a1ConScarto1IlPrimoPuntoVince() {
        Punteggio p = new Punteggio(1, 1);
        p.segnaPunto(Lato.DESTRA);
        assertPunteggio(p, 0, 1);
        assertVince(p, Lato.DESTRA);
    }

    // ---- dopo la fine

    @Test
    void vinceSinistraUnAltroPuntoNonCambiaNiente() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.SINISTRA, 11);
        p.segnaPunto(Lato.DESTRA);
        p.segnaPunto(Lato.SINISTRA);
        assertPunteggio(p, 11, 0);
        assertVince(p, Lato.SINISTRA);
        assertEquals(11, p.getStoria().size(), "i punti dopo la fine non entrano nella storia");
    }

    @Test
    void vinceDestraUnAltroPuntoNonCambiaNiente() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.DESTRA, 11);
        p.segnaPunto(Lato.SINISTRA);
        p.segnaPunto(Lato.DESTRA);
        assertPunteggio(p, 0, 11);
        assertVince(p, Lato.DESTRA);
        assertEquals(11, p.getStoria().size(), "i punti dopo la fine non entrano nella storia");
    }

    @Test
    void ilVincitoreNonCambiaPiu() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.DESTRA, 11);
        punti(p, Lato.SINISTRA, 20);
        assertVince(p, Lato.DESTRA);
    }

    // ---- annullaUltimoPunto

    @Test
    void annullaTogliELUltimoPunto() {
        Punteggio p = new Punteggio(11, 2);
        p.segnaPunto(Lato.SINISTRA);
        p.segnaPunto(Lato.DESTRA);
        p.segnaPunto(Lato.DESTRA);
        p.annullaUltimoPunto();
        assertPunteggio(p, 1, 1);
        assertEquals(List.of(Lato.SINISTRA, Lato.DESTRA), p.getStoria());
    }

    @Test
    void annullaConLaStoriaVuotaNonCambiaNiente() {
        Punteggio p = new Punteggio(11, 2);
        p.annullaUltimoPunto();
        assertPunteggio(p, 0, 0);
        assertInCorso(p);
        assertTrue(p.getStoria().isEmpty());
    }

    @Test
    void annullaTogliIlPuntoDiChiHaSegnatoPerUltimoAncheSeEInSvantaggio() {
        Punteggio p = new Punteggio(11, 2);
        p.segnaPunto(Lato.SINISTRA);
        p.segnaPunto(Lato.SINISTRA);
        p.segnaPunto(Lato.DESTRA);
        p.annullaUltimoPunto();
        assertPunteggio(p, 2, 0);
        assertEquals(List.of(Lato.SINISTRA, Lato.SINISTRA), p.getStoria());
    }

    @Test
    void annullaDueVolteDiFila() {
        Punteggio p = new Punteggio(11, 2);
        p.segnaPunto(Lato.SINISTRA);
        p.segnaPunto(Lato.DESTRA);
        p.segnaPunto(Lato.SINISTRA);
        p.annullaUltimoPunto();
        p.annullaUltimoPunto();
        assertPunteggio(p, 1, 0);
        assertEquals(List.of(Lato.SINISTRA), p.getStoria());
    }

    @Test
    void annullareIlPuntoDellaVittoriaRiapreLaPartita() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.SINISTRA, 11);
        p.annullaUltimoPunto();
        assertPunteggio(p, 10, 0);
        assertInCorso(p);
        p.segnaPunto(Lato.DESTRA);
        assertPunteggio(p, 10, 1);
    }

    @Test
    void annullaDopoUnPuntoIgnoratoTogliIlPuntoDellaVittoria() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.DESTRA, 11);
        p.segnaPunto(Lato.SINISTRA); // ignorato: la partita era finita
        p.annullaUltimoPunto();
        assertPunteggio(p, 0, 10);
        assertInCorso(p);
    }

    @Test
    void annullaConScarto2RiapreAncheDaDodiciADieci() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.SINISTRA, 10);
        punti(p, Lato.DESTRA, 10);
        punti(p, Lato.SINISTRA, 2);
        p.annullaUltimoPunto();
        assertPunteggio(p, 11, 10);
        assertInCorso(p);
    }

    // ---- la storia non espone il Punteggio

    @Test
    void cambiareLaStoriaRicevutaNonCambiaIlPunteggio() {
        Punteggio p = new Punteggio(11, 2);
        p.segnaPunto(Lato.SINISTRA);
        p.segnaPunto(Lato.SINISTRA);
        provaACambiare(p.getStoria());
        assertPunteggio(p, 2, 0);
        assertEquals(List.of(Lato.SINISTRA, Lato.SINISTRA), p.getStoria());
    }

    @Test
    void cambiareLaStoriaVuotaRicevutaNonCambiaIlPunteggio() {
        Punteggio p = new Punteggio(11, 2);
        provaACambiare(p.getStoria());
        assertPunteggio(p, 0, 0);
        assertTrue(p.getStoria().isEmpty());
        p.segnaPunto(Lato.SINISTRA);
        assertEquals(List.of(Lato.SINISTRA), p.getStoria());
    }

    @Test
    void cambiareLaStoriaRicevutaNonCambiaLAnnulla() {
        Punteggio p = new Punteggio(11, 2);
        p.segnaPunto(Lato.SINISTRA);
        provaACambiare(p.getStoria());
        p.annullaUltimoPunto();
        assertPunteggio(p, 0, 0);
        assertTrue(p.getStoria().isEmpty());
    }

    @Test
    void cambiareLaStoriaRicevutaNonCambiaLaFine() {
        Punteggio p = new Punteggio(3, 1);
        punti(p, Lato.SINISTRA, 3);
        List<Lato> storia = p.getStoria();
        try { storia.clear(); } catch (UnsupportedOperationException e) { /* non si lascia cambiare */ }
        assertVince(p, Lato.SINISTRA);
        assertEquals(3, p.getStoria().size());
    }

    // ---- azzera

    @Test
    void azzeraRiportaAZeroATutto() {
        Punteggio p = new Punteggio(11, 2);
        punti(p, Lato.SINISTRA, 11);
        p.azzera();
        assertPunteggio(p, 0, 0);
        assertInCorso(p);
        assertTrue(p.getStoria().isEmpty());
    }

    @Test
    void dopoAzzeraLaRegolaNonCambia() {
        Punteggio p = new Punteggio(3, 2);
        punti(p, Lato.DESTRA, 3);
        p.azzera();
        punti(p, Lato.SINISTRA, 2);
        punti(p, Lato.DESTRA, 2);
        p.segnaPunto(Lato.SINISTRA);
        assertInCorso(p);
        p.segnaPunto(Lato.SINISTRA);
        assertVince(p, Lato.SINISTRA);
    }
}
