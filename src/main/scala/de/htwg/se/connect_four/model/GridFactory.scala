package de.htwg.se.connect_four.model

object GridFactory {
  def getGrid(s:String): GridInterface= {
    if (s == "Grid Small") new Grid(6,7)
    else if (s == "Grid Middle") new Grid(10,11)
    else new Grid(16,17)
  }
}
