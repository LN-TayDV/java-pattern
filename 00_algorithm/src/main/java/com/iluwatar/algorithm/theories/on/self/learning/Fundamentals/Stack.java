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

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Stack <Item> implements Iterable<Item>{

    private Node<Item> first;
    private int n;

    public Stack () {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size () {
        return n;
    }

    public void push (Item item) {
        Node <Item> old = first;
        first = new Node<>();
        first.item = item;
        first.next = old;
        n++;
    }

    public Item pop () {
        if(isEmpty())  throw new NoSuchElementException("Stack underflow");

        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item>  {

        private Node<Item> current ;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return Objects.nonNull(current);
        }

        @Override
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
