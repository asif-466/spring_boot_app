package project;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class FullBank {

    static HashMap<Integer, String> map = new HashMap<>();
    static File f = new File("/home/asif/IdeaProjects/untitled/bank.csv");
    static Scanner sc = new Scanner(System.in);

    public static void menu() {
        System.out.println("1. CREATE ACCOUNT");
        System.out.println("2. CHECK BALANCE");
        System.out.println("3. DEPOSIT");
        System.out.println("4. WITHDRAW");
        System.out.println("5. EXIT");
    }

    public static void hashtoload() {
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] val = line.split(",");
                int pin = Integer.parseInt(val[0]);
                map.put(pin, line);
            }
        } catch (Exception e) {
            System.out.println("NOT LOAD INTO HASHMAP");
        }
    }

    public static void loadtofile() {
        try (FileWriter fw = new FileWriter(f)) {
            for (int key : map.keySet()) {
                String val = map.get(key);
                fw.write(val + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void create() {
        System.out.println("ENTER YOUR NAME:");
        String name = sc.nextLine();
        System.out.println("ENTER YOUR ADDRESS:");
        String address = sc.nextLine();
        System.out.println("ENTER FIRST DEPOSIT AMOUNT:");
        int amount = sc.nextInt();
        sc.nextLine();
        int pin = 1000 + (int) (Math.random() * 9000);
        String line = pin + "," + amount + "," + name + "," + address;
        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write(line + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        map.put(pin, line);
        System.out.println("ACCOUNT CREATED SUCCESSFULLY!");
        System.out.println("YOUR PIN IS: " + pin);
    }
    public static void bal() {
        System.out.print("ENTER YOUR PIN: ");
        int pin = sc.nextInt();
        sc.nextLine();
        if (map.containsKey(pin)) {
            String[] data = map.get(pin).split(",");
            System.out.println("BALANCE: " + data[1]);
        } else {
            System.out.println("INVALID PIN!");
        }
    }
    public static void dep() {
        System.out.print("ENTER YOUR PIN: ");
        int pin = sc.nextInt();
        if (map.containsKey(pin)) {
            System.out.print("ENTER AMOUNT TO DEPOSIT: ");
            int amount = sc.nextInt();
            sc.nextLine();
            String[] data = map.get(pin).split(",");
            int bal = Integer.parseInt(data[1]);
            bal += amount;
            data[1] = String.valueOf(bal);
            String updated = String.join(",", data);
            map.put(pin, updated);
            loadtofile();
            System.out.println("DEPOSIT SUCCESSFUL. NEW BALANCE: " + bal);
        } else {
            System.out.println("INVALID PIN!");
        }
    }

    public static void with() {
        System.out.print("ENTER YOUR PIN: ");
        int pin = sc.nextInt();
        if (map.containsKey(pin)) {
            System.out.print("ENTER AMOUNT TO WITHDRAW: ");
            int amount = sc.nextInt();
            sc.nextLine();
            String[] data = map.get(pin).split(",");
            int bal = Integer.parseInt(data[1]);
            bal -= amount;
            data[1] = String.valueOf(bal);
            String updated = String.join(",", data);
            map.put(pin, updated);
            loadtofile();
            System.out.println("WITHDRAW SUCCESSFUL. NEW BALANCE: " + bal);
        } else {
            System.out.println("INVALID PIN!");
        }
    }
    public static void main(String[] args) {
        hashtoload();
        while (true) {
            menu();
            System.out.print("CHOOSE AN OPTION: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    create();
                    break;
                case 2:
                    bal();
                    break;
                case 3:
                    dep();
                    break;
                case 4:
                    with();
                    break;
                case 5:
                    System.out.println("EXITING...");
                    loadtofile();
                    return;
                default:
                    System.out.println("INVALID OPTION TRY AGAIN:");
            }
        }
    }
}
