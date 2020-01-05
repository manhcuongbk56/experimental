package cuong.experimental;


import java.util.Objects;

public class Money implements Expression {

    public static final String DOLLAR_CURRENCY = "USD";
    public static final String FRANC_CURRENCY = "CHF";
    protected int amount;
    protected String currency;


    Money(int amount) {
        this.amount = amount;
        currency = null;
    }

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    static Money dollar(int amount) {
        return new Money(amount, DOLLAR_CURRENCY);
    }

    static Money franc(int amount) {
        return new Money(amount, FRANC_CURRENCY);
    }

    Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    Expression plus(Money addend){
        return new Sum(this, addend);
    }

    String currency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount &&
                currency.equals(money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
