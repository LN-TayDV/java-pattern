/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.algorithm.theories.on.self.learning.Fundamentals;

import com.iluwatar.algorithm.theories.on.book.robert.kevin.system.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Bag<Item> implements Iterable<Item> {

    private Node<Item> first;    // beginning of bag
    private int n;               // number of elements in bag

    public Bag () {
        first = null;
        n = 0;
    }

    public boolean isEmpty () {
        return first == null;
    }

    public int size () {
        return n ;
    }

    public void add (Item item) {
        Node<Item> old = first;
        first = new Node<Item>();
        first.item = item;
        first.next = old;
        n++;
    }


    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {

        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public void forEach(Consumer<? super Item> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Item> spliterator() {
        return Iterable.super.spliterator();
    }

    public static void main(String[] args) {
        Bag<String> strings = new Bag<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");

        StdOut.println("Size : " + strings.size());

        strings.forEach(e -> {
            StdOut.println("Item : " + e);
        });
    }
}
