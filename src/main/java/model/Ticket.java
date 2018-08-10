package model;

import java.util.HashSet;
import java.util.Set;

public class Ticket {
    private final int number;
    private FieldTicket topField = new FieldTicket();
    private FieldTicket botField = new FieldTicket();
    private Set<Column> setColumn = new HashSet<Column>();
    private int countIsCrossedNumber;
    private boolean isWinFirstTour = false;
    private boolean isWinSecondfTour = false;
    private boolean isWinNextTour = false;
    private boolean isWinJackpot = false;

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

    public int getCountIsCrossedNumber(){
        for (TicketRow ticketRow : this.getTopField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (cell.getCrossed()) {
                    countIsCrossedNumber++;
                }
            }
        }
        for (TicketRow ticketRow : this.getBotField().getSetTicketRow()) {
            for (Cell cell : ticketRow.getSetCell()) {
                if (cell.getCrossed()) {
                    countIsCrossedNumber++;
                }
            }
        }
            return countIsCrossedNumber;
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

    public boolean isWinFirstTour() {
        return isWinFirstTour;
    }

    public void setWinFirstTour(boolean winFirstTour) {
        isWinFirstTour = winFirstTour;
    }

    public boolean isWinSecondfTour() {
        return isWinSecondfTour;
    }

    public void setWinSecondfTour(boolean winSecondfTour) {
        isWinSecondfTour = winSecondfTour;
    }

    public boolean isWinNextTour() {
        return isWinNextTour;
    }

    public void setWinNextTour(boolean winNextTour) {
        isWinNextTour = winNextTour;
    }

    public boolean isWinJackpot() {
        return isWinJackpot;
    }

    public void setWinJackpot(boolean winJackpot) {
        isWinJackpot = winJackpot;
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
