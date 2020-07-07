package nz.co.stocks;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for {@link MaxProfitCalculator}.
 *
 */
public class MaxProfitCalculatorTest
{
  MaxProfitCalculator test = new MaxProfitCalculator();

  @Test
  public void testCalculateMaxProfitWithIncreasingGraph()
  {
    List<BigDecimal> priceList = new ArrayList<BigDecimal>();
    for (int price = 1; price <= 11; ++price)
    {
      priceList.add(new BigDecimal(price));
    }
    BigDecimal actual = test.calculateMaxProfit(priceList);
    assertEquals("Best profit is not calculated correctly", BigDecimal.TEN, actual);
  }

  @Test
  public void testCalculateMaxProfitWithLowsAndHighs()
  {
    BigDecimal actual = test.calculateMaxProfit(Arrays.asList(
        BigDecimal.TEN,
        new BigDecimal("7.11"),
        new BigDecimal("7.99"),
        new BigDecimal("5.25"),
        new BigDecimal("8.40"),
        new BigDecimal("8.56"),
        new BigDecimal("3.37"),
        new BigDecimal("4.45"),
        new BigDecimal("5.89"),
        BigDecimal.ONE));
    assertEquals("Best profit is not calculated correctly", new BigDecimal("3.31"), actual);
  }

  @Test
  public void testCalculateMaxProfitWhenNoProfitPossibility()
  {
    BigDecimal actual = test.calculateMaxProfit(Arrays.asList(
        new BigDecimal("10.98"),
        new BigDecimal("8.76"),
        new BigDecimal("6.54"),
        new BigDecimal("4.32"),
        new BigDecimal("2.10")));
    assertEquals("Best profit is not calculated correctly", BigDecimal.ZERO, actual);
  }

  @Test
  public void testCalculateMaxProfitInATradingDay()
  {
    List<BigDecimal> priceList = new ArrayList<BigDecimal>();
    // Assuming 6 hours in a trading day - so 360 stock prices for 360 minutes
    for (double price = 0.001; price <= 0.361; price += 0.001)
    {
      priceList.add(new BigDecimal(price).setScale(3, BigDecimal.ROUND_UP));
    }

    BigDecimal actual = test.calculateMaxProfit(priceList);
    assertEquals("Best profit is not calculated correctly", new BigDecimal("0.359"), actual);
  }

  @Test
  public void testCalculateMaxProfitOverALongPeriod()
  {
    List<BigDecimal> priceList = new ArrayList<BigDecimal>();
    // 10000 minutes ~ a trading month
    for (double price = 0.001; price <= 10; price += 0.001)
    {
      priceList.add(new BigDecimal(price).setScale(3, BigDecimal.ROUND_UP));
    }

    long start = System.currentTimeMillis();
    BigDecimal actual = test.calculateMaxProfit(priceList);
    assertEquals("Best profit is not calculated correctly", new BigDecimal("9.998"), actual);
    long end = System.currentTimeMillis();

    System.out.println("Took " + (end-start) + " ms to calculate best profit out of " + priceList.size() + " items");
  }

  @Test
  public void testCalculateMaxProfitWithAnEmptyArray()
  {
    BigDecimal actual = test.calculateMaxProfit(Collections.EMPTY_LIST);
    assertEquals("Best profit is not calculated correctly", BigDecimal.ZERO, actual);
  }
}
