package de.htwg.se.connect_four.aview

import de.htwg.se.connect_four.controller.{Controller, GameStatus}
import de.htwg.se.connect_four.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)
  var winnerCheck = false

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