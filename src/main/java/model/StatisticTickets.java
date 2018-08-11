package model;

import java.util.LinkedList;
import java.util.List;

public class StatisticTickets {
    private List<Ticket> listTicketWin = new LinkedList<Ticket>();
    private List<Ticket> listTicketTotal = new LinkedList<Ticket>();
    private int numberTirage;

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

        String information = "Номер тиража " + numberTirage + "\r\n" +
                "Список билетов \r\n " +
                "Номер билета Кол. совп. Выиграл Тур\r\n" + informTicket.toString();

        return information;
    }
}
