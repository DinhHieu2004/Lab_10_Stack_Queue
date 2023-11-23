package Task2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class My_FIFO_App {

	// method stutter that accepts a queue of integers as
	// a parameter and replaces
	// every element of the queue with two copies of that
	// element
	// front [1, 2, 3] back
	// becomes
	// front [1, 1, 2, 2, 3, 3] back
	public static <E> void stutter(Queue<E> input) {
		int size = input.size();
		
		for(int i =0 ; i<size; i++) {
			E element = input.poll();
			input.add(element);
			input.add(element);
		}
		System.out.println(input);
	}
	// Method mirror that accepts a queue of strings as a
	// parameter and appends the
	// queue's contents to itself in reverse order
	// front [a, b, c]

	// becomes
	// front [a, b, c, c, b, a] back
	public static <E> void mirror(Queue<E> input) {
		Stack<E> stack = new Stack<>();
		Queue<E> output = new LinkedList<>();
		while (!input.isEmpty()) {
            E element = input.poll();
            output.offer(element);
            stack.push(element);
        }

        // Enqueue elements from the stack to the end of the queue
        while (!stack.isEmpty()) {
            E element = stack.pop();
            output.offer(element);
        }
        System.out.println(output);
	}
	public static void main(String[] args) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		q.offer(2);
		q.offer(3);
		mirror(q);
	}
}
