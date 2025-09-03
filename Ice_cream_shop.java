package project;

import java.util.Scanner;

public class Ice_cream_shop {
    public static void main(String[] args) {
        int ch_qnty = 0;
        int van_qnty = 0;
        int but_qnty = 0;
        int pine_qnty = 0;
        int ch_total = 0;
        int van_total = 0;
        int but_total = 0;
        int pine_total = 0;

        boolean exit = true;
        while (exit) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1. CHOCOLATE");
            System.out.println("2. VANILLA");
            System.out.println("3. BUTTERSCOTCH");
            System.out.println("4. PINEAPPLE");
            System.out.println("5. EXIT AND BILL");
            System.out.println("CHOOSE YOUR FAVORITE ICE CREAM:");
            int num = sc.nextInt();

            switch (num) {
                case 1:
                    System.out.println("CHOCOLATE - 20 RUPEES");
                    System.out.println("ENTER QUANTITY OF CHOCOLATE:");
                    ch_qnty += sc.nextInt();
                    ch_total = ch_qnty * 20;
                    break;
                case 2:
                    System.out.println("VANILLA - 10 RUPEES");
                    System.out.println("ENTER QUANTITY OF VANILLA:");
                    van_qnty += sc.nextInt();
                    van_total = van_qnty * 10;
                    break;
                case 3:
                    System.out.println("BUTTERSCOTCH - 30 RUPEES");
                    System.out.println("ENTER QUANTITY OF BUTTERSCOTCH:");
                    but_qnty += sc.nextInt();
                    but_total = but_qnty * 30;
                    break;
                case 4:
                    System.out.println("PINEAPPLE - 40 RUPEES");
                    System.out.println("ENTER QUANTITY OF PINEAPPLE:");
                    pine_qnty += sc.nextInt();
                    pine_total = pine_qnty * 40;
                    break;
                case 5:
                    int grand_total = ch_total + van_total + but_total + pine_total;
                    System.out.println("\n--- FINAL BILL ---");
                    System.out.println("ICE CREAM      PRICE   QNTY   TOTAL");

                    if (ch_qnty > 0) {
                        System.out.println("CHOCOLATE      20      " + ch_qnty + "     " + ch_total);
                    }
                    if (van_qnty > 0) {
                        System.out.println("VANILLA        10      " + van_qnty + "     " + van_total);
                    }
                    if (but_qnty > 0) {
                        System.out.println("BUTTERSCOTCH   30      " + but_qnty + "     " + but_total);
                    }
                    if (pine_qnty > 0) {

                        System.out.println("PINEAPPLE      40      " + pine_qnty + "     " + pine_total);
                    }

                    System.out.println("-----------------------------");
                    System.out.println("GRAND TOTAL: " + grand_total + " RUPEES");
                    System.out.println("THANK YOU FOR SHOPPING!");
                    exit = false;
                    break;

                default:
                    System.out.println("INVALID CHOICE. PLEASE TRY AGAIN.");
            }
        }
    }
}
