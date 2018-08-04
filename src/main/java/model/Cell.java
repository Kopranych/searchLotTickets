package model;

public class Cell {
    private boolean isCrossed;
    private int value;

    public Cell(int value){
        this.isCrossed = false;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (isCrossed != cell.isCrossed) return false;
        return value == cell.value;
    }

    @Override
    public int hashCode() {
        int result = (isCrossed ? 1 : 0);
        result = 31 * result + value;
        return result;
    }

    public boolean isCrossed() {
        return isCrossed;
    }

    public void setCrossed(boolean crossed) {
        isCrossed = crossed;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
