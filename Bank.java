package com.example.demo;

import com.fasterxml.jackson.core.PrettyPrinter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

@RestController
public class Bank {
    static HashMap<Integer, String> map = new HashMap<>();
    static File f = new File("/home/asif/IdeaProjects/untitled/bank.csv");
    static Scanner sc = new Scanner(System.in);

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

    @GetMapping("/create/{name}/{address}/{amount}")
    public String  create(@PathVariable String name,@PathVariable String address,@PathVariable int amount) {
        int pin = 1000 + (int) (Math.random() * 9000);
        String line = pin + "," + amount + "," + name + "," + address;
        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write(line + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        map.put(pin, line);
        return ("ACCOUNT CREATED SUCCESSFULLY! & YOUR PIN IS "+pin);
    }

    @GetMapping("/balance/{pin}")
    public String bal(@PathVariable int pin) {
        if (map.containsKey(pin)) {
            String[] data = map.get(pin).split(",");
            return "BALANCE: " + data[1];
        } else {
            return "INVILID PIN:";
        }
    }
    @GetMapping("/deposite/{pin}/{amount}")
    public String dep(@PathVariable int pin,@PathVariable int amount) {
        if (map.containsKey(pin)) {
            String[] data = map.get(pin).split(",");
            int bal = Integer.parseInt(data[1]);
            bal += amount;
            data[1] = String.valueOf(bal);
            String updated = String.join(",", data);
            map.put(pin, updated);
            loadtofile();
            return "DEPOSIT SUCCESSFUL. NEW BALANCE: " + bal;
        } else {
            return "INVILID PIN:";
        }
    }
    @GetMapping("/withdraw/{pin}/{amount}")
    public String with(@PathVariable int pin,@PathVariable int amount) {
        if (map.containsKey(pin)) {
            String[] data = map.get(pin).split(",");
            int bal = Integer.parseInt(data[1]);
            bal -= amount;
            data[1] = String.valueOf(bal);
            String updated = String.join(",", data);
            map.put(pin, updated);
            loadtofile();
            return "WITHDRAW SUCCESSFUL. NEW BALANCE: " + bal;
        } else {
            return "INVALID PIN!";
        }
    }
}
