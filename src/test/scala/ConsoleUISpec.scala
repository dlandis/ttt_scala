import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito._
import org.mockito.Matchers._
import java.io.PrintStream
import java.io.BufferedReader

import com.tictactoe.ConsoleUI
import com.tictactoe.Game
import com.tictactoe.InputParser
import com.tictactoe.SquareParser
import com.tictactoe.PlayAgainParser

class ConsoleUISpec extends Spec with BeforeAndAfter {
    var mockOut = createMockOut
    var mockIn = createMockIn
    var mockGame = createMockGame
    
    var ui = new ConsoleUI(mockGame)

    
    def createMockOut = mock(classOf[PrintStream])
    def createMockIn = mock(classOf[BufferedReader])
    def createMockGame = mock(classOf[Game])
    
    before {
        mockGame = createMockGame
        ui = new ConsoleUI(mockGame)
        
        mockOut = createMockOut
        ui.io.setOut(mockOut)
        
        mockIn = createMockIn
        ui.in = mockIn
    }
    
    describe("output") {
        
        it("displays a message") {
            ui.displayMessage("Welcome To Tic Tac Toe in Scala")

            verify(mockOut).println("Welcome To Tic Tac Toe in Scala")
        }
        
        it("displays square info from game") {
            ui.displayBoard
            
            verify(mockGame, times(9)).getSquareValue(anyInt())
        }
        
        it("game over message") {
            when(mockGame.getStatus).thenReturn("X wins!")
            
            ui.gameOver
            
            verify(mockOut).println("X wins!")
        }
        
    }
    
    describe("input") {
        it("removes whitespace") {
            when(mockIn.readLine).thenReturn("    foo     ")
            
            expect("foo") { ui.getInput }
        }
        
        it("prompts user until valid input received") {
            var mockParser = mock(classOf[StringParser])
            ui.squareParser = mockParser
            
            when (mockParser.isValid(anyString()))
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true)    
            when(mockIn.readLine).thenReturn("foo")
                
            expect("foo") { ui.getValidInput(mockParser, "enter foo") }  
            
            verify(mockOut, times(3)).println("enter foo")          
            verify(mockParser, times(3)).isValid(anyString())
        }
        
        it("gets value from user") {
            var mockParser = mock(classOf[StringParser])
            
            when (mockParser.isValid(anyString())).thenReturn(true)
            when(mockIn.readLine).thenReturn("foo")
            when(mockParser.parsedInput("foo")).thenReturn("parsed foo")
            
            expect("parsed foo") { ui.getValueFromUser(mockParser, "enter foo") } 
            
            verify(mockOut).println("enter foo")
        }
        
        it("gets a move from user") {
            var mockParser = mock(classOf[SquareParser])
            ui.squareParser = mockParser
            
            when (mockParser.isValid(anyString())).thenReturn(true)
            when(mockIn.readLine).thenReturn("3")
            when(mockParser.parsedInput("3")).thenReturn(2)
            
            expect(2) { ui.getMoveFromUser }  
            
            verify(mockOut).println(ui.SquareSelectPrompt)
        }
        
        it("asks user to play again") {
            var mockParser = mock(classOf[PlayAgainParser])
            ui.playAgainParser = mockParser
            
            when (mockParser.isValid(anyString())).thenReturn(true)
            when(mockIn.readLine).thenReturn("foo")
            when(mockParser.parsedInput("foo")).thenReturn(false)
            
            expect(false) { ui.askUserToPlayAgain }  
            
            verify(mockOut).println(ui.PlayAgainPrompt)
        }
    }
    
    class StringParser extends InputParser {
        type ParsedValue = String
        def parsedInput(input: String): ParsedValue = {
            return "foo"
        }

        def isValid(input: String): Boolean = {
            return true
        }
    }
}

