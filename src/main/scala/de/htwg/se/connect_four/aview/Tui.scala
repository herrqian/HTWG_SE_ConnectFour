package de.htwg.se.connect_four.aview

import de.htwg.se.connect_four.model.{Grid, Cell, Matrix, Player}

class Tui {

  def processInputLine(input: String, grid: Grid): Grid = {
    input match {
      case "q" => grid
      case "n" => new Grid(6, 7)
      case _ => input.toList.filter(c => c != ' ') match {
          case 'i' :: column :: value :: Nil => checkBottom(grid, column.asDigit, value.asDigit)
          case _ => grid
        }
    }
  }

  def checkBottom(grid: Grid, column: Int, value: Int): Grid = {
    for (i <- grid.cells.row - 1 to 0 by -1) {
      if (grid.col(column).cell(i).equals(Cell(0))) {
        return grid.set(i, column, value)
      }
    }
    grid
  }
}