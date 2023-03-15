package com.example.profiling.demo;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\nSTART APPLICATION");
        for (int i = 0; i < 200_000_000; i++) {
            String[] split = "1,1:2,2".split(":");
            split[0] = Demo.eleven();
//            split[0] = Demo.substringWithDelay(split[0]);
            split[1] = "22";
        }
        System.out.println("FINISH APPLICATION");
    }

    private static String eleven() {
        return "11";
    }

    private static String substringWithDelay(String str) throws InterruptedException {
        Thread.sleep(1);
        return str.substring(2);
    }
}
