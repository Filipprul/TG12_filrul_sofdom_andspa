package core;

import java.util.Locale;
import java.util.Scanner;
public class GameController {
    private final Grid grid; // Speicherung Spielfeld
    private boolean running = false;
    private static final long FRAME_DELAY_MS = 200; // 5 Updates pro Sekunde

    public GameController(Grid grid) {
        this.grid = grid;
    }

    public void start() { // Start des Spiels
        grid.spawn_snake();
        running = true;

        Thread loopThread = new Thread(this::runLoop);
        loopThread.setDaemon(true);
        loopThread.start();
    }

    public void stop() { 
        running = false; // Zustand der Spielschleife 
    }

    private void runLoop() { 
        while (running) { // Beenden des Spiels
            processInput();
            update();
            render();

            try {
                Thread.sleep(FRAME_DELAY_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void processInput() {
        // später: Tastatureingaben auslesen
    }

    private void update() {
        grid.snake_move();
    }

    private void render() {
        // später: Spielfeld anzeigen / View aktualisieren
    }

    // Steuerung
    public void steuerung () {
        String eingabe = "";

        System.out.println("Steuerung: W = Hoch, S = Runter, A = Links, D = Rechts, Q = Beenden");

        try (Scanner scanner = new Scanner(System.in).useLocale(Locale.US)) {
            while (!eingabe.equals("q")) {
                System.out.print(">");
                eingabe = scanner.nextLine();

                switch (eingabe.toLowerCase()) {
                    case "w":
                        grid.setDirection(Direction.UP);
                        break;
                    case "s":
                        grid.setDirection(Direction.DOWN);
                        break;
                    case "a":
                        grid.setDirection(Direction.LEFT);
                        break;
                    case "d":
                        grid.setDirection(Direction.RIGHT);
                        break;
                    case "q":
                        stop();
                        System.out.println("Spiel beendet.");
                        break;
                    default:
                        System.out.println("Ungültige Eingabe. Bitte W, S, A, D oder Q verwenden.");
                }
            }
        }
    }
}