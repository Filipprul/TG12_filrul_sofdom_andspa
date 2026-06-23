# Software-Architektur
Dieses Dokument beschreibt die Klassenstruktur und die Aufteilung unseres Snake-Spiels in drei logische Schichten (Model-View-Controller).
Um dieses Markdown richtig zusehen werden diese Shritte benötigt:
1. Markdown Preview Mermaid Support - Extension herunterladen.
2. Mit `Strg + Shift + V` (Windows) oder `Cmd + Shift + V` (Mac) öffnen.

## Klassendiagramm

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
        -int[][] grid_size
        -Food currentFood
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