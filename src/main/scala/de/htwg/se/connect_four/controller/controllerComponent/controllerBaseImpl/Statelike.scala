package de.htwg.se.connect_four.controller.controllerComponent.controllerBaseImpl

trait Statelike {
  var gameStatus:GameStatus
  def writeName(gamestate: Gamestate, state: String): Unit
}
