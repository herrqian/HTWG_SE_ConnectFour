package de.htwg.se.connect_four.controller

import de.htwg.se.connect_four.controller.GameStatus.GameStatus

case class StatelikeIDLE(var gameStatus: GameStatus) extends Statelike {
  override def writeName(gamestate: Gamestate, state: String): Unit = {
    gamestate.setstate(StatelickWIN(GameStatus.WIN))
  }
}
