/**
 * Created by Federica on 20/04/2018.
 */
public class CircularLinkedList<T> {

    private Node head;
    private Node tail;
    private int listSize;


    public CircularLinkedList() {
        head = new Node(null);
        listSize = 0;
    }

    public void addFirst(T data) {

        Node node = new Node(data);

        if (listSize == 0) {
            head = node; //one-element list: head and tail are the same node
            tail = node;
            tail.next = head;
            head.next = tail;
        } else  {
            Node temp = head;
            node.next = temp; //the new node points to the head as its next node
            head = node; //the new node becomes the head
            tail.next = head; //also the tail's reference is updated
        }
        listSize++;
    }

    public void addLast(T data) {

        if (listSize == 0) {
            addFirst(data);
        } else {
            Node node = new Node(data);
            tail.next = node; //the tail points to the new node as next
            tail = node; //the new node becomes the tail
            tail.next = head; //its reference points to the head
            listSize++;
        }
    }

    public void clear() {
        Node node = head;
        while (node != null) {
            node.next = null;
            node = node.next;
        }
    }

    public T get(int index) {

        T data;
        Node currentNode = head;
        if (index == 0) {
            data = head.data;
        } else if (index == listSize - 1) {
            data = tail.data;
        } else {

            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            data = currentNode.data;
        }

        return data;
    }

    public int size() {
        return listSize;
    }

    public void removeFirst() {

        if (listSize != 0) {
            head = head.next;
            tail.next = head;
            listSize--;
        }
    }

    public void removeLast() {

        if (listSize != 0) {
            Node currentNode = head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
                if (currentNode.getNext() == tail)
                    break;
            }
            tail = currentNode;
            tail.next = head;
            listSize--;
        }
    }

    public void removeAt(int index) {

        if (index == 0 || index == listSize) {
            removeFirst();
        } else if (index == listSize - 1){
            removeLast();
        } else
        {
            if (listSize != 0) {
                Node currentNode = head;
                for (int i = 0; i < index - 1; i++) { //it loops through the nodes until it finds the node before the node to be removed
                    if (currentNode.getNext() != null) {
                        currentNode = currentNode.getNext();
                    }
                }
                Node tempNode = currentNode.getNext(); //temporary node gets the value of the node to be removed
                currentNode.setNext(tempNode.getNext()); //the node before the one to be removed changes reference
                listSize--;
            }
        }
    }

    public void print() {
        Node currentNode = head;
        for (int i = 0; i < listSize; i++) {
            System.out.println(currentNode.data);
            currentNode = currentNode.getNext();
        }
    }
    private class Node {
        Node next;
        T data;

        public Node(T myData) {
            next = null;
            data = myData;
        }


        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}

