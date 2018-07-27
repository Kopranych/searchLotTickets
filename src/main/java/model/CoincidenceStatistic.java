package model;

import java.util.HashSet;
import java.util.Set;

public class CoincidenceStatistic {
    private int numberCoincidence;
    private Set<Integer> setMatchedNumber = new HashSet<Integer>();

    public int getNumberCoincidence() {
        return numberCoincidence = setMatchedNumber.size();
    }

    public Set<Integer> getSetMatchedNumber() {
        return setMatchedNumber;
    }

    @Override
    public String toString() {
        numberCoincidence = setMatchedNumber.size();
        return "CoincidenceStatistic{" +
                "numberCoincidence=" + numberCoincidence +
                ", setMatchedNumber=" + setMatchedNumber +
                '}';
    }
}
