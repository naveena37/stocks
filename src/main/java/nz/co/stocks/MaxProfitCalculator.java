package nz.co.stocks;

import java.math.BigDecimal;
import java.util.List;

/**
 * Maximum stock profit calculator.
 */
public class MaxProfitCalculator
{
  /**
   * A simple method that determines the maximum profit from a list of stock values
   * on the condition that stocks have to be bought first before selling.
   *
   * @param priceList list of stock prices
   * @return The max profit in BigDecimal
   *
   * Working with BigDecimals as this is about money and correct precision becomes vital.
   */
  public BigDecimal calculateMaxProfit(List<BigDecimal> priceList)
  {
    if (priceList == null || priceList.isEmpty())
      return BigDecimal.ZERO;

    // Consider the first element as minimum value
    BigDecimal minimum = priceList.get(0);
    BigDecimal maxProfit = BigDecimal.ZERO;

    for (BigDecimal price : priceList)
    {
      minimum = price.compareTo(minimum) < 0 ? price : minimum;
      BigDecimal profit = price.subtract(minimum);
      maxProfit = profit.compareTo(maxProfit) > 0 ? profit : maxProfit;
    }
    return maxProfit;
  }
}