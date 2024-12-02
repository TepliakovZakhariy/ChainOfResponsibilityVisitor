package edu.ucu.apps.change;

public abstract class CoinMachine {

    private final CoinMachine nextCoinMachine;
    private final int denomination;

    public CoinMachine(int denomination, CoinMachine nextCoinMachine) {
        this.denomination = denomination;
        this.nextCoinMachine = nextCoinMachine;
    }

    public int receiveCoins(int amount) {
        int coinsQuantity = amount / denomination;
        int residual = amount % denomination;

        if (coinsQuantity != 0)
            System.out.format("Received %d coins of %d denomination", coinsQuantity, denomination);

        if (this.hasNext())
            coinsQuantity += nextCoinMachine.receiveCoins(residual);
        else if (residual > 0)
            throw new IllegalArgumentException("Cannot give this amount with existing denominations");

        return coinsQuantity;
    }

    public boolean hasNext() {
        return nextCoinMachine != null;
    }

}
