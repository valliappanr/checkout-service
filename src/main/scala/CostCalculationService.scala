import CostCalculation._

sealed trait CostCalculationService {
  def calculateCost(productsInBasked: List[Product]): Double
}

class DefaultCostCalculationService extends CostCalculationService {
  override def calculateCost(productsInBasked: List[Product]): Double = {
    aggregateCost(groupByProducts(productsInBasked))
  }
}

class CostCalculationWithDiscountService(val productDiscountMap: Map[Product, DiscountForAProduct])
  extends CostCalculationService {
  def applyDiscountForAProduct(productQtyMap: Map[Product, Int], productToDiscountMap: Map[Product, DiscountForAProduct]
                              ): Map[Product, Int] = {
    productQtyMap map {
      case (key, value) => (key, productToDiscountMap(key).applyDiscount(value))
    }
  }

  override def calculateCost(productsInBasked: List[Product]): Double = {
    aggregateCost(applyDiscountForAProduct(groupByProducts(productsInBasked),
      productDiscountMap.withDefaultValue(NoDiscount)))
  }
}