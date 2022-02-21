# Progetto di Fondamenti di Ingegneria del Software
**Simone Ferrari VR479921**  

_Nota: a causa delle politiche di sicurezza di GitHub, le icone CSS non sono visibili nel documento. Leggere questo file all'esterno di GitHub per visualizzare le icone dei pulsanti a cui si fa riferimento._
## Indice
[1. Il sistema Mentcare](#il-sistema-mentcare)  
[2. Ingegneria dei requisiti: scenari](#ingegneria-dei-requisiti-scenari)  
[3. Ulteriori requisiti soddisfatti](#ulteriori-requisiti-soddisfatti)  
[4. Testing](#testing)  
[5. Tecnologie impiegate](#tecnologie-impiegate)  
[6. Avvio del progetto](#avvio-del-progetto)  

## Il sistema Mentcare
**Descrizione generale**  
Il sistema in oggetto deve raccogliere e gestire le informazioni dei pazienti in cura presso alcune strutture specializzate nel settore della salute mentale. Tra le informazioni gestite, vi sono tutti i dati personali del paziente, le cure ricevute e i farmaci prescritti dai medici. Inoltre, vanno memorizzate informazioni riguardo quest'ultimi: dei medici, infatti, oltre ai dati personali, è necessario conoscere la loro specializzazione e la loro agenda contenente gli appuntamenti prenotati. Gli appuntamenti, infine, riguardano un paziente ed un medico, si svolgono in una clinica ed hanno una data associata.

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

## Ingegneria dei requisiti: scenari
I requisiti di progetto prevedono l'implementazione di un singolo modulo del sistema. Di seguito, verranno analizzati i requisiti e le scelte progettuali del modulo relativo al receptionist. 
### 1. Visualizzare i prossimi appuntamenti
**Obiettivo**: Il receptionist deve essere in grado di visualizzare rapidamente i prossimi appuntamenti.  
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage `[ / ]`. Per la data odierna, inoltre, devono essere presenti degli appuntamenti fissati.  
**Flusso normale**: Il receptionist trova una sezione denominata "Appuntamenti di oggi", la quale contiene i dettagli degli appuntamenti della giornata attuale. Per ogni appuntamento, vi è la possibilità di ottenere maggiori dettagli e/o di modificarlo, facendo uso dei due appositi pulsanti: rispettivamente, uno grigio <i class="fas fa-search-plus"></i> e uno blu <i class="fas fa-edit"></i>.  
**Flusso alternativo**: Il receptionist trova una sezione denominata "Appuntamenti di oggi", la quale contiene i dettagli degli appuntamenti della giornata attuale. Nell'angolo superiore destro è presente un pulsante "<i class="fas fa-search-plus"></i> Vedi tutti". Il receptionist esegue il click su di esso e viene rediretto alla pagina degli appuntamenti `[ /appuntamenti ]`, dove può visualizzare tutti gli appuntamenti futuri, ordinati dal più prossimo al più lontano.  
**Postcondizioni**: Il receptionist ha a disposizione la lista degli appuntamenti futuri.

### 2. Ricevere nuove richieste di cura
**Obiettivo**: Il receptionist deve gestire le nuove richieste di cura da parte dei nuovi pazienti.  
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage `[ / ]`. Devono essere presenti, inoltre, nuove richieste di cura.  
**Flusso normale**: Il receptionist trova una sezione denominata "Nuove richieste", la quale contiene i dettagli delle richieste ricevute (data, nome e cognome del paziente e la descrizione di massima della problematica). Per ogni richiesta, ha la possibilità di accettare <i class="fas fa-check"></i> o rifiutare <i class="fas fa-times"></i>. Nel caso il receptionist volesse accettarla, esegue il click sul pulsante verde <i class="fas fa-check"></i>. Viene rediretto alla pagina contenente tutti i dettagli (non modificabili) del paziente (Nome, Cognome, Codice Fiscale, Descrizione problematica(quest'ultima editabile per correggere eventuali errori di battitura)) `[ /nuovo-paziente ]`. All'interno di questa pagina, il receptionist seleziona: il medico da associare al paziente mediante il menù a tendina "Medico", un flag per classificare il paziente come pericoloso o meno e digita una "categoria" di problematica. Una volta valorizzati il campi, il receptionist clicca sul pulsante "<i class="fas fa-save"></i> Salva", confermando l'accettazione della richiesta.  
**Flusso alternativo**: NA  
**Postcondizioni**: Il nuovo paziente è stato inserito nel sistema ed il receptionist è collegato alla pagina dei dettagli del paziente appena aggiunto `[ /paziente/{id} ]`. Inoltre, la richiesta non compare più nella sezione "Nuove richieste".

### 3. Aggiungere nuovo paziente
**Obiettivo**: Il receptionist deve inserire nuovi pazienti all'interno del sistema.  
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage `[ / ]`.  
**Flusso normale**: Il receptionist deve accedere alla pagina dei pazienti: a tale scopo, esegue il click sul link "pazienti" posto all'interno della Navigation Bar collocata in cima alla pagina. Viene quindi rediretto alla pagina dei pazienti `[ /pazienti ]`, dove trova la lista di tutti i pazienti. Il receptionist deve cliccare sul pulsante verde "<i class="fas fa-plus"></i> Nuovo" posto nell'angolo superiore destro. Viene così rediretto alla pagina dedicata all'aggiunta di un nuovo paziente `[ /nuovo-paziente ]`. Qui, quindi, procede con la compilazione dei campi del form presente e, una volta valorizzato l'intero form, il receptionist deve cliccare il pulsante "<i class="fas fa-save"></i> Salva" per confermare l'inserimento del paziente nel sistema.   
**Flusso alternativo**: NA    
**Postcondizioni**: Il nuovo paziente è stato inserito nel sistema ed il receptionist è collegato alla pagina dei pazienti `[ /pazienti ]`.

### 4. Aggiungere nuovo appuntamento
**Obiettivo**: Il receptionist deve inserire nuovi appuntamenti all'interno del sistema.  
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage `[ / ]`.  
**Flusso normale**: Il receptionist deve accedere alla pagina degli appuntamenti: a tale scopo, esegue il click sul link "appuntamenti" posto all'interno della Navigation Bar collocata in cima alla pagina. Viene quindi rediretto alla pagina degli appuntamenti `[ /appuntamenti ]`, dove trova la lista di tutti gli appuntamenti. Il receptionist deve cliccare sul pulsante verde "<i class="fas fa-plus"></i> Nuovo" posto nell'angolo superiore destro. Si apre quindi una finestra modale, in cui il receptionist deve scegliere un medico o un paziente a cui associare il nuovo appuntamento. Una volta effettuata la scelta, il receptionist clicca su "<i class="fas fa-check"></i> Conferma Medico" o "<i class="fas fa-check"></i> Conferma Paziente" rispettivamente. Viene così rediretto alla pagina dedicata all'aggiunta di un nuovo appuntamento `[ /nuovo-appuntamento ]`. Qui, il campo "medico" è valorizzato e disabilitato se allo step precedente il receptionist ha confermato il medico. Se invece ha confermato il paziente, sia il campo "medico" che il campo "paziente" sono valorizzati e disabilitati. Il receptionist quindi procede con la compilazione dei campi del form presente e, una volta valorizzato l'intero form, il receptionist deve cliccare il pulsante "<i class="fas fa-save"></i> Salva" per confermare l'inserimento dell'appuntamento nel sistema.  
**Flusso alternativo**: L'aggiunta di un appuntamento può avvenire anche in altre due modalità:
1) A partire dalla pagina di dettaglio di un medico `[ /medico/{id} ]`: Il receptionist esegue il click sul link "medici" posto all'interno della Navigation Bar collocata in cima alla pagina. Viene quindi rediretto alla pagina dei medici `[ /medici ]` dove trova la lista di tutti i medici. Ogni entry della lista predispone di un pulsante maggiori dettagli <i class="fas fa-search-plus"></i>, di colore grigio. Il receptionist clicca su di esso e viene rediretto al dettaglio del medico `[ /medico/{id} ]`. All'interno della sezione "appuntamenti", il receptionist clicca sul pulsante verde "<i class="fas fa-plus"></i> Nuovo" per aggiungere un nuovo appuntamento. Viene così rediretto alla pagina dedicata all'aggiunta di un nuovo appuntamento `[ /nuovo-appuntamento ]`, dove questa volta troverà il campo "medico" precompilato e disabilitato. Il campo "paziente" conterrà solo i pazienti associati al medico. Il receptionist quindi procede con la compilazione dei campi del form presente e, una volta valorizzato l'intero form, deve cliccare il pulsante "<i class="fas fa-save"></i> Salva" per confermare l'inserimento dell'appuntamento nel sistema.
2) A partire dalla pagina di dettaglio di un paziente `[ /paziente/{id} ]`: Il receptionist esegue il click sul link "pazienti" posto all'interno della Navigation Bar collocata in cima alla pagina. Viene quindi rediretto alla pagina dei pazienti `[ /pazienti ]` dove trova la lista di tutti i pazienti. Ogni entry della lista predispone di un pulsante maggiori dettagli <i class="fas fa-search-plus"></i>, di colore grigio. Il receptionist clicca su di esso e viene rediretto al dettaglio del paziente `[ /paziente/{id} ]`. All'interno della sezione "appuntamenti", il receptionist clicca sul pulsante verde "<i class="fas fa-plus"></i> Nuovo" per aggiungere un nuovo appuntamento. Viene così rediretto alla pagina dedicata all'aggiunta di un nuovo appuntamento `[ /nuovo-appuntamento ]`, dove questa volta troverà i campi "medico" e "paziente" precompilati e disabilitati. Il receptionist quindi procede con la compilazione dei campi del form presente e, una volta valorizzato l'intero form, deve cliccare il pulsante "<i class="fas fa-save"></i> Salva" per confermare l'inserimento dell'appuntamento nel sistema.   

**Postcondizioni**: Il nuovo appuntamento è stato inserito nel sistema ed il receptionist è collegato alla pagina degli appuntamenti `[ /appuntamenti ]`.

### 5. Visualizzare dettagli appuntamento
**Obiettivo**: Il receptionist deve visualizzare i dettagli degli appuntamenti.
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage `[ / ]`. Inoltre, nel sistema esistono degli appuntamenti con data maggiore o uguale alla data odierna.  
**Flusso normale**: Ogniqualvolta si presenti una tabella contenente gli appuntamenti (`[ / ]`, `[ /appuntamenti ]`, `[ /paziente/{id} ]`, `[ /medico/{id} ]`), ogni singola entry è dotata di un pulsante color grigio contenente una lente di ingrandimento <i class="fas fa-search-plus"></i>. Il receptionist esegue il click su di essa, e viene rediretto alla pagina di dettaglio dell'appuntamento `[ /appuntamento/{id} ]`.  
**Flusso alternativo**: NA    
**Postcondizioni**: Il receptionist è collegato alla pagina di dettaglio dell'appuntamento `[ /appuntamento/{id} ]` e può visualizzare tutti i dati ad esso associati (Medico, Paziente, Data, Clinica e Comunicazioni)

### 6. Inviare comunicazioni associate ad un appuntamento
**Obiettivo**: Il receptionist deve inviare, al medico e/o al paziente, comunicazioni relative agli appuntamenti.
**Precondizione**: Il receptionist ha effettuato l'accesso al sistema ed è stato correttamente autenticato. E' quindi collegato alla homepage `[ / ]`.  Esiste, inoltre, almeno un appuntamento nel sistema.    
**Flusso normale**: Ogniqualvolta si presenti una tabella contenente gli appuntamenti (`[ / ]`, `[ /appuntamenti ]`, `[ /paziente/{id} ]`, `[ /medico/{id} ]`), ogni singola entry è dotata di un pulsante color grigio contenente una lente di ingrandimento <i class="fas fa-search-plus"></i>. Il receptionist esegue il click su di essa, e viene rediretto alla pagina di dettaglio dell'appuntamento `[ /appuntamento/{id} ]`. Nella sezione "Comunicazioni" è presente un pulsante verde "<i class="fas fa-plus"></i> Nuova". Il receptionist esegue il click su di esso, e viene rediretto alla pagina di invio comunicazione `[ /appuntamento/{id}/nuova-comunicazione ]`. Qui, digita il testo della comunicazione, successivamente seleziona i checkbox relativi ai destinatari della comunicazione e, infine, clicca sul pulsante "<i class="fas fa-paper-plane"></i> Invia" posto nell'angolo superiore destro.  
Attenzione: una comunicazione deve sempre avere almeno un destinatario.  
**Flusso alternativo**: NA    
**Postcondizioni**: La comunicazione viene inviata e il receptionist viene rediretto alla pagina di dettaglio appuntamento `[ /appuntamento/{id} ]`, dove può vedere la presenza della nuova comunicazione all'interno della sezione dedicata.

## Ulteriori requisiti soddisfatti
Oltre agli scenari descritti in precedenza, sono stati soddisfatti ulteriori requisiti, impliciti ed espliciti, raccolti dalla descrizione del sistema Mentcare. In particolare, si vedano di seguito i vari requisiti funzionali e non funzionali soddisfatti. 
### 1. Cliniche e medico associato
Facendo riferimento a *"I pazienti non necessitano di cure ospedaliere dedicate, ma devono frequentare regolarmente cliniche specializzate e incontrare un medico che abbia una conoscenza dettagliata dei loro problemi."*, per lo sviluppo del modulo è stato deciso di gestire sia la clinica che il medico associato. 
In particolare:
- Ogni appuntamento prevede, tra le varie informazioni, anche una clinica di riferimento, che, per motivi di facilità implementativa, è rappresentata da una stringa contenuta nel modello *Appointment*.
L'idea alla base è di associare una clinica ad ogni appuntamento, in modo tale che sia medico che paziente possano sempre essere consapevoli del luogo in cui si svolgerà la visita. 
- Ogni paziente deve sempre essere associato ad uno e un solo medico. Questa associazione è obbligatoria, e deve essere svolta con criterio, in quanto deve essere associato il medico più pertinente in risposta alle problematiche del paziente. A tale scopo, quindi, il campo per la scelta del medico (posti in aggiunta/modifica paziente) contiene non solo il nome e cognome di esso, ma anche la sua specializzazione. In questo modo, sulla base della descrizione della problematica del paziente, il receptionist può associare il medico più appropriato. 
### 2. Layout responsive per dispositivi mobili
In risposta a *"Il sistema deve essere accessibile da host remoti, i quali possono essere di diverse tipologie: PC Desktop, Laptop, Tablet, Smartphone, ..."*, si è deciso di progettare l'interfaccia grafica a partire dal layout mobile, per poi passare al layout tablet ed infine adattare il tutto al layout desktop. Si è quindi posta molta attenzione ai comportamenti Responsive della parte Front-End del sistema.  
Il sistema, inoltre, in una situazione reale, prevede un database centrale, posto su un server (o eventualmente distribuito) accessibile da qualsiasi dispositivo remoto.  
L'applicativo è stato infine sviluppato in ambiente Web, rendendolo quindi accessibile da qualsiasi dispositivo dotato di un Web Browser.  
### 3. Riservatezza delle cartelle cliniche
Per quanto riguarda la specifica *"Non tutti gli utenti del sistema hanno accesso alla cartella clinica del paziente: questa, infatti, può essere consultata solo dal medico e dal paziente stesso."*, si è pensato di non rendere visibili le informazioni cliniche del paziente all'interno della sua pagina di dettaglio. Tali informazioni, infatti, non sono e non devono essere in perimetro del Receptionist.   
Una possibile idea, non sviluppata perchè non ritenuta di fondamentale importanza, vede un pulsate, all'interno della pagina di dettaglio del paziente, che, al click, apra un piccolo form di "richiesta accesso alla cartella clinica". Tale form è diretto al medico associato, il quale dovrà valutare la richiesta ed approvarla o meno. Questa funzionalità potrebbe essere utile nel caso in cui sia necessario trasferire le informazioni all'esterno del sistema Mentcare, ad esempio per inoltrarle ad un medico esterno al network. 
### 4. Reperibilità
Una possibile soluzione a "*Ne segue che il paziente (o il tutore) debba poter essere contattato da parte del medico o della segreteria con delle comunicazioni di qualsiasi genere.*" risiede nella funzionalità di invio comunicazioni, descritta anche nello Scenario 6. In questo modo, un Receptionist può sempre comunicare con medico e paziente, o eventualmente il suo tutor.
### 5. Pericolosità dei pazienti
Un requisito importante risiede nel fatto che "*Alcuni pazienti potrebbero presentare delle problematiche gravi, che li renda classificabili come "pericolosi". In tal caso, l'intero staff (medico e non) deve essere sempre avvertito, in maniera tale che si possano prendere le dovute precauzioni a tutela del medico, del personale di segreteria e degli altri pazienti.*".
A tale scopo, il Model del paziente contiene un flag booleano che indica se il paziente è pericoloso o meno. Tale flag può essere gestito sia dal medico, che dal Receptionist, e, ove attivo per un paziente, farà comparire un simbolo di avvertimento rosso <i class="fas fa-exclamation-triangle text-danger" title="Pericoloso"></i>, accanto ad ogni occorrenza del paziente, all'interno dell'intero sistema (ad esempio, nel dettaglio di un appuntamento). 
### 6. Traccia delle eliminazioni
Per questioni legali, "*si deve sempre tenere traccia delle azioni svolte dal medico all'interno della cartella clinica, le quali, in caso di eventuale indagine di polizia o revisione giudiziaria, devono poter essere estratte.*". In risposta, quindi, si è pensato di implementare le azioni di DELETE di appuntamenti/pazienti/richieste come azioni di PUT, le quali andranno semplicemente a disattivare i record coinvolti. Infatti, ogni paziente e ogni appuntamento contengono all'interno del Model un flag booleano che indica se il record è attivo o meno. A Front-End, sono visualizzati solo i dati attivi, mentre quelli disattivati sono esclusi, ma presenti a Back-End e recuperabili per eventuali estrazioni (ed utilizzabili in possibili applicazioni amministrative). 
### 7. Funzionalità secondarie implementate
Per rendere il modulo sviluppato usabile, è stato necessario implementare ulteriori route e funzionalità all'interno del sistema.  
In particolare, sono presenti le routes utili alla visualizzazione dei dettagli di un medico e di un paziente. Inoltre, vi sono le routes per la visualizzazione dei pazienti, degli appuntamenti e dei medici.  
Per comodità di navigazione, inoltre, ogniqualvolta appaia il nome e cognome di un medico/paziente, effettuando un click su di esso si può accedere direttamente a maggiori dettagli.  
E' inoltre possibile modificare ed eliminare un appuntamento. 

### Altre scelte progettuali
Nel corso dello sviluppo del modulo sono state prese delle decisioni progettuali a scopo unico di facilitarne e semplificarne l'implementazione. Ove il sistema dovesse essere realmente implementato, allora tali assunzioni non verrebbero prese. 
- **Data**: Gli appuntamenti e le richieste sono gestite solamente con una data, nel formato gg/mm/aaaa. Ovviamente, in una situazione reale, si dovrebbe tenere conto anche dell'ora, in formato hh:mm, ma per lo scopo del progetto si è deciso di ometterla.
- **Controlli sugli appuntamenti**: Per semplicità, non è stato effettuato alcun controllo in merito a possibili appuntamenti duplicati e/o in conflitto. Si assume che il Receptionist effettui le dovute verifiche prima di prenotare appuntamenti incompatibili. 
- **Clinica**: Come già detto, la clinica è gestita mediante una stringa di testo libero, ma in un reale sistema completo, potrebbe essere gestita con un Model a parte, di nome *Clinic*, in cui al suo interno siano contenuti diversi dati. In questo modo, risulterebbe più semplice effettuare analisi e verifiche circa l'approvvigionamento dei medicinali e la disponibilità di slot per ulteriori appuntamenti. 
- **Un solo medico per paziente**: Si è assunto che un paziente abbia uno e un solo medico associato. Esso può essere cambiato, ma non potrà mai essere seguito da due medici nello stesso periodo di tempo. 

## Testing
### Test effettuati
Sono stati scritti Unit Test e Acceptance Test, di seguito dettagliati.
La coverage totale ammonta a 100% (100/100) sui metodi e 95% (322/337) sulle righe di codice totali.
#### Unit Test
Sono stati eseguiti Unit test per:
- Models: 64 test con coverage 100% (72/72) sui metodi e 100% (102/102) sulle righe di codice totali.
- Repositories: 6 test con coverage 85% (17/20) sui metodi e 86% (204/237) sulle righe di codice totali;
- Utils: 10 test con coverage 100% (8/8) sui metodi e 100% (61/61) sulle righe di codice totali;

#### Acceptance Test
Sono stati eseguiti gli Acceptance Test per gli scenari descritti in precedenza. Inoltre, sono stati inseriti alcuni test extra utili a coprire tutti i controller.  
In particolare:
- Scenario 1: 3 test.
- Scenario 2: 1 test.
- Scenario 3: 1 test.
- Scenario 4: 3 test.
- Scenario 5: 4 test.
- Scenario 6: 1 test.
- Extra: 3 test.  

TOTALE: 16 test con coverage 100% (20/20) sui metodi e 91% (158/172) sulle righe di codice totali;

### Coverage
Il report dettagliato della coverage dei test è contenuto nel file `\MentcareApplication\Coverage\index.html` .

## Tecnologie impiegate
Lo sviluppo del progetto ha richiesto l'uso di diverse tecnologie, di seguito descritte:
- **Java**: Linguaggio di programmazione utilizzato per la scrittura della business logic.
- **Spring-MVC**: Framework Java impiegato per la creazione di applicazioni web secondo il pattern Model View Controller.
- **H2Database**: DBMS scritto in Java eseguito in modalità "in-memory".
- **Gradle**: Build Automation Tool.
- **JPA**: Java Persistence API, Framework Java che si occupa dell'interazione e della persistenza dei dati nel DBMS d'appoggio.
- **Junit**: Framework di Testing per applicazioni Java (Unit Test).
- **Selenium**: Framework di Testing per la validazione di una applicazione Java (Acceptance Test).
- **Thymeleaf**: Template engine per il rendering delle Views.
- **HTML5**: Linguaggio di markup per la definizione dell'interfaccia grafica.
- **Bootstrap5**: Framework CSS per la definizione dello stile dell'interfaccia grafica.
- **IntelliJ IDEA**: IDE di sviluppo impiegato per la parte di Backend, esecuzione dei test e calcolo della coverage.
- **Visual Studio Code**: Editor di sviluppo impiegato per la parte Frontend.
- **Git & GitHub**: Sistema di controllo di versione del codice e gestore remoto di repositories. 

## Avvio del progetto
Di seguito la procedura per l'avvio del progetto: 
- Clonare la repository con `git clone https://github.com/SimoneFerrari99/MentcareApplication.git`
- Aprire il progetto con IntelliJ IDEA
- Nel menù di Gradle, eseguire la Task `application > bootRun`
- Collegarsi tramite un web browser (ad es. Google Chrome) all'indirizzo `http://http://localhost:8080/`


<link
    rel="stylesheet"
    href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous"
/>
