package de.htwg.se.connect_four.model

case class Cell(value: Int) {

  def isSet: Boolean = {
    value != 0
  }
  def set(value: Int): Cell = Cell(value)
  override def toString: String = value.toString
}
