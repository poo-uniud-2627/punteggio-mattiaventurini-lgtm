# Consegna 1 — Il Punteggio di Pong

**Scadenza: domenica 25 ottobre 2026, alle 23:59.** Conta com'è il repository a quell'ora: se i test automatici sono verdi, prendete un gettone (e, se è la vostra prima consegna verde, 10 crediti, da spendere alle aste). Dopo la scadenza il repository diventa pubblico e il codice va all'asta del giro 1 (laboratorio del 2 novembre; le regole dell'asta a lezione, lunedì 26).

## Cosa c'è qui

È la Pong che conoscete, divisa in pacchetti (lezione 4): `src/pong` il gioco, `src/giocatori` il giocatore automatico, `src/geometria` la `Posizione`. Una novità: i conti della partita non stanno più nella `Partita` ma in una classe a sé, **`Punteggio`** (`src/pong/Punteggio.java`). La `Partita` gli dice chi ha fatto punto; lo `Schermo` chiede i punti alla `Partita`, che li chiede al `Punteggio`.

La cartella `test` contiene i test ufficiali (`test/pong/PunteggioTest.java`); `lib` contiene JUnit, la libreria dei test; `.vscode` le impostazioni per VS Code; `.github` il file che fa partire i test su GitHub.

## Cosa dovete fare

Scrivere il corpo dei metodi di `Punteggio` e i campi che vi servono. Ogni metodo ha il suo **contratto** nel commento sopra: cosa pretende, cosa promette. Le novità rispetto alla lezione 2:

- **la regola per vincere** si sceglie quando si crea il `Punteggio`: `new Punteggio(11, 2)` vuol dire a 11 punti con almeno 2 punti di scarto (11 a 9 vince, 11 a 10 si continua, 12 a 10 vince); `new Punteggio(11, 1)` è la regola della lezione 2. Nel gioco la `Partita` lo crea con le sue due costanti, `PUNTI_PER_VINCERE` (11) e `SCARTO_MINIMO` (2); i test provano anche altre regole;
- **`annullaUltimoPunto()`**: l'arbitro ci ripensa e toglie l'ultimo punto della storia, anche quello che aveva chiuso la partita;
- **`getStoria()`**: la lista dei punti, nell'ordine in cui sono stati segnati. Chi la riceve non deve poter cambiare il `Punteggio` attraverso di lei (lezione 4, «dentro e fuori»);
- **`azzera()`**: una partita nuova con la stessa regola.

Potete aggiungere campi e metodi `private` di aiuto. Non cambiate le intestazioni dei metodi `public` (nome, parametri, tipo restituito), non toccate le altre classi né i test: i test ufficiali vengono comunque rimessi a ogni prova su GitHub. Non toccate nemmeno la cartella `.github`: se il file che fa partire i test viene cambiato, i test della consegna non contano.

Una lista in Java: `List<Lato>` è una lista di valori `Lato`; `new ArrayList<>()` ne crea una vuota che si può cambiare; `lista.add(x)` aggiunge in fondo, `lista.remove(lista.size() - 1)` toglie l'ultimo, `lista.size()` dice quanti sono, `lista.get(i)` dà l'elemento in posizione `i` (si parte da 0). `List.copyOf(lista)` crea una copia che non si può cambiare.

## Come si prova

**Sul vostro portatile, in VS Code** (lezione 5): aprite la cartella del repository, poi la barra laterale **Testing** (l'icona della provetta): i test di `PunteggioTest` compaiono da soli; il triangolo li lancia, verde o rosso per ciascuno. Il gioco si lancia da `src/pong/Main.java` («Run» sopra `main`).

**Su GitHub**: a ogni `git push` GitHub lancia i test. Il risultato compare nella pagina del repository, accanto all'ultimo commit (un segno verde o una croce rossa), e nella scheda **Actions**. Così sapete prima della scadenza se siete verdi.

**Come si legge un test rosso.** Il nome del test dice il caso provato (per esempio `a11ConScarto2UndiciADieciContinua`: a 11 con scarto 2, sull'11 a 10 la partita deve continuare). Sotto, una riga come `expected: <false> but was: <true>` dice cosa il contratto si aspettava e cosa ha restituito il vostro codice. In VS Code la trovate cliccando sul test rosso; su GitHub, in **Actions**, aprendo l'ultima prova e poi il passo dei test.

## Come si consegna

Con git: `git add`, `git commit -m "…"`, `git push` (anche dalla barra laterale di VS Code, **Source Control**). La prima volta (al clone o al primo `push`) VS Code vi chiede di accedere con il vostro account GitHub: «Allow»; si apre GitHub nel browser, entrate con il vostro account e cliccate «Authorize»; poi il browser vi chiede di tornare in VS Code. Succede una volta sola.

**Se git non funziona**, si può consegnare anche dalla pagina web del repository: aprite `src/pong/Punteggio.java`, la matita in alto a destra («Edit this file»), incollate il vostro codice, poi «Commit changes». Ogni modifica salvata così è un commit e fa partire i test.

## L'invito

Il repository vi arriva con un invito di GitHub. Lo trovate in tre posti: nella posta dell'email del vostro account GitHub; su GitHub, nella campanella delle notifiche in alto a destra; nella vostra pagina del mercato (poo.dimi.uniud.it), alla voce «I tuoi repository», con il link per accettarlo. Va accettato entro 7 giorni; se scade, il mercato ve lo rimanda.

**L'invito non c'è da nessuna parte?** Controllate il nome utente GitHub che avete scritto nel questionario del corso: se è sbagliato, correggete la vostra risposta; l'invito arriva al nome utente giusto quando l'elenco del corso viene aggiornato.
