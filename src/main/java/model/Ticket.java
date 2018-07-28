package model;

import java.util.*;

public class Ticket {
    private final int number;
    private FieldTicket topField = new FieldTicket();
    private FieldTicket botField = new FieldTicket();
    private Set<Column> setColumn = new HashSet<Column>();

    public Ticket(int number) {
        this.number = number;
    }

    public Ticket(int number, Set<Column> setColumn) {

        this.number = number;
        this.setColumn = setColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return number == ticket.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    public int getNumber() {
        return number;
    }

    public Set<Column> getSetColumn() {
        return setColumn;
    }

    public void setSetColumn(Set<Column> setColumn) {
        this.setColumn = setColumn;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number=" + number +
                ", setColumn=" + setColumn +
                '}';
    }
}
