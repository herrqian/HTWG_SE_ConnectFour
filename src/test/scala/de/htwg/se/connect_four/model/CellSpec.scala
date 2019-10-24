package de.htwg.se.connect_four.model

import org.scalatest.{Matchers, WordSpec}

class CellSpec extends WordSpec with Matchers {

  "a Cell" when {
    "not set to any value" should {
      val emtpyCell = Cell(0)
      "habe value 0" in {
        emtpyCell.value should be(0)
      }
      "not be set" in {
        emtpyCell.isSet should be(false)
      }
    }
    "set to a specific value" should {
      val nonEmptyCell = Cell(1)
      "return that value" in {
        nonEmptyCell.value should be(1)
      }
      "be set" in {
        nonEmptyCell.isSet should be(true)
      }
    }
  }

}
