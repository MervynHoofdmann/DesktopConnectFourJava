package four;

public class TurnCounter {

    private int turnCounter = 0;

    public void incrementTurnCounter() {
        this.turnCounter += 1;
    }

    public int getTurnCounter() {
        return this.turnCounter;
    }

    public void resetTurnCounter() {
        this.turnCounter = 0;
    }
}