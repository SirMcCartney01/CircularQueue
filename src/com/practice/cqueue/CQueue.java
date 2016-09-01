package com.practice.cqueue;

public class CQueue {

    private int front, rear;
    private int[] Queue;
    private int size;

    public CQueue(int size) {
        this.front = 0;
        this.rear = 0;
        this.size = size;
        this.Queue = new int[size + 1];
    }

    public void enqueue(int value) {
        rear = (rear + 1) % Queue.length;

        if (rear == front) {
            System.err.println("Cola llena.");

            if (rear == 0)
                rear = Queue.length - 1;
            else
                rear = rear - 1;
        }

        Queue[rear] = value;
    }

    public int dequeue() {
        if (rear == front) {
            System.err.println("Cola vacia.");
            return -1;
        }

        int tmp = Queue[front];
        front = (front + 1) % Queue.length;

        return tmp;
    }
}
