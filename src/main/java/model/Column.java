package model;

import java.util.HashSet;
import java.util.Set;

public class Column {
    private int id;
    private int numberColumn;
    private Set<Integer> setNumber = new HashSet<Integer>();

    public Column() {

    }

    public Column(int numberColumn) {
        this.numberColumn = numberColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Column column = (Column) o;

        if (id != column.id) return false;
        if (numberColumn != column.numberColumn) return false;
        return setNumber != null ? setNumber.equals(column.setNumber) : column.setNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + numberColumn;
        result = 31 * result + (setNumber != null ? setNumber.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberColumn() {
        return numberColumn;
    }

    public void setNumberColumn(int numberColumn) {
        this.numberColumn = numberColumn;
    }

    public Set<Integer> getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(Set<Integer> setNumber) {
        this.setNumber = setNumber;
    }

    @Override
    public String toString() {
        return "Column{" +
                "numberColumn=" + numberColumn +
                ", setNumber=" + setNumber +
                '}';
    }
}
