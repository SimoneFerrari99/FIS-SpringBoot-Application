# Progetto di Fondamenti di Ingegneria del Software
**Simone Ferrari VR479921**

## Indice
[1. Il sistema MentCare](#il-sistema-mentcare)  
[2. Modulo implementato](#modulo-implementato)  
[3. Ingegneria dei requisiti: scenari](#ingegneria-dei-requisiti-scenari)

## Il sistema MentCare
**Descrizione generale**  
Il sistema in oggetto deve raccogliere e gestire le informazioni dei pazienti in cura presso alcune strutture specializzate nel settore della salute mentale. Tra le informazioni gestite, vi sono tutti i dati personali del paziente, le cure ricevute e i farmaci prescritti dai medici. Inoltre, vanno memorizzate informazioni riguardo quest'ultimi: dei medici, infatti, oltre ai dati personali, è necessario conoscere la loro specializzazione e la loro aggenda contenente gli appuntamenti prenotati.  Gli appuntamenti, infine, riguardano un paziente ed un medico, si svolgono in una clinica ed hanno una data associata.

I pazienti non necessitano di cure ospedaliere dedicate, ma devono frequentare regolarmente cliniche specializzate e incontrare un medico che abbia una conoscenza dettagliata dei loro problemi.
Perciò, ad ogni paziente deve essere associato il medico più appropriato e tutti i dati devono essere salvati in un database centralizzato. Il sistema deve essere accessibile da host remoti, i quali possono essere di diverse tipologie: PC Desktop, Laptop, Tablet, Smartphone, ...

**Privacy**  
Le informazioni trattate sono estremamente sensibili, pertanto l'accesso ad esse deve avvenire previa autenticazione e in estrema sicurezza. Non tutti gli utenti del sistema hanno accesso alla cartella clinica del paziente: questa, infatti, può essere consultata solo dal medico e dal paziente stesso.

**Reperibilità e sicurezza**  
I pazienti in cura, inoltre, possono essere irrazionali a causa della natura delle loro problematiche, e potrebbero: perdere medicinali e/o prescrizioni; perdersi all'interno delle strutture cliniche; dimenticarsi degli appuntamenti con i medici.
Ne segue che il paziente (o il tutore) debba poter essere contattato da parte del medico o della segreteria con delle comunicazioni di qualsiasi genere.
Infine, alcuni pazienti potrebbero presentare delle problematiche gravi, che li renda classificabili come "pericolosi". In tal caso, l'intero staff (medico e non) deve essere sempre avvertito, in maniera tale che si possano prendere le dovute precauzioni a tutela del medico, del personale di segreteria e degli altri pazienti.

**Amministrazione**  
Per quanto concerne la parte amministrativa, si deve sempre tenere traccia delle azioni svolte dal medico all'interno della cartella clinica, le quali, in caso di eventuale indagine di polizia o revisione giudiziaria, devono poter essere estratte. Inoltre, il sistema deve generare report mensili di vario genere, sia a fini statistici che per controllare le forniture delle varie cliniche.

**Utenti del sistema**  
Il sistema deve essere disponibili per diverse tipologie di utenza:
- Personale medico:
    - Medici: utente rappresentate i medici. Possono visualizzare le cartelle cliniche dei pazienti, all'interno delle quali è concesso prescrivere medicinali e cure. Il medico può visualizzare un report dettagliato del paziente, analizzandone l'intera storia clinica. Può, inoltre, vedere la propria agenda contenente gli appuntamenti futuri.
    - Infermieri: utente di supporto al medico. Possono accedere alle cartelle cliniche dei pazienti solo in visualizzazione, in quanto necessario per somministrare i farmaci prescritti. Dopo la somministrazione, devono aggiungere un log alla cartella clinica per confermare l'avvenuta somministrazione.
    - Visitatori sanitari: sottocategoria di infermiere che esegue le proprie azioni presso il domicilio dei pazienti. Le azioni concesse sono le stesse degli infermieri.

- Personale non medico:
    - Receptionist: personale di segreteria, si occupa della gestione degli appuntamenti, dei nuovi (e non) pazienti e delle comunicazioni da inviare ai medici e/o ai pazienti (o tutori).
    - Gestore cartelle cliniche: gestiscono e controllano i record medici delle cartelle cliniche.
    - Amministratore: personale amministrativo, si occupa di visualizzare e analizzare i report generati dal sistema. Inoltre, si occupa della gestione dei medici e del resto del personale.

- Pazienti:
    - Profilo personale del paziente, utilizzato da lui stesso (se autonomo) o dal tutore. Si possono visualizzare gli appuntamenti futuri e si può accedere alla cartella clinica. Infine, si possono leggere/inviare comunicazioni dal/al medico e/o dalla/alla segreteria.


## Modulo implementato
Come richiesto è stato sviluppato un singolo modulo del progetto MentCare, ovvero quello relativo al medico.


## Ingegneria dei requisiti: scenari
### 1. Visualizzare i prossimi appuntamenti
**Obiettivo**: Il receptionist deve essere in grado di visualizzare rapidamente i prossimi appuntamenti.  
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage [ */* ].  
**Flusso normale**: Il receptionist trova una sezione denominata "Appuntamenti di oggi", la quale contiene i dettagli degli appuntamenti della giornata attuale. Per ogni appointment, vi è la possibilità di ottenere maggiori dettagli e/o di modificarlo, facendo uso dei due appositi pulsanti: rispettivamente, uno grigio <i class="fas fa-search-plus"></i> e uno blu <i class="fas fa-edit"></i>.  
**Flusso alternativo**: Il receptionist trova una sezione denominata "Appuntamenti di oggi", la quale contiene i dettagli degli appuntamenti della giornata attuale. Nell'angolo superiore destro è presente un pulsante "<i class="fas fa-search-plus"></i> Vedi tutti". Il receptionist esegue il click su di esso e viene rediretto alla pagina degli appuntamenti [ */appuntamenti* ], dove può visualizzare tutti gli appuntamenti futuri, ordinati dal più prossimo al più lontano.  
**Postcondizioni**: Il receptionist ha a disposizione la lista degli appuntamenti futuri.

### 2. Ricevere nuove richieste di cura
**Obiettivo**: Il receptionist deve gestire le nuove richieste di cura da parte dei pazienti.  
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage [ */* ].  
**Flusso normale**: Il receptionist trova una sezione denominata "Nuove richieste", la quale contiene i dettagli delle richieste ricevute (data, nome e cognome del paziente e la descrizione di massima della problematica). Per ogni richiesta, ha la possibilità di accettare <i class="fas fa-check"></i> o rifiutare <i class="fas fa-times"></i>. Nel caso il receptionist volesse accettarla, esegue il click sul pulsante verde <i class="fas fa-check"></i>. Viene rediretto alla pagina contenente tutti i dettagli (non modificabili) del paziente (Nome, Cognome, Codice Fiscale, Descrizione problematica) [ */nuovo-paziente* ]. All'interno di questa pagina, il receptionist seleziona: il medico da associare al paziente mediante il menù a tendina "Medico", un flag per classificare il paziente come pericoloso o meno e digita una "categoria" di problematica. Una volta valorizzati il campi, il receptionist clicca sul pulsante "<i class="fas fa-save"></i> Salva", confermando l'accettazione della richiesta.  
**Flusso alternativo**: NA  
**Postcondizioni**: Il nuovo paziente è stato inserito nel sistema ed il receptionist è collegato alla pagina dei dettagli del paziente appena aggiunto [ */paziente/{id}* ]

### 3. Aggiungere nuovo paziente
**Obiettivo**: Il receptionist deve inserire nuovi pazienti all'interno del sistema.  
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage [ */* ].  
**Flusso normale**: Il receptionist deve accedere alla pagina dei pazieni: a tale scopo, esegue il click sul link "pazienti" posto all'interno della Navigation Bar collocata in cima alla pagina. Viene quindi rediretto alla pagina dei pazienti [ */pazienti* ], dove trova la lista di tutti i pazienti. Il receptionist deve cliccare sul pulsante verde "<i class="fas fa-plus"></i> Nuovo" posto nell'angolo superiore destro. Viene così rediretto alla pagina dedicata all'aggiunta di un nuovo paziente [ */nuovo-paziente* ]. Qui, quindi, procede con la compilazione dei campi del form presente e, una volta valorizzato l'intero form, il receptionist deve cliccare il pulsante "<i class="fas fa-save"></i> Salva" per confermare l'inserimento del paziente nel sistema.   
**Flusso alternativo**: NA    
**Postcondizioni**: Il nuovo paziente è stato inserito nel sistema ed il receptionist è collegato alla pagina dei dettagli del paziente appena aggiunto [ */paziente/{id}* ]

### 4. Aggiungere nuovo appointment
**Obiettivo**: Il receptionist deve inserire nuovi appuntamenti all'interno del sistema.  
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage [ */* ].  
**Flusso normale**: Il receptionist deve accedere alla pagina degli appuntamenti: a tale scopo, esegue il click sul link "appuntamenti" posto all'interno della Navigation Bar collocata in cima alla pagina. Viene quindi rediretto alla pagina degli appuntamenti [ */appuntamenti* ], dove trova la lista di tutti gli appuntamenti. Il receptionist deve cliccare sul pulsante verde "<i class="fas fa-plus"></i> Nuovo" posto nell'angolo superiore destro. Si apre quindi una finestra modale, in cui il receptionist deve scegliere un medico o un paziente a cui associare il nuovo appointment. Una volta effettuata la scelta, il receptionist clicca su "<i class="fas fa-check"></i> Conferma Medico" o "<i class="fas fa-check"></i> Conferma Paziente" rispettivamente. Viene così rediretto alla pagina dedicata all'aggiunta di un nuovo appointment [ */nuovo-appointment* ]. Qui, il campo "medico" è valorizzato e disabilitato se allo step precedente il receptionist ha confermato il medico. Se invece ha confermato il paziente, sia il campo "medico" che il campo "paziente" sono valorizzati e disabilitati. Il receptionist quindi procede con la compilazione dei campi del form presente e, una volta valorizzato l'intero form, il receptionist deve cliccare il pulsante "<i class="fas fa-save"></i> Salva" per confermare l'inserimento dell'appointment nel sistema.  
**Flusso alternativo**: L'aggiunda di un appointment può avvenire anche in altre due modalità:
1) A partire dalla pagina di dettaglio di un medico [ */medico/{id}* ]: Il receptionist esegue il click sul link "medici" posto all'interno della Navigation Bar collocata in cima alla pagina. Viene quindi rediretto alla pagina dei medici [ */medici* ] dove trova la lista di tutti i medici. Ogni entry della lista predispone di un pulsante "<i class="fas fa-search-plus"></i> maggiori dettagli", di colore grigio. Il receptionist clicca su di esso e viene rediretto al dettaglio del medico [ */medico/{id}* ]. All'interno della sezione "appuntamenti", il receptionist clicca sul pulsante verde "<i class="fas fa-plus"></i> Nuovo" per aggiungere un nuovo appointment. Viene così rediretto alla pagina dedicata all'aggiunta di un nuovo appointment [ */nuovo-appointment* ], dove questa volta troverà il campo "medico" precompilato e disabilitato. Il campo "paziente" conterrà solo i pazienti associati al medico. Il receptionist quindi procede con la compilazione dei campi del form presente e, una volta valorizzato l'intero form, deve cliccare il pulsante "<i class="fas fa-save"></i> Salva" per confermare l'inserimento dell'appointment nel sistema.
2) A partire dalla pagina di dettaglio di un paziente [ */paziente/{id}* ]: Il receptionist esegue il click sul link "pazienti" posto all'interno della Navigation Bar collocata in cima alla pagina. Viene quindi rediretto alla pagina dei pazienti [ */pazienti* ] dove trova la lista di tutti i pazienti. Ogni entry della lista predispone di un pulsante "<i class="fas fa-search-plus"></i> maggiori dettagli", di colore grigio. Il receptionist clicca su di esso e viene rediretto al dettaglio del paziente [ */paziente/{id}* ]. All'interno della sezione "appuntamenti", il receptionist clicca sul pulsante verde "<i class="fas fa-plus"></i> Nuovo" per aggiungere un nuovo appointment. Viene così rediretto alla pagina dedicata all'aggiunta di un nuovo appointment [ */nuovo-appointment* ], dove questa volta troverà i campi "medico" e "paziente" precompilati e disabilitati. Il receptionist quindi procede con la compilazione dei campi del form presente e, una volta valorizzato l'intero form, deve cliccare il pulsante "<i class="fas fa-save"></i> Salva" per confermare l'inserimento dell'appointment nel sistema.

**Postcondizioni**: Il nuovo appointment è stato inserito nel sistema ed il receptionist è collegato alla pagina dei dettagli dell'appointment appena aggiunto [ */appointment/{id}* ]

### 5. Visualizzare dettagli appointment
**Obiettivo**: Il receptionist deve visualizzare i dettagli degli appuntamenti.
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage [ */* ].  
**Flusso normale**: Ogniqualvolta si presenti una tabella contenente gli appuntamenti ([ */* ], [ */appuntamenti* ], [ */paziente/{id}* ], [ */medico/{id}* ]), ogni singola entry è dotata di un pulsante color grigio contenente una lente di ingrandimento <i class="fas fa-search-plus"></i>. Il receptionist esegue il click su di essa, e viene rediretto alla pagina di dettaglio dell'appointment [ */appointment/{id}* ]  
**Flusso alternativo**: NA    
**Postcondizioni**: Il receptionist è collegato alla pagina di dettaglio dell'appointment [ */appointment/{id}* ] e può visualizzare tutti i dati ad esso associati (Medico, Paziente, Data, Clinica e Comunicazioni)

