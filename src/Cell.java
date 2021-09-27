/**
 * One cell on the board.
 * Memory the mark
 */
public class Cell {
    private String mark;

    public Cell() {
        this("");
    }

    public Cell(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
