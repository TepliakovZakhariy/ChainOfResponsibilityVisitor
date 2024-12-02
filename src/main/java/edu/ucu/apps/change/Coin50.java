package edu.ucu.apps.change;

public class Coin50 extends CoinMachine {

    public static final int DENOMINATION = 50;

    public Coin50(CoinMachine nextCoinMachine) {
        super(DENOMINATION, nextCoinMachine);
    }

}
