import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito._
import org.mockito.Matchers._

import com.tictactoe.ConsoleUI
import com.tictactoe.Game
import com.tictactoe.SquareParser

class SquareParserSpec extends Spec with BeforeAndAfter {
   
    var mockGame = createMockGame
    var parser = new SquareParser(mockGame)

    def createMockGame = mock(classOf[Game])

    describe("invalid input") {
        List("oaisdjf", "0", "foo", "99").foreach ( input => {
            it("does not allow '" + input + "'") {
                expect(false) { parser.isValid(input) }
            }
        })
        
        it("doesn't allow occupied squares") {
            when(mockGame.isSquareUnoccupied(0)).thenReturn(false)
            
            expect(false) { parser.isValid("1") }
        }
        
        it("allows unoccupied squares") {
            when(mockGame.isSquareUnoccupied(0)).thenReturn(true)
            
            expect(true) { parser.isValid("1") }
        }
    }
    
    describe("valid input") {
   
        List("1", "2", "3", "4", "5", "6", "7", "8", "9").foreach ( input => {
            it("allows '" + input + "'") {
                when(mockGame.isSquareUnoccupied(anyInt())).thenReturn(true)
                
                expect(true) { parser.isValid(input) }   
            }
        })
    }
    
    describe("parse input") {
        List("1", "2", "3", "4", "5", "6", "7", "8", "9").foreach ( input => {
            val parsedInput = (input.toInt - 1)
            it("parses '" + input + "' to " + parsedInput) {
                expect(parsedInput) { parser.parsedInput(input) }   
            }
        })
    }


}