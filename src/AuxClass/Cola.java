/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AuxClass;

/**
 *
 * @author Angelo
 */
public class Cola <T> {

    private Nodo<T> head;
    private Nodo<T> tail;
    private int size;

    public Cola() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void empty() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (isEmpty()) {
            this.head = nuevo;
            this.tail = nuevo;
        } else {
            this.tail.setpNext(nuevo);
            this.tail = nuevo;
        }
        this.size++;
    }

    public void desencolar() {
        if (!isEmpty()) {
            if (this.size == 1) {
                empty();
            } else {
                this.head = this.head.getpNext();
                this.size--;
            }
        }
    }

    public String travel() {
        String toPrint = "";
        if (!isEmpty()) {
            Nodo<T> actual = this.head;
            while (actual != null) {
                toPrint += actual.gettInfo() + "-->";
                actual = actual.getpNext();
            }
        }
        return toPrint;
    }

    public Nodo<T> getHead() {
        return head;
    }

    public void setHead(Nodo<T> head) {
        this.head = head;
    }

    public Nodo<T> getTail() {
        return tail;
    }

    public void setTail(Nodo<T> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
