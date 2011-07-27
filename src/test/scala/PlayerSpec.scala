import org.scalatest.Spec
import org.scalatest.BeforeAndAfter

import com.tictactoe.Player

class PlayerSpec extends Spec with BeforeAndAfter {
    
    val player = new Player("X")
    
    describe("symbol") {
        it("has a symbol") {            
            expect("X") { player.symbol }
        }
    }
    
    describe("automation") {
        it("is human by default") {
            expect(true) { player.isHuman }
        }
        
        it("can be a computer player") {
            val computerPlayer = new Player("X", false)
            
            expect(false) { computerPlayer.isHuman }
        }
    }
}