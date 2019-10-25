package de.htwg.se.connect_four.model

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon

import scala.collection.mutable.ArrayBuffer

case class Grid(cells: Matrix[Cell]) {
  def this(row: Int, col:Int) = this(new Matrix[Cell](row, col, Cell(0)))
  val size: Int = cells.size
  def cell(row:Int, col: Int): Cell=cells.cell(row,col)
  def set(row:Int, col: Int, value:Int): Grid=copy(cells.replaceCell(row,col,Cell(value)))
  def row(row: Int):House=House(cells.rows(row))
  def col(col:Int):House=House(cells.rows.map(row=>row(col)))

  def link_diagonal(row:Int, col:Int):House = {
    var mrow = row
    var mcol = col
    val mvec = ArrayBuffer[Cell]()
    while (mrow < cells.row && mcol > 0) {
      mrow = mrow + 1
      mcol = mcol - 1
    }
    while (mrow >= 0 && mcol < cells.col) {
      mvec.append(cells.cell(mrow, mcol))
      mrow = mrow - 1
      mcol = mcol + 1
    }
    House(mvec.toVector)
  }


  def right_diagonal(row: Int, col: Int):House = {
    var mrow = row
    var mcol = col
    val mvec = ArrayBuffer[Cell]()
    while (mrow > 0 && mcol > 0) {
      mrow = mrow - 1
      mcol = mcol - 1
    }
    while (mrow < cells.row && mcol < cells.col) {
        mvec.append(cells.cell(mrow, mcol))
        mrow = mrow + 1
        mcol = mcol + 1
      }
    House(mvec.toVector)
  }
}

case class House(private val cells:Vector[Cell]) {
  def cell(index:Int):Cell=cells(index)
}