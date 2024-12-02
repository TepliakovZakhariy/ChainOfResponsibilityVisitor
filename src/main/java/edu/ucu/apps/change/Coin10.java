package edu.ucu.apps.change;

public class Coin10 extends CoinMachine {

    public static final int DENOMINATION = 10;

    public Coin10(CoinMachine nextCoinMachine) {
        super(DENOMINATION, nextCoinMachine);
    }

}
