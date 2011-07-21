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
        it("parses anything beginning with 'y' to true") (pending)
    }
}