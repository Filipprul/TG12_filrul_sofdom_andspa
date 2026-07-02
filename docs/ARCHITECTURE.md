# Software-Architektur
Dieses Dokument beschreibt die Klassenstruktur und die Aufteilung unseres Snake-Spiels in drei logische Schichten (Model-View-Controller).
Um dieses Markdown richtig zusehen werden diese Shritte benötigt:
1. Markdown Preview Mermaid Support - Extension herunterladen.
2. Mit `Strg + Shift + V` (Windows) oder `Cmd + Shift + V` (Mac) öffnen.

## Jetzige Software-Architektur
### Klassendiagramm
Klassendiagramm der jetzigen Projektversion.

```mermaid
classDiagram
    %% Vererbungshierarchie (game.objects)
    class Objekt {
        #int x
        #int y
        #int value
        #int previus_x
        #int previus_y
        +get_value() int
        +get_x() int
        +get_y() int
        +get_previus_x() int
        +get_previus_y() int
        +set_x(int x) void
        +set_y(int y) void
    }

    class Head {
        +up() void
        +down() void
        +left() void
        +right() void
    }

    class Body {
        +follow(Objekt vordermann) void
    }

    class Food {
    }

    Objekt <|-- Head
    Objekt <|-- Body
    Objekt <|-- Food

    %% Kernlogik und Verwaltung (game.core)
    class Grid {
        -ArrayList~Objekt~ snake
        -Obj[][] grid_size
        -food_index
        -max_food
        +check_collision

        +spawn_snake() void
        +spawn_food() void
        +snake_grow() void
        +moveSnake() void
    }

    Grid "1" --> "*" Objekt : verwaltet

    %% Menü und App-Start (menu / src)
    class menu {
        +getMenuScene(Stage primaryStage) Scene
    }

    class Main {
        +start(Stage primaryStage) void
        +main(String[] args) void
    }

    Main --> menu : startet
    menu ..> Grid : ruft auf (Zukunft)
```

## Finale Software-Architektur

### Klassendiagramm
Klassendiagramm der finalen Projektversion.
Wurde von AI verbessert! (Ist nicht komplett).

```mermaid
classDiagram
    %% ARCHITEKTUR-EBENEN (MODEL-VIEW-CONTROLLER)
    
    %% ====== LAYER 1: MODEL (game.objects & game.core) ======
    class Objekt {
        <<abstract>>
        # int x
        # int y
        # int value
        # int previousX
        # int previousY
        + getX() int
        + getY() int
        + getPreviousX() int
        + getPreviousY() int
        + setX(int x) void
        + setY(int y) void
    }

    class Head {
        + move(Direction dir) void
    }

    class Body {
        + follow(Objekt vordermann) void
    }

    class Food {
    }

    class Direction {
        <<enumeration>>
        UP
        DOWN
        LEFT
        RIGHT
    }

    class Grid {
        - List~Objekt~ snake
        - Food currentFood
        - int width
        - int height
        - boolean isGameOver
        + spawnSnake() void
        + spawnFood() void
        + snakeGrow() void
        + update() void
        + getSnake() List~Objekt~
        + getCurrentFood() Food
        + isGameOver() boolean
    }

    Objekt <|-- Head
    Objekt <|-- Body
    Objekt <|-- Food
    Grid o-- "1..*" Objekt : aggregates
    Head ..> Direction : uses

    %% ====== LAYER 2: VIEW (menu & view) ======
    class MenuInterface {
        <<interface>>
        + startMenu(Stage stage) void
    }

    class TerminalMenu {
        + startMenu(Stage stage) void
    }

    class JavaFXMenu {
        + startMenu(Stage stage) void
    }

    class GameView {
        <<interface>>
        + render(Grid grid) void
        + showGameOver() void
    }

    class TerminalView {
        + render(Grid grid) void
        + showGameOver() void
    }

    class JavaFXView {
        + render(Grid grid) void
        + showGameOver() void
    }

    MenuInterface <|.. TerminalMenu
    MenuInterface <|.. JavaFXMenu
    GameView <|.. TerminalView
    GameView <|.. JavaFXView

    %% ====== LAYER 3: CONTROLLER (game.controller & Main) ======
    class GameController {
        - Grid grid
        - GameView view
        - Direction currentDirection
        + startGameLoop() void
        + handleInput(String input) void
    }

    class Main {
        + start(Stage primaryStage) void
        + main(String[] args) void
    }

    Main --> MenuInterface : instantiates
    GameController --> Grid : controls
    GameController --> GameView : updates
```

## Technischer Arbeitsablauf & Abhängigkeiten (Development Pipeline)

