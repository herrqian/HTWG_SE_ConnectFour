package de.htwg.se.connect_four.model

case class Matrix[T] (rows:Vector[Vector[T]]) {
  def this(row:Int, col:Int, filling:T) = this(Vector.tabulate(row, col){(row, col) => filling})
  val row:Int = rows.size
  val col :Int = rows.head.size
  val size:Int = row * col
  def cell(row:Int, col:Int):T = rows (row)(col)
  def fill (filling:T):Matrix[T]= copy( Vector.tabulate(row, col){(row, col) => filling})
  def replaceCell(row:Int, col:Int, cell:T):Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))
}
