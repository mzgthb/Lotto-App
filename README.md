# Lotto-App

### Aufgabenstellung: Eine Java-Applikation entwickeln, welche Tippreihen für Lottospieler generieren kann.

#### Anforderungen:
- **Kommando-Zeile / Terminalzeile** als Aufruf und Praesentationsmittel
- Bestimmen, welche Tippreihe generiert werden soll (**6aus49** oder **5aus50** plus **2aus10**)
  - Auswahl des Spiels wird ueber feste Namensparamenter ausgewaehlt
  - Wird dieser Parameter nicht definiert, soll Lotto **6aus49** ausgewaehlt werden
  - Ungueltiger Parameter fuehrt zu einer Fehlermeldung mit den gueltigen Parameter
- Bis zu sechs Unglueckszahlen eingeben, welche bei der Generierung ausgeschlossen werden
  - Uebergabe der Unglueckszahlen ueber Aufrufparameter
  - Unglueckszahlen werden geprueft, ob diese innerhalb der Grenzen des gueltigen Zahlenraums liegen
  - Ungueltige Unglueckszahl fuehrt zu einer Fehlermeldung, welche den gueltigen Zahlenraum anzeigt
  - Unglueckszahlen werden gespeichert, um diese nach dem Schließen trotzdem weiterzuverwenden
  - Unglueckszahlen werden unabhaengig der Lotterie beruecksichtigt
- Neue Unglueckszahlen festlegen koennen
- Gespeicherte Unglueckszahlen loeschen koennen
- Zufaellig genererierte Tippreihe so ausgeben, dass man diese uebertragen kann
  - Sortierte, aufsteigende Reihenfolge der Zahlen der Tippreihe
  - Erkennbare Trennung fuer die Eurojackpot-Reihe erst **5aus50 dann** **2aus10**
  
## Dokumentation fuer Programmierer
Datei: *LottoApp.java*

##### Diese Klasse ist die "Haupt"-Klasse, die dem Benutzer erlaubt, zwischen zwei verschiedenen Lotterien zu waehlen und dann eine Ziehung durchzufuehren (**6aus49** oder **Eurojackpot**).
 
##### Die *init-Lottery*-Methode initialisiert die Lotterie, indem sie den Benutzer auffordert, eine Wahl zu treffen, und dann die entsprechende Enum-Instant (*LOTTO* oder *EUROJACKPOT*) zurueckgibt. Wenn der Benutzer jedoch eine ungueltige Auswahl trifft, wird er aufgefordert, erneut zu waehlen, bis eine gueltige Auswahl getroffen wird.
- 6aus49
- Eurojackpot
- Enter druecken (waehlt 6aus49)

Datei: *Generator.java*
##### Es handelt sich die Interface-Klasse mit allen Grundlegenden Funktionen fuer die Generierung der Tippreihen.
- start()
- getArray(int[] arr)
- checkArrayValues()
- askForUnluckyNumbers()
- addUnluckyNumbers()
- checkUnluckyNumbers()
- saveUnluckyNumbers()
- askGenerateUnluckyNumbers()
- generateNumbers(boolean unluckyNumbers)

Datei: *Lotto.java*

##### In dieser Klasse ist die Logik mit den Funktionen fuer die Generierung der Tippreihe, hierbei handelt es sich um die Genierung der Tippreihe fuer die Lotterie **6aus49**. Es werden zwei Arrays mit der groesse der generierbaren Zahlen (6) und der moeglichen Unglueckszahlen (6).

- start()
  - startet die Lotterie
- getArray(int[] arr)
  - gibt das Array in einer einfach und sortierten Darstellung aus
- checkArrayValues()
  - prueft die Arrays nach doppelten Zahlen, wenn vorhanden, dann wird diese Zahl neu generiert
  - fuegt der "log.txt"-Datei die aktuell generierte Tippreihe hinzu
- askForUnluckyNumbers()
  - prueft, ob Unglueckszahlen bereits in der "unluckynumbers.txt" vorhanden sind, wenn diese vorhanden sind, werden diese angezeigt und der Benutzer wird gefragt, ob er diese verwenden oder loeschen moechte
  - wenn der Benutzer diese nutzen moechte, werden die Zahlen in dem Array der Unglueckszahlen abgespeichert
  - ausserdem werden die Zahlen in die "log.txt"-Datei hinzugefuegt
  - wenn der Benutzer diese loeschen moechte, wird die gesamte "unluckynumbers.txt"-Datei geleert