```mermaid
graph TD
    %% Phase 1
    A[#9 Spielfeld / Grid] -->|Basis für Schlangen-Daten| B[#18 Snake-Körper & Objekte]
    B -->|Basis für Bewegung| C[#10 Snake-Bewegung]
    
    %% Phase 2
    C -->|Liefert den Takt für die Bewegung| D[#15 Game-Loop]
    E[#19 Core-Interfaces] -->|Definiert Eingabe-Schnittstelle| F[#16 Tastatursteuerung]
    D -->|Verbindet Takt mit Steuerung| F
    
    %% Phase 3
    F -->|Schlange kann gezielt gesteuert werden| G[#12 Food-Spawning]
    G -->|Essen existiert im Raum| H[#11 Kollisionserkennung]
    H -->|Kollisionen lösen Event aus| I[#13 Score-Logik & Wachstum]
    
    %% Phase 4
    I -->|Terminal-Version läuft komplett| J[#14 Grafische Darstellung JavaFX]
    J --> K[#4 Menü & Screens]
    K --> L[#2 #3 #5 Zusatzfeatures]

    style A fill:#f96,stroke:#333,stroke-width:2px
    style B fill:#f96,stroke:#333,stroke-width:2px
    style E fill:#f96,stroke:#333,stroke-width:2px
```

## Dynamischer Programmablauf (Runtime Sequence)
Der zeitliche Ablauf des Spiels ist streng taktgesteuert (Tick-basiert) und wird vollständig vom `GameController` koordiniert. Dadurch wird eine saubere Synchronisation zwischen Benutzereingabe, physikalischer Berechnung und grafischer Anzeige erzwungen.

### Der detaillierte Takt-Zyklus (Game Loop Step)
Jeder einzelne Spielschritt durchläuft deterministisch die folgenden vier Phasen:

```mermaid
sequenceDiagram
    autonumber
    participant U as Spieler (User)
    participant C as GameController
    participant M as Grid (Model)
    participant V as GameView (Terminal/GUI)

    U->>C: Drückt Richtungstaste (z.B. 'W' / Pfeiltaste Oben)
    Note over C: Input wird asynchron abgefangen<br/>und Richtung (Direction) aktualisiert
    
    Note over C: Timer triggert nächsten Frame (Tick)
    
    C->>M: update()
    activate M
    Note over M: 1. Kopf bewegt sich<br/>2. Körpersegmente folgen<br/>3. Kollisionen prüfen
    M-->>C: Liefert Spielzustand (isGameOver, Score)
    deactivate M
    
    C->>V: render(grid)
    activate V
    Note over V: Liest aktuelle X/Y-Koordinaten<br/>und zeichnet das Spielfeld neu
    V-->>C: Zeichnen abgeschlossen
    deactivate V
```

## Inkrementeller Release-Plan (Versions-Roadmap)

```mermaid
graph LR
    %% Phasen-Definitionen
    subgraph P1 [Phase 1: Das Fundament]
        v01[v0.1: Alpha Engine<br/>'Unsichtbare Logik']
    end

    subgraph P2 [Phase 2: Die Absicherung]
        v10[v1.0: Terminal MVP<br/>'Voll spielbar im Terminal']
    end

    subgraph P3 [Phase 3: Das Ziel]
        v20[v2.0: JavaFX GUI<br/>'Grafik & Screens']
    end

    subgraph P4 [Phase 4: Offenes Ende]
        v31[v3.1: Schwierigkeit]
        v32[v3.2: Highscore]
        v33[v3.3: Sound]
    end

    %% Ablauf-Pfeile
    v01 -->|Logik steht| v10
    v10 -->|Meilenstein: Abgabe gesichert| v20
    
    %% Offenes Ende
    v20 -->|Option 1| v31
    v20 -->|Option 2| v32
    v20 -->|Option 3| v33
```

## Ordnerstruktur

```mermaid
graph TD
    %% Hauptverzeichnis
    Root[TG12_FILRUL_SOFDOM_ANDSPA]
    
    %% Erste Ebene
    Docs[docs]
    Res[resources]
    Src[src]
    Gitignore[.gitignore]
    Readme[README.md]
    
    Root --> Docs
    Root --> Res
    Root --> Src
    Root --> Gitignore
    Root --> Readme
    Root --> LICENSE

    %% Docs Ebene
    Docs --> Arch[ARCHITECTURE.md]
    Docs --> Req[REQUIREMENTS.md]
    Docs --> Mgmt[MANAGEMENT.md]

    %% Resources Ebene
    Res --> Audio[audio]
    Res --> Img[images]

    %% Src Ebene
    Src --> Main[Main.java]
    Src --> Game[game]
    Src --> Menu[menu]
    Src --> View[view]

    %% Game Unterordner
    Game --> Core[core]
    Game --> Obj[objects]

    %% Core Klassen
    Core --> GC[GameController.java]
    Core --> Grid[Grid.java]

    %% Objects Klassen
    Obj --> BaseObj[Objekt.java]
    Obj --> Head[Head.java]
    Obj --> Body[Body.java]
    Obj --> Food[Food.java]

    %% Menu Klassen
    Menu --> MenuClass[menu.java]

    %% View Klassen
    View --> ViewInt[GameView.java]
    View --> TermView[TerminalView.java]
    View --> FXView[JavaFXView.java]

    %% Styling für die Übersicht
    style Root fill:#4CAF50,stroke:#333,stroke-width:2px,color:#fff
    style Src fill:#2196F3,stroke:#333,color:#fff
    style Docs fill:#9C27B0,stroke:#333,color:#fff
    style Res fill:#FF9800,stroke:#333,color:#fff
```
