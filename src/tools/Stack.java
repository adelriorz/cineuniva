package tools;

/*
**Written by: Armando Del Río Ramírez & Paola Escalera
**Date: 01/05/ 2021 - 04/10/2021
**Description: Code for input verifier
*/
public class Stack<X>{
	
	private int stackPointer;
	
	private X[] stackArray;
	
	@SuppressWarnings("unchecked")
	public Stack() {
		
		this.stackPointer = 0;
		this.stackArray = (X[]) new Object[1000];
	}
	
	@SuppressWarnings("unchecked")
	public Stack(int size) {
		this.stackPointer = 0;
		// For creating an stack of a different size instead of hard-coding it. 
		this.stackArray = (X[]) new Object[size];
	}

	public void push(X item) {
		
		if (stackPointer > 999) {
			throw new IllegalStateException("Stack Overflow");
		} else {
			stackArray[stackPointer] = item;
			stackPointer++;
		}
		
	}

	public X pop() {
		X item;
		if (stackPointer == 0) {
			throw new IllegalStateException("Stack Underflow");
		} else {
			item = stackArray[stackPointer];
			stackPointer--;
		}		
		return item;
	}

	public boolean contains(X item) {
		for (X x : stackArray) {
			if (x.equals(item)) {
				return true;
			}
		}
		return false;
	}

	public X access(X item) {	
		while (stackPointer > 0) {
			X poppedElement = pop();
			if (item.equals(poppedElement)) {
				return poppedElement;
			}
		}
		
		throw new IllegalStateException("Item" + item + "not found!");
	}
	
	public int size() {
		return stackPointer;
	}
	
	public void printStack() {
		if (stackPointer == 0) {
			System.out.println("Empty Stack");
		} else {
			for (int i=0; i<=stackPointer; i++) {
				System.out.println(stackArray[i]);
			}
		}
	}
}
