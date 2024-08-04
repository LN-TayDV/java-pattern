package com.iluwatar.algorithm.theories.on.book.robert.kevin.chap1_3;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Bag<T> implements Iterable<T> {

    T value;

    public Bag(T value) {
        this.value = value;
    }

    public Bag() {}

    public void add (T value) {

    }

    public boolean isEmpty () {
        return false;
    }

    public int size() {
        return 0;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }
}
