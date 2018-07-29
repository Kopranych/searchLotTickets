package model;

import java.util.HashSet;
import java.util.Set;

public class TicketRow {
    Set<Integer> setNumber;

    public TicketRow(Set<Integer> setNumber) {
        this.setNumber = setNumber;
    }

    public Set<Integer> getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(Set<Integer> setNumber) {
        this.setNumber = setNumber;
    }
}
