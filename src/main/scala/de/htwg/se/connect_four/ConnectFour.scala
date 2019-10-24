package de.htwg.se.connect_four

import de.htwg.se.connect_four.model.Player

import scala.io.StdIn

object ConnectFour {
  def main(args: Array[String]): Unit = {
    println("please give your player name:")
    val playername = StdIn.readLine()
    val player = Player(playername)
    println(s"$player, welcome to Connect Four")
  }
}
