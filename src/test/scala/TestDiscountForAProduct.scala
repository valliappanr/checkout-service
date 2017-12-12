import org.scalatest.{FeatureSpec, Matchers}

class TestDiscountForAProduct extends FeatureSpec with Matchers {

  feature("Discount for a product"){
    scenario("no discount should not apply any discount for any quantity") {
      NoDiscount.applyDiscount(1) shouldEqual 1
    }
    scenario("buy one get one should calculate the quantity correctly for a product of single quantity"){
      BuyOneGetOne.applyDiscount(1) shouldEqual 1
    }
    scenario("buy one get one should calculate the quantity correctly for a product of two quantity"){
      BuyOneGetOne.applyDiscount(2) shouldEqual 1
    }
    scenario("buy three get one should calculate the quantity correctly for a product of single quantity"){
      BuyThreeGetOne.applyDiscount(1) shouldEqual 1
    }
    scenario("buy three get one should calculate the quantity correctly for a product of three quantity"){
      BuyThreeGetOne.applyDiscount(3) shouldEqual 1
    }
  }
}
