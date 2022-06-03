import scala.io.Source
import scala.io.StdIn.readLine
import scala.sys.exit

class Operations(file: String) {
  def nth(ns: Iterator[String], n: Int): Iterator[String] = {
    if (n == 1) ns.take(1)
    else ns.drop(n - 1).take(1)
  }

  def line_to_list(s: String, n: Int): Seq[String] = {
    nth(Source.fromFile(s).getLines, n).flatMap(line => line.split("\\s+")).toSeq
  }

  def readfile(s: String) = {
    val filename = s
    val num_lines = Source.fromFile(filename).getLines.size

    while (true) {
      for {i <- 1 to num_lines} {
        var respond = true
        while (respond) {
          val line = line_to_list(filename, i)
          println("English word: " + line.head)
          val your_answer = readLine("Type your translation: ")
          if (your_answer == line(1)) {
            respond = false
            println("Very good")
          }
          else {
            println("Try again")
          }
        }
      }

      val answer = readLine("Repeat exercise ? (Y/n) ")
      if (answer == "n") {
        println("Thanks")

        exit()
      }
    }
  }

  readfile(file)
}

