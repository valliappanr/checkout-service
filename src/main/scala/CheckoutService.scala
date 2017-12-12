case class Product(val name: String, val cost: Double)
object CheckoutService {

  def apply(products: List[Product]): CheckoutService = {
    new CheckoutService(products.map(product => (product.name, product)).toMap.withDefaultValue(
      Product("unsupported", 0.0)))
  }
}
class CheckoutService(val supportedProducts: Map[String, Product]) {
  def calculateCost(productsInBasket: List[String]): Double = {
    productsInBasket.map(product => supportedProducts(product).cost).sum
  }
}