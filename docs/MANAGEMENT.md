# Risikoanalyse
- Technische Probleme mit JavaFX -> Gegenmaßnahme: Priorisierung der Terminal-Version als lauffähiges Basisprodukt
- Plannungs Probleme mit Produkt verknüpfung -> Gegenmaßnahme: Klassendiagramme

# Git-Workflow
- Main-Branch Protection: Der master-Branch enthält zu jedem Zeitpunkt ausschließlich stabilen, ausführbaren Code.
  Direktes Arbeiten oder ungetestetes Pushen auf den main-Branch ist strengstens untersagt.

- Pull Requests & Code Reviews: Wenn ein Feature fertig ist, wird ein Pull Request (PR) gestellt. Der Code darf erst in
  den master-Branch einfließen (ge-mergt werden), nachdem andere Teammitglieder den Code
  gesichtet und freigegeben haben.

# Clean Code & Programmier-Standards
- Einheitliche Benennung (Code Style): Alle Variablen, Methoden und Klassennamen werden einheitlich in CamelCase
  (Java-Standard) verfasst.

- Kein "Dead Code": Auskommentierter Code, der nicht mehr benötigt wird, oder ungenutzte Importe sind vor dem Erstellen
  eines Pull Requests restlos zu entfernen.

- Kommentierungspflicht: Komplexe mathematische Logiken, Klassen und Methoden müssen mit kurzen, prägnanten
  Zeilenkommentaren versehen werden.