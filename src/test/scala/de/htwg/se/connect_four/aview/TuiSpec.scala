package de.htwg.se.connect_four.aview

import de.htwg.se.connect_four.model.{Cell, Grid, Matrix}
import org.scalatest.{Matchers, WordSpec}

class TuiSpec extends WordSpec with Matchers {
  "a Tui" when {
    "his function processInputLine is to test" should {
      val tui = new Tui
      "input n will create a new grid" in {
        val new_grid_small = tui.processInputLine("n small", new Grid(2,3))._1
        new_grid_small should be(new Grid(6,7))
        val new_grid_middle = tui.processInputLine("n middle", new Grid(2,3))._1
        new_grid_middle should be(new Grid(10,11))
        val new_grid_huge = tui.processInputLine("n huge", new Grid(2,3))._1
        new_grid_huge should be(new Grid(16,17))
      }
      "input i1 will insert a value to column 1" in {
        val new_grid = tui.processInputLine("i1", new Grid(6,7))._1
        val a_grid = new Grid(6,7).set(5, 1, 1)
        new_grid should be(a_grid)
      }
      "input i1 will return a error because this column is full" in {
        val error = tui.processInputLine("i1", Grid(new Matrix[Cell](Vector(Vector(Cell(0),Cell(2)),Vector(Cell(0),Cell(1))))))._2
        error should be((-1,-1))
      }
    }
    "his function checkBottom is to test" should {
      val tui = new Tui
      "insert at the location in the grid" in {
        val a_grid = new Grid(6,7).set(5, 1, 1)
        val new_grid = tui.checkBottom(new Grid(6,7), 1, 1)
        new_grid._1 should be(a_grid)
        new_grid._2 should be(5)
      }
      "insertion failed because the column is full" in {
        val new_grid = tui.checkBottom(Grid(new Matrix[Cell](Vector(Vector(Cell(0),Cell(2)),Vector(Cell(0),Cell(1))))), 1, 1)
        new_grid._2 should be(-1)
      }
    }
    "his function check4number is to test" should {
      val tui = new Tui
      "return true" in {
        tui.check4number(Vector(Cell(1),Cell(1),Cell(1),Cell(1))) should be(true)
      }
      "return false" in {
        tui.check4number(Vector(Cell(0),Cell(1),Cell(0),Cell(1),Cell(0))) should be(false)
        tui.check4number(Vector(Cell(1),Cell(1),Cell(0),Cell(1),Cell(1))) should be(false)
        tui.check4number(Vector(Cell(2),Cell(1),Cell(0),Cell(1),Cell(2))) should be(false)
        tui.check4number(Vector(Cell(1),Cell(1),Cell(2),Cell(1),Cell(1))) should be(false)
      }
    }
  }
}
