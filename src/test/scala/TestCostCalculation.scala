import org.scalatest.{FeatureSpec, Matchers}

class TestCostCalculation extends FeatureSpec with Matchers {

  feature("CostCalculation") {
    scenario("group by products should group the products correctly based on the product in a list") {
      val apple = Product("Apple", 0.6)
      val products = List(apple, apple)
      CostCalculation.groupByProducts(products) shouldEqual Map(apple -> 2)
    }
    scenario("aggregate cost for products with price should calculate the aggregation correctly") {
      val apple = Product("Apple", 0.6)
      val orange = Product("Orange", 0.25)
      val aggregatedProductMap = Map(apple -> 2, orange -> 3)
      CostCalculation.aggregateCost(aggregatedProductMap) shouldEqual 1.95
    }
  }

}