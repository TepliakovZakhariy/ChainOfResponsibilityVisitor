package edu.ucu.apps.change;

public class ATM {

    private final CoinMachine coinMachine;

    public ATM() {
        this.coinMachine = new Coin500(new Coin200(new Coin100(new Coin10(null))));
    }

    public int receiveMoney(int money) {
        return this.coinMachine.receiveCoins(money);
    }

}
