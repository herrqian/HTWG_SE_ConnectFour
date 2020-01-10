package de.htwg.se.connect_four.aview

import de.htwg.se.connect_four.controller.{Controller, GameStatus}
import de.htwg.se.connect_four.util.Observer
import de.htwg.se.connect_four.model.Player
import de.htwg.se.connect_four.controller.{CellChanged, GridSizeChanged, winEvent}

import scala.io.StdIn
import scala.swing.Reactor

class Tui(controller: Controller) extends Reactor {

  listenTo(controller)
  var winnerCheck = false
  var player1 = ""
  var player2 = ""
  var input = ""

  reactions += {
    case event: GridSizeChanged => printGrid
    case event: CellChanged     => printGrid
    case event: winEvent        => {printGrid
                                    printEnd(event.winner)}
  }

  def processInputLineStart(): Unit = {

    println("Player 1 please type in your name:")
    player1 = StdIn.readLine()
    println("Player 2 please type in your name:")
    player2 = StdIn.readLine()
    println(s"$player1 and $player2, Welcome to Connect Four")
    println(controller.gridToString)

    processInputLineLoop()
  }

  def processInputLineLoop(): Unit = {
    do {
      if (controller.getTurn(0)) {
        println(s"$player1, its your turn!")
      } else if (controller.getTurn(1)) {
        println(s"$player2, its your turn!")
      }
      input = StdIn.readLine()
      processInputLine(input)
    } while (input != "q")
  }

  def processInputLine(input: String): Unit = {
    input match {
      case "q" => println("The game exit")
      case "n small" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid("Grid Small")
      case "n middle" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid("Grid Middle")
      case "n huge" =>
        controller.playerList = Array(true, false)
        controller.createEmptyGrid("Grid Huge")
      case "undo" => controller.undo
      case "redo" => controller.redo
      case _ =>
        input.toList.filter(c => c != ' ') match {
          case 'i' :: column :: Nil =>
            controller.setValueToBottom(column.asDigit)
          case _ =>
            println("wrong input, repeat your turn!")
        }
    }
  }

  def printGrid(): Boolean = {
    println(controller.gridToString)
    println(GameStatus.message(controller.gameStatus.mystate.gameStatus))
    if (controller.gameStatus.mystate.gameStatus.equals(GameStatus.WIN))
      winnerCheck = true
    true
  }

  def printEnd(winner: String): Unit = {
    println("Player " + winner + " hat gewonnen!")
    controller.gamereset()
    controller.createEmptyGrid("Grid Small")
    processInputLineStart()
  }
}