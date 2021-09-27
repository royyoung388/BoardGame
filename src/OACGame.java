public class OACGame  extends Game{
    @Override
    public void start() {

    }

    @Override
    public void newGame() {

    }

    @Override
    public boolean move(int row, int column, String mark) {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Team winner() {
        return null;
    }

    @Override
    public boolean canMove(int row, int column, String mark) {
        return false;
    }
}
