package tools;

import java.util.ArrayList;
import java.util.List;


public class Lists<X>{
	
	private int stackPointer;
	
	private List<X> stackList;
	
	public Lists() {
		
		this.stackPointer = 0;
		this.stackList = new ArrayList<X>();
	}

	public void push(X item) {
		
		if (stackPointer > 999) {
			throw new IllegalStateException("Stack Overflow");
		} else {
			stackList.add(item);
			stackPointer++;
		}
	}

	public X pop() {
		X item;
		if (stackPointer == 0) {
			throw new IllegalStateException("Stack Underflow");
		} else {
			item = stackList.get(--stackPointer);
		}		
		return item;
	}

	public boolean contains(X item) {
		for (X x : stackList) {
			if (x.equals(item)) {
				return true;
			}
		}
		return false;
	}

	public X access(X item) {
		while (stackPointer >= 0) {
			X poppedElement = pop();
			if (item.equals(poppedElement)) {
				return poppedElement;
			}
		}
		
		throw new IllegalStateException("Item" + item + "not found!");
	}

	public void printStack() {
		if (stackPointer == 0) {
			System.out.println("Empty Stack");
		} else {
			for (X x: stackList) {
				System.out.println(x);
			}
		}
	}

	public int size() {
		return stackPointer;
	}

}
