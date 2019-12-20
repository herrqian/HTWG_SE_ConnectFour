package de.htwg.se.connect_four.controller

import de.htwg.se.connect_four.controller.GameStatus.GameStatus

case class StatelickWIN(var gameStatus: GameStatus) extends Statelike {
  override def writeName(gamestate: Gamestate, state: String): Unit = {
    gamestate.setstate(StatelickWIN(GameStatus.IDLE))
  }
}
