package core;

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
}