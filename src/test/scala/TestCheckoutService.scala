import org.scalatest.{FeatureSpec, Matchers}

trait CheckoutServiceTestInitializer {
  val apple = Product("Apple", 0.6)
  val orange = Product("Orange", 0.25)
  val banana = Product("Banana", 0.1)
  def checkoutService: CheckoutService
}
object CheckoutServiceTestInitializerWithoutDiscount extends CheckoutServiceTestInitializer {
  override def checkoutService: CheckoutService =  CheckoutService(List(apple, orange))
}

object CheckoutServiceTestInitializerWithDiscount extends CheckoutServiceTestInitializer {
  val discountedProducts = Map(apple -> BuyOneGetOne, orange -> BuyThreeGetOne)

  override def checkoutService: CheckoutService =  CheckoutService(List(apple, orange),
    new CostCalculationWithDiscountService(discountedProducts))
}

class TestCheckoutService extends FeatureSpec with Matchers {


  feature("checkout service") {
    import CheckoutServiceTestInitializerWithoutDiscount._
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

  feature("checkout service with discounts") {
    import CheckoutServiceTestInitializerWithDiscount._
    scenario("should apply the discounts correctly for buy one get one") {
      val products = List(apple.name, apple.name, orange.name, apple.name)
      val expectedCost = 1.45
      val actualCost = checkoutService.calculateCost(products)
      actualCost shouldEqual expectedCost
    }
    scenario("should apply the discounts correctly buy three get one") {
      val products = List(orange.name, orange.name, orange.name)
      val expectedCost = 0.25
      val actualCost = checkoutService.calculateCost(products)
      actualCost shouldEqual expectedCost
    }
  }
}