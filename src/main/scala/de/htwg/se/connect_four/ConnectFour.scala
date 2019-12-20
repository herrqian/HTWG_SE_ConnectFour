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
      tui.processInputLineStart()
    }
}