package game.core;

import java.util.Scanner;
import view.terminal.TerminalGame;

public class GameController {
    private final Grid grid; // Speicherung Spielfeld
    private final TerminalGame gameView; // Speicherung View
    private boolean running = false;
    private static final long FRAME_DELAY_MS = 200; // 5 Updates pro Sekunde
    private final Scanner scanner;

    public GameController(Grid grid, Scanner scanner) {
        this.grid = grid;
        this.gameView = new TerminalGame(grid);
        this.scanner = scanner;
    }

    public void start() { // Start des Spiels
        grid.spawn_snake();
        grid.spawn_food();
        grid.syncSnakeToGrid();
        running = true;

        Thread loopThread = new Thread(this::runLoop);
        loopThread.setDaemon(true);
        loopThread.start();

        steuerung();
    }

    public void stop() {
        running = false; // Zustand der Spielschleife
    }

    private void runLoop() {
        while (running) { // Beenden des Spiels
            update();
            render();

            try {
                Thread.sleep(FRAME_DELAY_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }
    }

    private void update() {
        grid.snake_move();
        grid.syncSnakeToGrid();
        if (grid.chech_colision()) {
            System.out.println("Game Over!");
            stop();
        }
        grid.eat_food();
    }

    private void render() {
        for (int i = 0; i < 50; i++) {
            System.out.println(); // Bildschirm leeren
        }

        gameView.render();
        System.out.print(">");
    }

    public void steuerung() {
        System.out.println("Steuerung: W = Hoch, S = Runter, A = Links, D = Rechts, Q = Beenden (jeweils + ENTER)");

        while (running) {
            String eingabe = scanner.nextLine().toLowerCase();
            switch (eingabe) {
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
                    break;
            }
        }
    }
}
