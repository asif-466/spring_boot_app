package project;

import java.util.Scanner;

public class AtmPinVerification {
    public static void main(String[] args) {
        int pin = 1234;
        int wrong = 0;

        while (true) {
            System.out.println("ENTER PIN:");
            Scanner sc = new Scanner(System.in);
            int userpin = sc.nextInt();

            if (pin == userpin) {
                System.out.println("ACCESS GRANTED:");
                break;

            } else {
                System.out.println("ACCESS DENIED:");
                wrong++;

                if (wrong > 3) {
                    System.out.println("CARD BLOCK:");
                    return;
                }
            }
        }
    }
}
