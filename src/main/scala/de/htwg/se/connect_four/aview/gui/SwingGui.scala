package de.htwg.se.connect_four.aview.gui

import de.htwg.se.connect_four.aview.gui.CellPanel

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.connect_four.controller.controllerComponent.{CellChanged, ControllerInterface, GridSizeChanged}

import scala.io.Source._

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGui(controller: ControllerInterface) extends Frame {
  listenTo(controller)
  var cells = Array.ofDim[CellPanel](controller.getGridRow, controller.getGridCol)
  title = "Connect Four"
  def gridPanel = new GridPanel(controller.getGridRow, controller.getGridCol) {
    for {
      row <- 0 until controller.getGridRow
      column <- 0 until controller.getGridCol
    } {
      val cellpanel = new CellPanel(row,column,controller)
      cells(row)(column) = cellpanel
      contents += cellpanel
      listenTo(cellpanel)
    }
  }

  val statusline = new TextField(controller.getGameStatus().toString, 20)

  contents = new BorderPanel {
    add(gridPanel, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.North)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") { controller.createEmptyGrid("Grid Small") })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo })
      contents += new MenuItem(Action("Redo") { controller.redo })
    }
    contents += new Menu("Options") {
      mnemonic = Key.O
      contents += new MenuItem(Action("Size 6*7") { controller.createEmptyGrid("Grid Small") })
      contents += new MenuItem(Action("Size 10*11") { controller.createEmptyGrid("Grid Middle") })
      contents += new MenuItem(Action("Size 16*17") { controller.createEmptyGrid("Grid Huge") })
    }
  }

  visible =true
  redraw

  reactions += {
    case event: GridSizeChanged => {
      val row = controller.getGridRow
      val col = controller.getGridCol
      resize(row,col)
    }
    case event: CellChanged     => redraw
  }

  def resize(gridrow: Int, gridcol:Int) = {
    cells = Array.ofDim[CellPanel](controller.getGridRow, controller.getGridCol)
    contents = new BorderPanel {
      add(gridPanel, BorderPanel.Position.Center)
      add(statusline, BorderPanel.Position.North)
    }
  }

  def redraw = {
    for {
      row <- 0 until controller.getGridRow
      column <- 0 until controller.getGridCol
    } cells(row)(column).redraw
    statusline.text = controller.getGameStatus().toString
    repaint
  }
}