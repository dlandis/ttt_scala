import org.scalatest.Spec
import org.scalatest.BeforeAndAfter
import org.mockito.Mockito._
import org.mockito.Matchers._
import org.mockito.InOrder
import java.io.PrintStream
import java.io.BufferedReader

import com.tictactoe.ConsoleUI
import com.tictactoe.Game
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
        
        it("displays an input prompt") {
            ui.inputPrompt("Enter a move - ")

            verify(mockOut).printf("Enter a move - ")
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
        
        it("displays message for computer's turn") {
            ui.computerIsMakingMove
            
            verify(mockOut).println(ui.ComputerMoveMessage)
        }
        
        it("welcomes user to the game") {
            ui.welcomeUser
            
            verify(mockOut).println(ui.WelcomeMessage)
            verify(mockOut).println(ui.InstructionsMessage)
            
        }
    }
    
    describe("input") {
        it("removes whitespace") {
            when(mockIn.readLine).thenReturn("    foo     ")
            
            expect("foo") { ui.getInput }
        }
        
        it("sends a carriage return after input") {
            when(mockIn.readLine).thenReturn("foo")
            
            ui.getInput
            
            val order: InOrder = inOrder(mockIn, mockOut)
            
            order.verify(mockIn).readLine
            order.verify(mockOut).println
        }
        
        it("prompts user until valid input received")  {
            var mockParser = mock(classOf[StringParser])
            when (mockParser.isValid(anyString()))
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true)    
            when(mockIn.readLine).thenReturn("foo") 
            val validate = (input:String) => mockParser.isValid(input) 
               
            expect("foo") { ui.getValidInput(validate, "enter foo") }  
            
            verify(mockOut, times(3)).printf("enter foo")          
            verify(mockParser, times(3)).isValid(anyString())
        }
        
        it("gets a move from user") {
            var mockParser = mock(classOf[SquareParser])
            ui.squareParser = mockParser
            when (mockParser.isValid(anyString())).thenReturn(true)
            when(mockIn.readLine).thenReturn("3")
            when(mockParser.parsedInput("3")).thenReturn(2)
            
            expect(2) { ui.getMoveFromUser }  
            
            verify(mockOut).printf(ui.SquareSelectPrompt)
        }
        
        it("asks user to play again") {
            var mockParser = mock(classOf[PlayAgainParser])
            ui.playAgainParser = mockParser
            when (mockParser.isValid(anyString())).thenReturn(true)
            when(mockIn.readLine).thenReturn("foo")
            when(mockParser.parsedInput("foo")).thenReturn(false)
            
            expect(false) { ui.askUserToPlayAgain }  
            
            verify(mockOut).printf(ui.PlayAgainPrompt)
        }
        
    }
    
    class StringParser {
        def parsedInput(input: String): String = {
            return "foo"
        }

        def isValid(input: String): Boolean = {
            return true
        }
    }
}

