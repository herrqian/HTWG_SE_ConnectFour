package de.htwg.se.connect_four.controller

import de.htwg.se.connect_four.model.{Cell, GridInterface}
import de.htwg.se.connect_four.util.{Observable, UndoManager}
import de.htwg.se.connect_four.controller.GameStatus._
import de.htwg.se.connect_four.model.GridFactory

class Controller(var grid: GridInterface) extends Observable {

  var playerList = Array(true, false)
  var gameStatus: GameStatus = IDLE
  private val undoManager = new UndoManager

  def createEmptyGrid(s:String): Unit = {
    grid = GridFactory.getGrid(s)
    notifyObservers()
  }

  def setValueToBottom(column: Int): Unit = {
    val value = if (playerList(0)) {
      1
    } else {
      2
    }
    for (i <- grid.cells.row - 1 to 0 by -1) {
      if (grid.col(column).cell(i).equals(Cell(0))) {
        undoManager.doStep(new SetCommand(i,column, value, this))
        if (this.checkWinner(i, column)) {
          printf("The player%s is the winner!\n", this.currentPlayer().toString)
          gameStatus = WIN
          notifyObservers()
          return
        } else {
          this.changeTurn()
          notifyObservers()
          return
        }
      }
    }
  }

  def checkWinner(row: Int, col: Int): Boolean = {
    if (check4number(grid.col(col).getCells)) {
      true
    } else if (check4number(grid.row(row).getCells)) {
      true
    } else if (check4number(grid.link_diagonal(row, col).getCells)) {
      true
    } else if (check4number(grid.right_diagonal(row, col).getCells)) {
      true
    } else {
      false
    }
  }

  private def check4number(vector: Vector[Cell]): Boolean = {
    var counter = 0
    for (cell <- vector) {
      if (cell.equals(Cell(currentPlayer()))) {
        counter = counter + 1
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

  def gridToString: String = grid.toString

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers()
  }

  def redo: Unit ={
    undoManager.redoStep
    notifyObservers()
  }
}
