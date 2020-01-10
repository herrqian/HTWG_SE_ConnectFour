package de.htwg.se.connect_four.model.fileIOComponent.fileIoJsonImpl

import java.io.{File, PrintWriter}
import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.connect_four.ConnectFourModule
import de.htwg.se.connect_four.model.fileIOComponent.FileIOInterface
import de.htwg.se.connect_four.model.gridComponent.{GridInterface, CellInterface}
import play.api.libs.json._
import scala.io.Source

class FileIO extends FileIOInterface {
  override def load: GridInterface = {
    var grid :GridInterface = null
    val source: String = Source.fromFile("grid.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "grid" \ "size").get.toString
    val rows = (json \ "grid" \ "rows").get.toString.toInt
    val cols = (json \ "grid" \ "cols").get.toString.toInt
    val injector = Guice.createInjector(new ConnectFourModule)
    size match {
      case "small" => grid = injector.instance[GridInterface](Names.named("Grid Small"))
      case "middle" => grid = injector.instance[GridInterface](Names.named("Grid Middle"))
      case "huge" => grid = injector.instance[GridInterface](Names.named("Grid Huge"))
      case _ =>
    }
    for (index <- 0 until rows * cols) {
      val row = (json \\ "row")(index).as[Int]
      val col = (json \\ "col")(index).as[Int]
      val cell = (json \\ "cell")(index)
      val value = (cell \ "value").as[Int]
      grid = grid.set(row,col,value)
    }
    grid
  }

  override def save(grid: GridInterface): Unit = {
    val pw = new PrintWriter(new File("grid.json"))
    pw.write(Json.prettyPrint(gridToJson(grid)))
    pw.close()
  }

  implicit val cellWrites = new Writes[CellInterface] {
    override def writes(o: CellInterface): JsValue = {
      Json.obj(
        "value" -> o.value
      )
    }
  }

  def gridToJson(interface: GridInterface): JsValue = {
    Json.obj(
      "grid" -> Json.obj(
        "size" -> JsString(sizeOfGrid(interface)),
        "rows"->JsNumber(interface.rows),
        "cols"->JsNumber(interface.cols),
        "cells" -> Json.toJson(
          for {
            row <- 0 until interface.rows;
            col <- 0 until interface.cols
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(interface.cell(row, col))
            )
          }
        )
      )
    )
  }

  def sizeOfGrid(interface: GridInterface): String =  {
    if (interface.rows == 6 && interface.cols == 7) {
      return "small"
    }
    if (interface.rows == 10 && interface.cols == 11)
      return "middle"
    "huge"
  }


}

