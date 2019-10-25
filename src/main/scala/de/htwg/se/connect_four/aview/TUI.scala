package de.htwg.se.connect_four.aview

import de.htwg.se.connect_four.model.{Grid, Cell, Matrix, Player}

class Tui {

  def processInputLine(input: String, grid:Grid):Grid = {
    input match {
      case "q" => grid
      case "n" => new Grid(3,3)
      case _ => {
        input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
          case row :: column :: value :: Nil => grid.set(row, column, value)
          case _ => grid
        }
      }
    }
  }
}