package de.htwg.se.connect_four.controller

case class Gamestate(var mystate:Statelike) {

  def setstate(statelike: Statelike):Unit= mystate=statelike

  def writeName(name: String): Unit = {
    mystate.writeName(this,name)
  }
}
