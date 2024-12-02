package edu.ucu.apps.change;

public class Coin500 extends CoinMachine {

    public static final int DENOMINATION = 500;

    public Coin500(CoinMachine nextCoinMachine) {
        super(DENOMINATION, nextCoinMachine);
    }

}
