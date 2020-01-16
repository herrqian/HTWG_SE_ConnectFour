package de.htwg.se.connect_four.aview.gui

import scala.swing._
import javax.swing.table._

import scala.swing.event._
import de.htwg.se.connect_four.controller.controllerComponent.{CellChanged, ControllerInterface, GridSizeChanged, WinEvent}


class CellPanel(row: Int, column: Int, controller: ControllerInterface) extends FlowPanel {

  val label =
    new Label {
      text = " " //controller.grid.cell(row, column).toString
      font = new Font("Verdana", 1, 36)
    }

  var winnerCheck = false;
  def redraw = {
    label.text = " " //controller.grid.cell(row, column).toString
    this.background = if (controller.grid.cell(row,column).value == 0) {
      new Color(255,255,255)
    } else if (controller.grid.cell(row, column).value == 1) {
      new Color(255,0,0)
    } else {
      new Color(0,0,255)
    }
    repaint
  }

  contents += new BoxPanel(Orientation.Vertical) {
    contents += label
    preferredSize = new Dimension(70, 70)
    background = new Color(255,255,255)
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case MouseClicked(src, pt, mod, clicks, pops) => {
        if(!winnerCheck) {
          controller.setValueToBottom(column)
          repaint
        }
        }

      case f: GridSizeChanged => {
        label.text = " " //controller.grid.cell(row, column).toString
        this.background = if (controller.grid.cell(row,column).value == 0) {
          new Color(255,255,255)
        } else if (controller.grid.cell(row, column).value == 1) {
          new Color(255,0,0)
        } else {
          new Color(0,0,255)
        }
        repaint
      }

      case e: CellChanged => {
        label.text = " " //controller.grid.cell(row, column).toString
        this.background = if (controller.grid.cell(row,column).value == 0) {
          new Color(255,255,255)
        } else if (controller.grid.cell(row, column).value == 1) {
          new Color(255,0,0)
        } else {
          new Color(0,0,255)
        }
        repaint
      }

      case d: WinEvent => {
        label.text = " " //controller.grid.cell(row, column).toString
        this.background = if (controller.grid.cell(row,column).value == 0) {
          new Color(255,255,255)
        } else if (controller.grid.cell(row, column).value == 1) {
          new Color(255,0,0)
        } else {
          new Color(0,0,255)
        }
        winnerCheck = true
        repaint
      }
    }
  }
}