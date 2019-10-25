package de.htwg.se.connect_four

import de.htwg.se.connect_four.aview.Tui
import de.htwg.se.connect_four.model.{Grid, Player}

import scala.io.StdIn

object ConnectFour {
  import scala.io.StdIn.readLine
    var grid = new Grid(7,6)
    val tui = new Tui

    def main(args: Array[String]): Unit = {
      var input: String = ""

      do {
        println(grid.toString)
        input = readLine()
        grid = tui.processInputLine(input, grid)
      } while (input != "q")
    }
}