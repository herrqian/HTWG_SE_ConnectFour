package de.htwg.se.connect_four.aview

import de.htwg.se.connect_four.model.{Grid, Cell, Matrix, Player}

class Tui {

  var playerList = Array(true, false)

  def processInputLine(input: String, grid:Grid):(Grid, (Int,Int)) = {
    input match {
      case "q" => (grid, (0,0))
      case "n" => {
        playerList = Array(true, false)
        (new Grid(6,7), (0,0))
      }
      case _ => {
        input.toList.filter(c => c != ' ') match {
          case 'i' :: column :: Nil => {
            var value = 0
            if (playerList(0)) {
              value = 1
            } else {
              value = 2
            }
            if (column.asDigit > grid.cells.col-1) {
              println("wrong input, repeat your turn!")
              return (grid, (-1,-1))
            }
            val checkGrid = checkBottom(grid, column.asDigit, value)
            if (checkGrid._2 == -1) {
              println("cant insert at this column, repeat your turn!")
              return (grid, (-1,-1))
            }
            (checkGrid._1, (checkGrid._2, column.asDigit))
          }
          case _ => {
            println("wrong input, repeat your turn!")
            (grid, (-1,-1))
          }
        }
      }
    }
  }
  def checkBottom(grid: Grid, column: Int, value: Int): (Grid, Int) = {
    for (i <- grid.cells.row-1 to 0 by -1){
      if (grid.col(column).cell(i).equals(Cell(0))) {
        return (grid.set(i, column, value), i)
      }
    }
    (grid, -1)
  }

  def checkWinner(grid: Grid, row: Int, col: Int): Boolean = {
    if (check4number(grid.col(col).getCells)) {
      return true
    } else if (check4number(grid.row(row).getCells)) {
      return true
    } else if (check4number(grid.link_diagonal(row, col).getCells)) {
      return true
    } else if (check4number(grid.right_diagonal(row, col).getCells)) {
      return true
    } else {
      false
    }
  }

  def check4number(vector: Vector[Cell]): Boolean ={
    var counter = 0
    for (cell <- vector) {
      if (cell.equals(Cell(currentPlayer()))) {
        counter = counter +1
        if (counter == 4) {
          return true
        }
      } else {
        counter = 0
      }
    }
    false
  }

  def getTurn(playerNumber: Int): Boolean = {
    playerList(playerNumber)
  }

  def changeTurn(): Unit = {
    playerList(0) = !playerList(0)
    playerList(1) = !playerList(1)
  }

  def currentPlayer(): Int = {
    if (playerList(0)) {
      return 1
    }
    2
  }
}