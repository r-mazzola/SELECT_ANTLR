# SELECT_ANTLR
## Progetto L&amp;C
Questo progetto è stato creato come parte del corso di Linguaggi e Compilatori e tratta un utilizzo pratico del linguaggio ANTLR.
In particolare ci focalizziamo sulla realizzazione di un compilatore in grado di interpretare una query scritta in linguaggio SQL.
Nel caso specifico di questa applicazione ci occupiamo della gestione di una versione semplificata del comando SELECT che permette di filtrare i dati in base alle richieste specificate nella clausola WHERE.

Il progetto è così organizzato:
- Grammar **SELECT.g**
    - Contiene le regole ANTLR per la gestione di Lexer e Parser, che consentono lettura dei dati della query.
- Package test
    - Contiene la classe **Parser.java** che funge da main del progetto
- Package helper
    - Contiene le classi **PaserTester.java** e **ReadTableHelper.java**, in cui sono scritte le principali funzioni per la gestione del parsing della query, la lettura dei dati dalla tabella di input e la loro elaborazione
- Package manager
    - Contiene la classe **ManageExcel.java** che si occupa della scrittura dei risultati sul foglio Excel di output
