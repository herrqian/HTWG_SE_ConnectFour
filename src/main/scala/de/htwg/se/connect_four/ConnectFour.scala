package de.htwg.se.connect_four

import de.htwg.se.connect_four.aview.Tui
import de.htwg.se.connect_four.model.{Grid, Player}
import scala.io.StdIn.readLine
import util.control.Breaks._

object ConnectFour {
    var grid = new Grid(6,7)
    val tui = new Tui

    def main(args: Array[String]): Unit = {
      var input: String = ""
      println("Player 1 please type in your name:")
      val player1 = Player(readLine())
      var input2: String = ""
      println("Player 2 please type in your name:")
      val player2 = Player(readLine())
      println(s"$player1 and $player2, Welcome to Connect Four")

      do {
        if (tui.getTurn(0)) {
          println(s"$player1, its your turn!")
        } else if (tui.getTurn(1)) {
          println(s"$player2, its your turn!")
        }
        println(grid.toString)
        input = readLine()
        val result = tui.processInputLine(input, grid)
        grid = result._1
        val flag = result._2
        if (flag != (-1, -1) && !input.contains("n")) {
          if (tui.checkWinner(grid, flag._1, flag._2)) {
            println("player"+tui.currentPlayer() + " win!")
            break
          } else if (!input.contains("n")) {tui.changeTurn()}
        }
      } while (input != "q")
    }
}