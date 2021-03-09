package adhocs.threads;


import java.util.Stack;

class SafeStack {
    private int[] stack;
    private int top;
    public SafeStack(int capacity) {
        stack = new int[capacity];
        top = -1;
    }

    public synchronized int push(int val) {
        if (top==stack.length-1) {
            return 0;
        }
        stack[++top] = val;
        return 1;
    }
    public synchronized int pop() {
        if (top==-1) {
            return -1;
        }
        return stack[top--];
    }
    public synchronized int peek() {
        if (top==-1) {
            return -1;
        }
        return stack[top];
    }
}

class PushWorker extends Thread{
    private int val;
    private Stack stack;

    public PushWorker(int val,Stack stack) {
        this.val = val;
        this.stack = stack;
    }

    @Override
    public void run() {
        int result;
        synchronized (stack) {
            result = (Integer) stack.push(val);
        }
        if (result==0)
            System.out.println("Stack Overflow");
        else
            System.out.println("Inserted "+val);
    }
}

class PopWorker extends Thread{
    private Stack stack;
    public PopWorker(Stack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        int result;
        synchronized (stack) {
            result = (Integer) stack.pop();
        }
        if (result==-1)
            System.out.println("Stack Underflow");
        else
            System.out.println("Popped "+result);
    }
}

public class Fifth {
public static void main(String[] args) {
        Stack<Integer> stack = new Stack();
//        PopWorker thread = new PopWorker(stack);
        PushWorker th1 = new PushWorker(1, stack);
        th1.start();
        PushWorker th2 = new PushWorker(2, stack);
        th2.start();
        PushWorker th3 = new PushWorker(3, stack);
        th3.start();
//        thread.start();
        PushWorker th4 = new PushWorker(4, stack);
        th4.start();
        PushWorker th5 = new PushWorker(5, stack);
        th5.start();

        try {
            Thread.sleep(1000);
            // seeing the order in which items were inserted
            while (stack.size()!=0){
                System.out.println("item : "+stack.pop());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}