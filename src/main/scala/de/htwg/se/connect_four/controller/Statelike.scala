package de.htwg.se.connect_four.controller

import de.htwg.se.connect_four.controller.GameStatus.GameStatus

trait Statelike {
  var gameStatus:GameStatus
  def writeName(gamestate: Gamestate, state: String): Unit
}
