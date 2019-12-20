package de.htwg.se.connect_four.aview

import de.htwg.se.connect_four.controller.{Controller, GameStatus}
import de.htwg.se.connect_four.util.Observer
import de.htwg.se.connect_four.model.Player
import scala.io.StdIn

class Tui(controller: Controller) extends Observer {

  controller.add(this)
  var winnerCheck = false
  var player1 = ""
  var player2 = ""
  var input = ""

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
    } while (input != "q" && !winnerCheck)
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

  override def update(): Boolean = {
    println(controller.gridToString)
    println(GameStatus.message(controller.gameStatus))
    if (controller.gameStatus.equals(GameStatus.WIN))
      winnerCheck = true
    controller.gameStatus=GameStatus.IDLE
    true
  }
}