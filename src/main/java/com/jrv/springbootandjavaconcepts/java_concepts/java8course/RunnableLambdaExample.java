package com.jrv.springbootandjavaconcepts.java_concepts.java8course;

public class RunnableLambdaExample {
    public static void main(String[] args) {
        /*Prior Java 8*/
        Runnable runnablePriorJava8 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable Prior Java 8");
            }
        };
        new Thread(runnablePriorJava8).start();

        /*Runnable using Lambda*/
        Runnable runnableUsingLambda = () -> {
            System.out.println("Runnable using Lambda");
        };
        new Thread(runnableUsingLambda).start();

        /*Runnable without creating any objects*/
        new Thread(() -> System.out.println("Runnable without creating any objects")).start();
    }
}
