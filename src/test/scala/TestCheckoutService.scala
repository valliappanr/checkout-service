import org.scalatest.{FeatureSpec, Matchers}

class TestCheckoutService extends FeatureSpec with Matchers {

  val apple = Product("Apple", 0.6)
  val orange = Product("Orange", 0.25)
  val banana = Product("Banana", 0.1)
  val checkoutService = CheckoutService(List(apple, orange))

  feature("checkout service") {
    scenario("should calculate the cost for the products in basket correctly") {
      //Given
      val products = List(apple.name, apple.name, orange.name)
      val estimatedCost = 1.45
      //When
      val actualCost = checkoutService.calculateCost(products)
      //Then
      actualCost shouldEqual estimatedCost
    }
    scenario("should handle unsupported products and ignore them") {
      val estimatedCost = 0.0
      val actualCost = checkoutService.calculateCost(List(banana.name))
      actualCost shouldEqual estimatedCost
    }
  }
}