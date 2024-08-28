package org.lab;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Repository<T> {

    private List<T> items = new ArrayList<>();

    //element hinzufügen
    public void add(T item) {
        items.add(item);
    }

    //element löschen
    public void remove(T item) {
        items.remove(item);
    }
    //alle Elementen holen
    public List<T> findAll() {
        return  new ArrayList<>(items);
    }

    //filter elemente nach bestimmten Bedingungen
    public List<T> findByCriterium(Criterium<T> criterium) {
        return items.stream().filter(criterium::test).collect(Collectors.toList());
    }

    //sortiere nach bestimmten Bedingungen
    public <U extends Comparable<U>> List<T> sorted(Comparator<T> comparator) {
        return items.stream().sorted(comparator).collect(Collectors.toList() );
    }

    public Optional<T> findFirstByCriterium(Criterium<T> criterium){
        return items.stream().filter(criterium::test).findFirst();
    }



}
