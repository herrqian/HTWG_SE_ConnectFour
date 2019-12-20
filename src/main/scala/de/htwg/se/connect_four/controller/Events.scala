package de.htwg.se.sudoku.controller

import scala.swing.event.Event

class CellChanged extends Event
case class GridSizeChanged(newSize: String) extends Event
case class winEvent(winner: String) extends Event