package de.htwg.se.connect_four

import de.htwg.se.connect_four.aview.Tui
import de.htwg.se.connect_four.model.{Grid, Player}

import scala.io.StdIn.readLine

object ConnectFour {
    var grid = new Grid(6,7)
    val tui = new Tui

    def main(args: Array[String]): Unit = {
      var input: String = ""
      println("Please give your name:")
      input = readLine()
      println(s"$input, welcome to Connect Four")

      do {
        println(grid.toString)
        input = readLine()
        grid = tui.processInputLine(input, grid)
      } while (input != "q")
    }
}