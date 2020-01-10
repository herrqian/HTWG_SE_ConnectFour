package de.htwg.se.connect_four.controller.controllerComponent

import de.htwg.se.connect_four.controller.controllerComponent.GameStatus.GameStatus

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def createEmptyGrid(s: String): Unit
  def setValueToBottom(col:Int): Unit
  def checkWinner(row:Int,col:Int):Boolean
  def getTurn(playerNumber:Int):Boolean
  def changeTurn():Unit
  def currentPlayer():Int
  def gridToString:String
  def resetPlayerList():Unit
  def getGameStatus():GameStatus
  def undo:Unit
  def redo:Unit
  def save:Unit
  def load:Unit
}

import scala.swing.event.Event

class CellChanged extends Event
class GridSizeChanged(size:String) extends Event
class WinEvent extends Event