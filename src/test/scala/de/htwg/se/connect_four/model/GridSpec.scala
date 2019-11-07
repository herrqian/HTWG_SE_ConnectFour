package de.htwg.se.connect_four.model

import org.scalatest.{Matchers, WordSpec}

class GridSpec extends WordSpec with Matchers {
  "A Grid is the playingfield of Connect Four. A Grid" when {
    "to be constructed" should {
      "be created with the length of its edges as size. Practically relevant are size 4*5, 6*8, and 10*12" in {
        val smallgrid = new Grid(4,5)
        val middlegrid = new Grid(6,8)
        val biggrid = new Grid(10,12)
      }
      "for test purposes only created with a Matrix of Cells" in {
        val awkwardGrid = Grid(new Matrix(2, 4, Cell(0)))
        val testGrid = Grid(Matrix[Cell](Vector(Vector(Cell(0), Cell(0), Cell(0)), Vector(Cell(0), Cell(0)))))
      }
    }
    "created properly but empty" should {
      val smallGrid = new Grid(4, 5)
      val normalGrid = new Grid(6,8)
      val awkwardGrid = new Grid(10,12)
      "give access to its Cells" in {
        smallGrid.cell(0, 0) should be(Cell(0))
        smallGrid.cell(0, 1) should be(Cell(0))
        smallGrid.cell(1, 0) should be(Cell(0))
        smallGrid.cell(1, 1) should be(Cell(0))
      }
      "allow to set individual Cells and remain immutable" in {
        val changedGrid = smallGrid.set(0, 0, 1)
        changedGrid.cell(0, 0) should be(Cell(1))
        smallGrid.cell(0, 0) should be(Cell(0))
      }
    }
    "prefilled with values 1 to n" should {
      val tinyGrid = Grid(new Matrix[Cell](Vector(Vector(Cell(1)))))
      val smallGrid = Grid(new Matrix[Cell](Vector(Vector(Cell(1), Cell(2), Cell(3)), Vector(Cell(4), Cell(5), Cell(6)), Vector(Cell(7), Cell(8), Cell(9)))))
      "have the right values in the right places" in {
        smallGrid.cell(0, 0) should be(Cell(1))
        smallGrid.cell(0, 1) should be(Cell(2))
        smallGrid.cell(1, 0) should be(Cell(4))
        smallGrid.cell(1, 1) should be(Cell(5))
      }
      "have Houses with the right Cells" in {
        tinyGrid.row(0).cell(0) should be(Cell(1))
        tinyGrid.col(0).cell(0) should be(Cell(1))

        smallGrid.row(0).cell(0) should be(Cell(1))
        smallGrid.row(0).cell(1) should be(Cell(2))
        smallGrid.row(1).cell(0) should be(Cell(4))
        smallGrid.row(1).cell(1) should be(Cell(5))
        smallGrid.col(0).cell(0) should be(Cell(1))
        smallGrid.col(0).cell(1) should be(Cell(4))
        smallGrid.col(1).cell(0) should be(Cell(2))
        smallGrid.col(1).cell(1) should be(Cell(5))
        smallGrid.col(2).getCells should be(Vector(Cell(3), Cell(6), Cell(9)))
      }
      "have Houses with the diagonal" in {
        smallGrid.link_diagonal(1,0).cell(0) should be(Cell(4))
        smallGrid.link_diagonal(1, 0).cell(1) should be(Cell(2))
        smallGrid.right_diagonal(1,0).cell(0) should be(Cell(4))
        smallGrid.right_diagonal(1,0).cell(1) should be(Cell(8))
        smallGrid.right_diagonal(1,1).cell(0) should be(Cell(1))
        smallGrid.right_diagonal(1,1).cell(1) should be(Cell(5))
        smallGrid.link_diagonal(1,1).getCells should be(Vector(Cell(7),Cell(5),Cell(3)))
        smallGrid.right_diagonal(1,1).getCells should be(Vector(Cell(1), Cell(5), Cell(9)))
        smallGrid.link_diagonal(1,0).getCells should be(Vector(Cell(4), Cell(2)))
        smallGrid.right_diagonal(1,0).getCells should be(Vector(Cell(4), Cell(8)))
      }
    }
  }
}
