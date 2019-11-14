package de.htwg.se.connect_four

import de.htwg.se.connect_four.aview.Tui
import de.htwg.se.connect_four.controller.Controller
import de.htwg.se.connect_four.model.{Grid, Player}

import scala.io.StdIn.readLine


object ConnectFour {
    val controller = new Controller(new Grid(6,7))
    var grid = new Grid(6,7)
    val tui = new Tui(controller)

    def main(args: Array[String]): Unit = {
      var input: String = ""

      println("Player 1 please type in your name:")
      val player1 = Player(readLine())
      var input2: String = ""
      println("Player 2 please type in your name:")
      val player2 = Player(readLine())
      println(s"$player1 and $player2, Welcome to Connect Four")

      println(controller.gridToString)
      do {
        if (controller.getTurn(0)) {
          println(s"$player1, its your turn!")
        } else if (controller.getTurn(1)) {
          println(s"$player2, its your turn!")
        }
        input = readLine()
        tui.processInputLine(input)
      } while (input != "q" && !tui.winnercheck)
    }
}