package model;

import java.util.LinkedList;
import java.util.List;

public class StatisticTickets {
    private List<Ticket> listTicketWin = new LinkedList<Ticket>();
    private List<Ticket> listTicketTotal = new LinkedList<Ticket>();
    private int numberTirage;
    private List<Integer> listRemainingNumbers;

    public List<Ticket> getListTicketWin() {
        return listTicketWin;
    }

    public void setListTicketWin(List<Ticket> listTicketWin) {
        this.listTicketWin = listTicketWin;
    }

    public List<Ticket> getListTicketTotal() {
        return listTicketTotal;
    }

    public void setListTicketTotal(List<Ticket> listTicketTotal) {
        this.listTicketTotal = listTicketTotal;
    }

    public List<Integer> getListRemainingNumbers() {
        return listRemainingNumbers;
    }

    public void setListRemainingNumbers(List<Integer> listRemainingNumbers) {
        this.listRemainingNumbers = listRemainingNumbers;
    }

    public int getNumberTirage() {
        return numberTirage;
    }

    public void setNumberTirage(int numberTirage) {
        this.numberTirage = numberTirage;
    }

    public String displayStatistic(List<Ticket> listTicket) {
        listTicketTotal.addAll(listTicket);
        listTicketTotal.removeAll(listTicketWin);
        StringBuffer informTicket = new StringBuffer();
        for (Ticket ticket : listTicketWin) {
            informTicket.append(ticket.getNumber() + " | " + ticket.getCountIsCrossedNumber() + " | " + ticket.win.name() + "\r\n");
        }
        for (Ticket ticket : listTicketTotal) {
            informTicket.append(ticket.getNumber() + " | " + ticket.getCountIsCrossedNumber() + " | " + ticket.win.name() + "\r\n");
        }
        informTicket.append("Оставшиеся числа тиража\r\n");
        for (Integer i : listRemainingNumbers) {
            informTicket.append(i + ", ");
        }

        String information = "Номер тиража " + numberTirage + "\r\n" +
                "Список билетов \r\n " +
                "Номер билета Кол. совп. Выиграл Тур\r\n" + informTicket.toString();

        return information;
    }
}
