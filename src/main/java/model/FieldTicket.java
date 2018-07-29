package model;

import java.util.HashSet;
import java.util.Set;

public class FieldTicket {
    private Set<TicketRow> setTicketRow = new HashSet<TicketRow>();

    public Set<TicketRow> getSetTicketRow() {
        return setTicketRow;
    }

    public void setSetTicketRow(Set<TicketRow> setTicketRow) {
        this.setTicketRow = setTicketRow;
    }
}
