trait DiscountForAProduct {
  val discountFormula = (qty: Int, offerSize: Int) => {qty / offerSize + qty % offerSize}
  def applyDiscount(qty: Int): Int
}


case object NoDiscount extends DiscountForAProduct {
  override def applyDiscount(qty: Int) = qty
}

case object BuyOneGetOne extends DiscountForAProduct {
  override def applyDiscount(qty: Int): Int = discountFormula(qty, 2)
}

case object BuyThreeGetOne extends DiscountForAProduct {
  override def applyDiscount(qty: Int): Int = discountFormula(qty,3)
}