### 6. Inviare comunicazioni associate ad un appointment
**Obiettivo**: Il receptionist deve inviare, ai medico e/o ai paziente, comunicazioni relative agli appuntamenti.
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage [ */* ].  
**Flusso normale**: Ogniqualvolta si presenti una tabella contenente gli appuntamenti ([ */* ], [ */appuntamenti* ], [ */paziente/{id}* ], [ */medico/{id}* ]), ogni singola entry è dotata di un pulsante color grigio contenente una lente di ingrandimento <i class="fas fa-search-plus"></i>. Il receptionist esegue il click su di essa, e viene rediretto alla pagina di dettaglio dell'appointment [ */appointment/{id}* ]. Nella sezione "Comunicazioni" è presente un pulsante verde "<i class="fas fa-plus"></i> Nuova". Il receptionist esegue il click su di esso, e viene rediretto alla pagina di invio comunicazione [ */appointment/{id}/nuova-comunicazione* ]. Qui, digita il testo della comunicazione, successivamente seleziona i checkbox relativi ai destinatari della comunicazione e, infine, clicca sul pulsante "<i class="fas fa-paper-plane"></i> Invia" posto nell'angolo superiore destro.  
**Flusso alternativo**: NA    
**Postcondizioni**: La comunicazione viene inviata e il receptionist viene rediretto alla pagina di dettaglio appointment [ */appointment/{id}* ], dove può vedere la presenza della nuova comunicazione all'interno della sezione dedicata.

<link
    rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous"
/>