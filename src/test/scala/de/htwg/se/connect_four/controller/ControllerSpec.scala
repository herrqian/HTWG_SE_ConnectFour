package de.htwg.se.connect_four.controller
import de.htwg.se.connect_four.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.connect_four.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.connect_four.util.Observer
import org.scalatest.{Matchers, WordSpec}
class ControllerSpec extends WordSpec with Matchers {
  "a controller" when {
    "observed by an Observer" should {
      val aGrid = new Grid(2, 3)
      val controller = new Controller(aGrid)
      val observer = new Observer() {
        var updated: Boolean = false

        def isUpdated: Boolean = updated

        override def update(): Boolean = {
          updated = true
          true
        }
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.createEmptyGrid("Grid Small")
        observer.updated should be(true)
        controller.grid.size should be(42)
      }
      "notify its Observer after setting a cell" in {
        controller.setValueToBottom(0)
        observer.updated should be(true)
        controller.grid.cell(5, 0).value should be(1)
        controller.setValueToBottom(1)
        observer.updated should be(true)
        controller.grid.cell(5, 1).value should be(2)
        controller.setValueToBottom(2)
        observer.updated should be(true)
        controller.grid.cell(5, 2).value should be(1)
        controller.setValueToBottom(3)
        observer.updated should be(true)
        controller.grid.cell(5, 3).value should be(2)
      }
      "test the checkWinner function" in {
        controller.checkWinner(0,0) should be(false)
        controller.checkWinner(5,0) should be(false)
      }
      "test the getTrun function" in {
        controller.getTurn(0) should be(true)
      }
      "test the changeTurn function" in {
        controller.changeTurn()
        controller.getTurn(0) should be(false)
      }
      "test the currentPlayer function" in {
        controller.currentPlayer() should be(2)
      }
    }
  }
}
