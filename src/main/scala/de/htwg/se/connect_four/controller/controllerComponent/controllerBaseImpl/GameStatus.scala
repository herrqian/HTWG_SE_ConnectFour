package de.htwg.se.connect_four.controller.controllerComponent.controllerBaseImpl

object GameStatus extends Enumeration {
  type GameStatus = Value
  val IDLE, WIN, PLAYERTURN= Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    WIN -> "Game over",
    PLAYERTURN -> "Player turn"
  )

  def message(gameStatus: GameStatus)  ={
    map(gameStatus)
  }
}
