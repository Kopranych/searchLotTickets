package model;

import java.util.HashSet;
import java.util.Set;

public class TicketRow {
    private int numberRow;
    private Set<Integer> setNumber;

    private Set<Cell> setCell = new HashSet<>();

    public TicketRow(Set<Integer> setNumber) {
        this.setNumber = setNumber;
        for (Integer i : setNumber) {
            setCell.add(new Cell(i));
        }
    }

    public int getNumberRow() {
        return numberRow;
    }

    public Set<Cell> getSetCell() {
        return setCell;
    }

    public void setNumberRow(int numberRow) {
        this.numberRow = numberRow;
    }

    public Set<Integer> getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(Set<Integer> setNumber) {
        this.setNumber = setNumber;
    }
}
