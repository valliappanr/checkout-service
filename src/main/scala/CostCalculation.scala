object CostCalculation {

  def groupByProducts(productsInBasket: List[Product]): Map[Product, Int] = {
    productsInBasket.groupBy(product => product) map {
      case (key, value) => (key, value.size)
    }
  }

  def aggregateCost(productQtyMap: Map[Product, Int]): Double = {
    productQtyMap.map {
      case (key, value) => value * key.cost
    }.sum
  }
}