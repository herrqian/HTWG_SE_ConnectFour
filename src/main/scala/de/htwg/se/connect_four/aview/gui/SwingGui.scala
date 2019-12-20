package de.htwg.se.connect_four.aview.gui


import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.connect_four.controller._
import scala.io.Source._

class CellClicked(val column: Int) extends Event

class SwingGui(controller: Controller) extends Frame {
  object HelloWorld extends SimpleSwingApplication {
    def top = new MainFrame {
      title = "Hello, World!"
      contents = new Button("Click Me!")
    }
  }

}
