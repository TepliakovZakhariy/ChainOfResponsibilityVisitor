package edu.ucu.apps;

import edu.ucu.apps.change.ATM;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.receiveMoney(1000);
        System.out.println();
    }
}