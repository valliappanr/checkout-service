case class Product(val name: String, val cost: Double)
object CheckoutService {

  val unsupportedProduct = Product("unsupported", 0.0)
  def apply(products: List[Product]): CheckoutService = {
    new CheckoutService(products.map(product => (product.name, product)).toMap.withDefaultValue(
      unsupportedProduct), new DefaultCostCalculationService)
  }
  def apply(products: List[Product], costCalculationService: CostCalculationService): CheckoutService = {
    new CheckoutService(products.map(product => (product.name, product)).toMap.withDefaultValue(
      unsupportedProduct), costCalculationService)
  }
}
class CheckoutService(val supportedProducts: Map[String, Product], costCalculationService: CostCalculationService) {
  def calculateCost(productsInBasket: List[String]): Double = {
    costCalculationService.calculateCost(productsInBasket.map(productName => supportedProducts(productName)))
  }
}