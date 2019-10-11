object ScalaTutorial {
  def main(args: Array[String]): Unit = {
    var i = 0
    while (i < 10) {
      println(i)
      i += 1
    }

    for (i <- 1 to 10) {
      println(i)
    }
  }
}
