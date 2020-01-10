package de.htwg.se.connect_four.aview.gui

import de.htwg.se.connect_four.aview.gui.CellPanel

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.connect_four.controller.controllerComponent.ControllerInterface

import scala.io.Source._

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui(controller: ControllerInterface) extends Frame {
  listenTo(controller)
  contents = new GridPanel(6, 7) {
    for {
      row <- 0 until 6
      column <- 0 until 7
    } {
      contents += new CellPanel(row,column,controller)
    }
  }
  visible = true
}