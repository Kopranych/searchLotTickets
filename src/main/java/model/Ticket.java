package model;

import java.util.*;

public class Ticket {
    private final int number;
    private FieldTicket topField = new FieldTicket();
    private FieldTicket botField = new FieldTicket();
    private Set<Column> setColumn = new HashSet<Column>();

    public Ticket(int number) {
        this.number = number;
        for(int i = 1; i <=9; i++){
            Column col = new Column(i);
            setColumn.add(col);
        }
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

    public FieldTicket getTopField() {
        return topField;
    }

    public FieldTicket getBotField() {
        return botField;
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

    public  void displayTicket(){
        System.out.println("Ticket number=" + number );
        System.out.println("Top field ");
        for(TicketRow row: topField.getSetTicketRow()){
            System.out.println(row.getSetNumber());
        }
        System.out.println("Bot field ");
        for(TicketRow row: botField.getSetTicketRow()){
            System.out.println(row.getSetNumber());
        }
        for(Column col: setColumn){
            System.out.println("Column " + col.getNumberColumn());
            System.out.println(col.getSetNumber());
        }
        System.out.println("");
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number=" + number +
                ", topField=" + topField +"\\n"+
                ", botField=" + botField +"\\n"+
                ", setColumn=" + setColumn +"\\n"+
                '}';
    }
}
