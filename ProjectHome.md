Si sviluppi un’applicazione web che gestisce una pizzeria online: presenta su web il
catalogo delle pizze e permette di effettuare prenotazioni via web. L’applicazione
gestisce tre tipi di ruolo utente: utente anonimo, utente registrato (cliente della
pizzeria) e amministratore del sito web.
Nel dettaglio, l’applicazione permette all’utente di:
• Visualizzare il catalogo delle pizze (per tutti i ruoli)
• Prenotare pizze (specificando tipo, quantità e data di consegna), visualizzare le
proprie prenotazioni di pizze, cancellare una o più delle proprie prenotazioni e
confermare l’avvenuta consegna delle pizze prenotate. Ruoli: solo utente
registrato e amministratore.
• Visualizzare tutte le prenotazioni di pizze esistenti. Ruoli: solo
amministratore.
• Inserire/rimuovere/modificare i descrittori delle pizze in catalogo. Ruoli: solo
amministratore.
• La registrazione di utenti nuovi (nuovi clienti della pizzeria) e’ opzionale.

Requisiti tecnici:
• Il descrittore delle pizze deve specificare nome, ingredienti e prezzo in Euro.
• L’applicazione deve essere basata su architettura MVC, con Controller + viste
implementate come JSP/pagine HTML5+AJAX+CSS e Model per l’accesso ai
dati.
• Si controlli l’inserimento di input utente, ove necessario (per esempio per
evitare che l’utente cerchi di collegarsi senza inserire login e password, o che
l’amministratore inserisca dati parziali delle pizze). A tale scopo, si usino i tag
di HTML5 e/o javascript. In caso di errore, l’applicazione deve permettere
all’utente di correggere i dati e ripetere l’operazione senza perdere i dati
precedentemente inseriti (cioè, senza riempire d’accapo le form).
• L’applicazione deve salvare in un database relazionale i seguenti tipi di
informazione:
o account, password e ruolo degli utenti registrati
o descrittori delle pizze
o prenotazioni di pizze
• L’applicazione deve controllare che gli utenti non eseguano operazioni
illecite. Per es., che un utente non amministratore cerchi di inserire un
descrittore di pizza in catalogo. Ogni utente (tranne l’amministratore) vede
solo le proprie prenotazioni di pizze e non quelle altrui.
• E’ obbligatorio gestire le sessioni utente e garantire che un utente non acceda
a pagine che richiedono autenticazione senza essere autenticato.
• L’interfaccia utente deve essere:
o Basata sull’uso di HTML5 con layout fluido.
o Comprensibile (trasparenza). Per esempio, a fronte di errori, deve
segnalare il problema; quando un’operazione viene eseguita con
successo, deve visualizzare la conferma di esecuzione. o Ragionevolmente efficiente per permettere all’utente di eseguire le
operazioni volute con un numero minimo di click e di inserimenti di
dati.
NB: la scelta del dominio applicativo (pizzeria) non e’ vincolante e sono ammessi
altri tipi di applicazione, purche’ si rispettino le specifiche funzionali e tecniche
descritte sopra.
NB: Chi desidera puo’ sviluppare l’applicazione utilizzando il framework Spring 3.0.