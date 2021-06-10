trait Node[T] {
  def head:T
  def tail: Node[T]
  def isEmpty: Boolean
  def append (value:T):Node[T]
  def :: (value:T):Node[T] = append(value)
}

class Nil[T] extends Node[T] {
  override def head:Nothing = throw new NoSuchElementException("list is empty")
  override def tail: Nothing = throw new NoSuchElementException("list is empty")
  override def isEmpty:Boolean = true
  def append(value: T):Node[T] = new Cons[T](value, new Nil)

  override def toString: String = "."
}

class Cons[T](val head:T , val tail:Node[T]) extends Node[T] {
  def isEmpty:Boolean = false
  def append(value:T):Node[T] = new Cons[T](value, this)

  override def toString: String = s"${head} --> ${tail}"
}

new Nil append 1 append 2
2 :: 1 :: new Nil
new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Nil()))))