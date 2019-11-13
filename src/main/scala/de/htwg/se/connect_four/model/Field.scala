package de.htwg.se.connect_four.model

case class Field(private val cells:Vector[Cell]) {
  def cell(index:Int):Cell=cells(index)
  def getCells: Vector[Cell] = cells
}
