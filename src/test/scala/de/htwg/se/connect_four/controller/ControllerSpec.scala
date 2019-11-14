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
        controller.setBottom(0)
        observer.updated should be(true)
        controller.grid.cell(3, 0).value should be(1)
      }
      "test the checkWinner function" in {
        controller.checkWinner(0,0) should be(false)
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
