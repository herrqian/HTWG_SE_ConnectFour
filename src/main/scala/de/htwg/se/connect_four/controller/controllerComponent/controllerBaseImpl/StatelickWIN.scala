package de.htwg.se.connect_four.controller.controllerComponent.controllerBaseImpl

case class StatelickWIN(var gameStatus: GameStatus) extends Statelike {
  override def writeName(gamestate: Gamestate, state: String): Unit = {
    gamestate.setstate(StatelickWIN(GameStatus.IDLE))
  }
}
