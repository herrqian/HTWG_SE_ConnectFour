package de.htwg.se.connect_four.model

import org.scalatest.{WordSpec, Matchers}

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    "new" should {
      val name ="Your Name"
      val player = Player(name)
      "have a name" in {
        player.name should be("Your Name")
      }
      "have a nice String representation" in {
        player.toString should be("Your Name")
      }
    }
  }
}
