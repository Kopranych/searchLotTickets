package model;

import java.util.LinkedList;
import java.util.List;

public class StatisticTickets {
    List<Ticket> listTicketWin = new LinkedList<Ticket>();
    List<Ticket> listTicketTotal = new LinkedList<Ticket>();

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

    @Override
    public String toString(List<Ticket> listTicket){
        listTicketTotal.addAll(listTicket);
        listTicketTotal.;
        String information = "Список билетов "
        return null;
    }
}
