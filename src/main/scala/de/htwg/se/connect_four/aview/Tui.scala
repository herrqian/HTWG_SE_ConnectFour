package de.htwg.se.connect_four.aview

import de.htwg.se.connect_four.controller.Controller
import de.htwg.se.connect_four.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)
  var winnercheck = false

  def processInputLine(input: String): Unit = {
    input match {
      case "q" => println("The game exit")
      case "n small" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid(6, 7)
      case "n middle" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid(10, 11)
      case "n huge" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid(16, 17)
      case _ =>
        input.toList.filter(c => c != ' ') match {
          case 'i' :: column :: Nil =>
            val row_number = controller.setValueToBottom(column.asDigit)
            if (row_number == -1) {
              println("cant insert at this column, repeat your turn!")
            }
            if (controller.checkWinner(row_number, column.asDigit)) {
              printf("The player%s is the winner!\n", controller.currentPlayer().toString)
              winnercheck = true
            } else {
              controller.changeTurn()
            }
          case _ =>
            println("wrong input, repeat your turn!")
        }
    }
  }

  override def update(): Unit = println(controller.gridToString)

  //  def checkBottom(grid: Grid, column: Int, value: Int): (Grid, Int) = {
  //    for (i <- grid.cells.row - 1 to 0 by -1) {
  //      if (grid.col(column).cell(i).equals(Cell(0))) {
  //        return (grid.set(i, column, value), i)
  //      }
  //    }
  //    (grid, -1)
  //  }
  //
  //  def checkWinner(grid: Grid, row: Int, col: Int): Boolean = {
  //    if (check4number(grid.col(col).getCells)) {
  //      true
  //    } else if (check4number(grid.row(row).getCells)) {
  //      true
  //    } else if (check4number(grid.link_diagonal(row, col).getCells)) {
  //      true
  //    } else if (check4number(grid.right_diagonal(row, col).getCells)) {
  //      true
  //    } else {
  //      false
  //    }
  //  }
  //
  //  def check4number(vector: Vector[Cell]): Boolean = {
  //    var counter = 0
  //    for (cell <- vector) {
  //      if (cell.equals(Cell(currentPlayer()))) {
  //        counter = counter + 1
  //        if (counter == 4) {
  //          return true
  //        }
  //      } else {
  //        counter = 0
  //      }
  //    }
  //    false
  //  }
  //
  //  def getTurn(playerNumber: Int): Boolean = {
  //    playerList(playerNumber)
  //  }
  //
  //  def changeTurn(): Unit = {
  //    playerList(0) = !playerList(0)
  //    playerList(1) = !playerList(1)
  //  }
  //
  //  def currentPlayer(): Int = {
  //    if (playerList(0)) {
  //      return 1
  //    }
  //    2
  //  }
}