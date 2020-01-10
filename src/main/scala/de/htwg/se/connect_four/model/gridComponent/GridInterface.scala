package de.htwg.se.connect_four.model.gridComponent

import de.htwg.se.connect_four.model.gridComponent.gridBaseImpl.{Cell, Field, Grid, Matrix}

trait GridInterface {
  val cells: Matrix[Cell]

  def size:Int

  def cell(row: Int, col: Int): Cell

  def set(row: Int, col: Int, value: Int): Grid

  def row(row: Int): Field

  def col(col: Int): Field

  def link_diagonal(row: Int, col: Int): Field

  def right_diagonal(row: Int, col: Int): Field
}
