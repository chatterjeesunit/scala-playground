
val x1 = new Rational(2,3)
val x2 = new Rational (144, 81)
val x3 = new Rational(5)
val x5 = ~x2
val x6 = x1 + x3
val x7 = x1 / x2
val x4 = new Rational(8 , 0)


class Rational(x:Int, y:Int) {

  require(y>0, "Denominator should be greater than 0")

  def this(x:Int) = this(x, 1)


  private def gcd(x:Int, y:Int):Int = if(x%y == 0) y else gcd (y , x%y)

  val g:Int = gcd(x, y)

  def numer = x/g
  def denom = y/g

  override def toString = s"$numer/$denom"

  def +(other:Rational):Rational = new Rational(
    this.numer*other.denom + other.numer*this.denom,
    this.denom * other.denom
  )

  def -(other:Rational):Rational = new Rational(
    this.numer*other.denom - other.numer*this.denom,
    this.denom * other.denom
  )

  def *(other:Rational):Rational = new Rational(
    this.numer*other.numer, this.denom*other.denom
  )

  def unary_~ : Rational = new Rational(this.denom, this.numer)

  def /(other:Rational):Rational = this * ~other
}
