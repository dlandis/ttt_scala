import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito._
import org.mockito.Matchers._

import com.tictactoe.ConsoleUI
import com.tictactoe.PlayAgainParser

class PlayAgainParserSpec extends Spec with BeforeAndAfter {
    
    var parser = new PlayAgainParser()
    
    describe("all input is valid") {
        List(" ", "foo", "Yes", "y", "Yeppers", "F023495324u", "jjjjj").foreach (
            input => {
                it(input + " is valid") {
                    expect(true) { parser.isValid(input) }
                }
            }
        )
    }
    
    describe("parsing") {
        List("yes", "YES", "Yeah", "Yno", "yrsgjl", "y").foreach (
            input => {
                it(input + " parses to true") {
                    expect(true) {parser.parsedInput(input) }
                }
            }
        )

        List("well maybe", "no", "fyes", "afsdf").foreach (
            input => {
                it(input + " parses to false") {
                    expect(false) {parser.parsedInput(input) }
                }
            }
        )
    }
}