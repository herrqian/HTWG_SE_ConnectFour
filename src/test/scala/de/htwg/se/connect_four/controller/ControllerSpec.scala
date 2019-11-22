package de.htwg.se.connect_four.controller
import de.htwg.se.connect_four.model.Grid
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

        override def update(): Unit = updated = true
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.createEmptyGrid(4, 5)
        observer.updated should be(true)
        controller.grid.size should be(20)
      }
      "notify its Observer after setting a cell" in {
        controller.setValueToBottom(0)
        observer.updated should be(true)
        controller.grid.cell(3, 0).value should be(1)
        controller.setValueToBottom(1)
        observer.updated should be(true)
        controller.grid.cell(3, 1).value should be(1)
        controller.setValueToBottom(2)
        observer.updated should be(true)
        controller.grid.cell(3, 2).value should be(1)
        controller.setValueToBottom(3)
        observer.updated should be(true)
        controller.grid.cell(3, 3).value should be(1)
        controller.setValueToBottom(10) should be(-1)
        controller.changeTurn()
        controller.setValueToBottom(4)
        controller.grid.cell(3, 4).value should be(2)
        controller.changeTurn()
        controller.setValueToBottom(0)
        controller.setValueToBottom(0)
        controller.setValueToBottom(0)
        controller.setValueToBottom(0) should be(-1)
      }
      "test the checkWinner function" in {
        controller.checkWinner(0,1) should be(false)
        controller.checkWinner(3,3) should be(true)
        controller.setValueToBottom(1)
        controller.setValueToBottom(1)
        controller.setValueToBottom(2)
        controller.setValueToBottom(2)
        controller.changeTurn()
        controller.setValueToBottom(3)
        controller.changeTurn()
        controller.setValueToBottom(3)
        controller.setValueToBottom(3)
        controller.checkWinner(2,1) should be(true)
        controller.checkWinner(2,2) should be(true)
        controller.checkWinner(0,0) should be(true)
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
