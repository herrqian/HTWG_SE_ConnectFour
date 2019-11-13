package de.htwg.se.connect_four.controller

import de.htwg.se.connect_four.model.Grid
import de.htwg.se.connect_four.util.Observable

class Controller(var grid: Grid) extends Observable{
  def creatEmptyGrid(row:Int, col:Int): Unit = {
      grid = new Grid(row, col)
      notifyObservers()
  }
  //todo:continue to code
  def gridToString: String = grid.toString
}
