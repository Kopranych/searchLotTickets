package model;

import java.util.HashSet;
import java.util.Set;

public class CoincidenceStatistic {
    private int numberCoincidence;//количество совпадений между двумя билетами
    private Set<Integer> setIdenticalNumber = new HashSet<Integer>();//набор одинаковых чисел

    public int getNumberCoincidence() {
        return numberCoincidence = setIdenticalNumber.size();
    }

    public Set<Integer> getSetIdenticalNumber() {
        return setIdenticalNumber;
    }

    @Override
    public String toString() {
        return "CoincidenceStatistic{" +
                "numberCoincidence=" + numberCoincidence +
                ", setIdenticalNumber=" + setIdenticalNumber +
                '}';
    }
}
