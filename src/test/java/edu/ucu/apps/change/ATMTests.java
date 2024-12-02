package edu.ucu.apps.change;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMTests {

    @Test
    void testAmount() {
        ATM atm = new ATM();

        int totalCoins = atm.receiveMoney(1280);

        assertEquals(11, totalCoins);
    }

    @Test
    void testSingle() {
        CoinMachine coinMachine = new Coin10(null);

        int totalCoins = coinMachine.receiveCoins(50);

        assertEquals(5, totalCoins);
    }

    @Test
    void testNoCoins() {
        CoinMachine coinMachine = new Coin10(null);

        int totalCoins = coinMachine.receiveCoins(0);

        assertEquals(0, totalCoins);
    }

    @Test
    void testWrongAmount() {
        ATM atm = new ATM();

        try {
            atm.receiveMoney(1285);
        } catch (IllegalArgumentException e) {
            assertEquals("Cannot give this amount with existing denominations", e.getMessage());
        }

    }

    @Test
    void testDenominations() {
        CoinMachine coinMachine = new Coin500(new Coin200(new Coin100(new Coin10(null))));

        int totalCoins = coinMachine.receiveCoins(760);

        assertEquals(4, totalCoins);
    }
}
