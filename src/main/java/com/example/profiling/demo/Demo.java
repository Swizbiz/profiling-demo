package com.example.profiling.demo;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\nSTART APPLICATION");
        for (int i = 0; i < 200_000_000; i++) {
            String[] split = "1,1:2,2".split(":");
//            split[0] = "11";
            split[0] = split[0].substring(2);
            split[1] = "22";
        }
        System.out.println("FINISH APPLICATION");
    }

}
