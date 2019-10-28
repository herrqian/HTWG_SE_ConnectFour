package de.htwg.se.connect_four

import de.htwg.se.connect_four.aview.Tui
import de.htwg.se.connect_four.model.{Grid, Player}

object ConnectFour {
  import scala.io.StdIn.readLine
    var grid = new Grid(6,7)
    val tui = new Tui

    def main(args: Array[String]): Unit = {
      var input: String = ""
      println("Please give your name:")
      input = readLine()
      val player1 = Player(input)
      println(s"$player1, welcome to Connect Four")
      do {
        println(grid.toString)
        input = readLine()
        grid = tui.processInputLine(input, grid)
      } while (input != "q")
    }
}