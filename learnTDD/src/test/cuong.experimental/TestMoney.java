package cuong.experimental;

import org.junit.Test;

import static cuong.experimental.Money.dollar;
import static cuong.experimental.Money.franc;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestMoney {

    @Test
    public void multiplication_shouldSuccess() {
        Money dollar = dollar(5);
        assertEquals(dollar(10), dollar.times(2));
        assertEquals(dollar(15), dollar.times(3));
    }

    @Test
    public void testEquality() {
        assertTrue(dollar(5).equals(dollar(5)));
        assertFalse(dollar(5).equals(dollar(6)));
        assertTrue(franc(5).equals(franc(5)));
        assertFalse(franc(5).equals(franc(6)));
        assertFalse(franc(5).equals(dollar(5)));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", franc(1).currency());
    }

    @Test
    public void testSimpleAction() {
        Expression sum = dollar(5).plus(dollar(5));
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(dollar(10), reduced);
    }

    @Test
    public void testPlusShouldReturnSum() {
        Money five = dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augend);
        assertEquals(five, sum.addend);
    }

    @Test
    public void testReduceSum() {
        Expression sum = new Sum(dollar(3), dollar(4));
        Bank bank = new Bank();
        Money result =
    }
}
