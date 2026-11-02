package chap10.interfaces;

import onjava.Operation;

class Heat implements Operation {

    @Override
    public void execute() {
        System.out.println("Heat");
    }
}

public class MetalWork {

    public static void main(String[] args) {

        Operation twist = new Operation() {
            @Override
            public void execute() {
                Operation.show("Twist");
            }
        };

        Operation.runOps(
            new Heat(), // external class
            new Operation() {
                @Override
                public void execute() {
                    Operation.show("Hammer");
                }
            },  // anonymous class
            twist::execute, // method reference; must be in a static context to access a method reference
            () -> Operation.show("Anvil")  // Lambda expression
        );
    }
}
