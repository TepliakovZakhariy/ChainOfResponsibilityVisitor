package edu.ucu.apps.change;

public class Coin100 extends CoinMachine{

    public static final int DENOMINATION = 500;

    public Coin100(CoinMachine nextCoinMachine) {
        super(DENOMINATION, nextCoinMachine);
    }

}
