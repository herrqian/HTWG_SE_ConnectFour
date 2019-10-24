package de.htwg.se.connect_four.model

case class Grid(cells: Matrix[Cell]) {
  def this(size: Int) = this(new Matrix[Cell](size, Cell(0)))
  val size: Int = cells.size
  def cell(row:Int, col: Int): Cell=cells.cell(row,col)
  def set(row:Int, col: Int, value:Int): Grid=copy(cells.replaceCell(row,col,Cell(value)))
  def row(row: Int):House=House(cells.rows(row))
  def col(col:Int):House=House(cells.rows.map(row=>row(col)))
}

case class House(private val cells:Vector[Cell]) {
  def cell(index:Int):Cell=cells(index)
}