- addUnluckyNumbers()
  - abfragen, wie viele Unglueckszahlen der Benutzer eingeben moechte (0 bis 6)
  - prueft, ob der Benutzer eine Zahl zwischen 0 und 6 eingegeben hat
  - wenn ja, dann wird der Benutzer aufgefordert seine Unglueckszahlen einzugeben
  - wenn der Benutzer seine Unglueckszahlen erfolgreich eingegeben hat, werden diese in der Datei "unluckynumbers.txt" abgespeichert
  - am Ende werden die zufaelligen Zahlen generiert
- checkUnluckyNumbers()
  - prueft, ob sich in der Datei "unluckynumbers.txt" Unglueckszahlen befinden
  - gibt "true" oder "false" zurueck
- saveUnluckyNumbers()
  - speichert alle Zahlen aus dem Array "unluckyNumbers" in die Datei "unluckynumbers.txt"
- askGenerateUnluckyNumbers()
  - Abfrage, ob der Benutzer Unglueckszahlen eingeben moechte
- generateNumbers(boolean unluckyNumbers)
  - prueft, ob Unglueckszahlen verwendet werden sollen
  - generiert Zahlen zwischen 0 und 49
  - beruecksichtigt Unglueckszahlen
  
Datei: *Eurojackpot.java*

##### In dieser Klasse ist die Logik mit den Funktionen fuer die Generierung der Tippreihe, hierbei handelt es sich um die Genierung der Tippreihe fuer die Lotterie **Eurojackpot**. Es werden drei Arrays mit der groesse der generierbaren Zahlen (5) und (2). Diese Klasse erbt von der Oberklasse "*Lotto.java*".

##### Grundsaetzlich ist diese Klasse aequivalent zu der "*Lotto.java*"-Klasse aufgebaut, mit dem Unterschied, dass zwei Arrays verwendet werden, welche eine unterschiedliche groesse aufweisen (**5aus50** und **2aus10**).

Datei: *LottoTest.java und EurojackpotTest.java*

##### Mit den Unittests prueft man, ob ausgewaehlte Funktionen so funktionieren, wie man diese erwartet. In diesem Fall uerprueft man, ob die Werte in dem Arrays mit den generierten Zahlen zwischen 0 und 49 liegt (6aus49), 0 und 50 + 0 aund 20 (Eurojackpot), außerdem ob die Werte einzigartig sind und es nicht um doppelte Werte handelt. Zudem gibt es eine Funktion zum Pruefen der Unglueckszahlen, diesen Wert kann man entweder auf "true" oder "false" einstellen und somit die Funktion der Methode pruefen.

![Klassendiagramm](https://user-images.githubusercontent.com/126078900/224562850-9e5d3ec0-741d-432f-977a-eb8674122e1d.png)
 
## Dokumentation fuer Anwender
1. Anwendung starten
2. Auswaehlen zwischen
  - 6aus49
  - Eurojackpot
3. Auswaehlen, ob Unglueckszahlen ausgewaehlt werden sollen (Ja/Nein)
  - Wenn bereits Unglueckszahlen vorhanden sind, wird man gefragt, ob man diese verwenden oder loeschen moechte (Ja/Nein/Loeschen)
  - Bestaetigt man mit "Nein", wird man gefragt, ob man neue Unglueckszahlen waehlen moechte
  - Mit "Loeschen" loescht man alle Unglueckszahlen aus der Datei "unluckynumbers.txt"
4. Mit "Ja" hat man die Moeglichkeit bis zu sechs Unglueckszahlen zu waehlen (zwischen 0 und 50)
5. Hat man diese eingegeben, werden Tippreihen fuer die jeweilige Lottery generiert

In der Datei "**log.txt**" befinden sich Informationen zu der jeweiligen Generierung.
- Datum und Uhrzeit
- Unglueckszahlen
- Generierte Tippreihe
