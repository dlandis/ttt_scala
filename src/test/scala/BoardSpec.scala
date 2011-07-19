import org.scalatest.Spec

import com.tictactoe.Board

class BoardSpec extends Spec {
    describe("Status") {
        it("has a default status of 'Game In Progress'") {
            expect("Game In Progress") { new Board().status() }
        }
    }
}