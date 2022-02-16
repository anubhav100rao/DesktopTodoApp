import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class BoardGame {
    var board by mutableStateOf(
        listOf(false, false, false, false, false, false, false, false, false)
    )

    var initialState by mutableStateOf(
        listOf(false, false, false, false, false, false, false, false, false)
    )

    fun changeState(index: Int) {
        val ls = board.toMutableList()
        ls[index] = !ls[index]
        board = ls.toList()

        val l = initialState.toMutableList()
        l[index] = true
        initialState = l.toList()
    }
    fun resetBoard() {
        board = listOf(false, false, false, false, false, false, false, false, false)
        initialState = listOf(false, false, false, false, false, false, false, false, false)
    }

    fun checkBoard(): Boolean {
        if(board[0] and board[1] and board[2])
            return true
        if(board[3] and board[4] and board[5])
            return true
        if(board[6] and board[7] and board[8])
            return true

        if(board[0] and board[3] and board[6])
            return true
        if(board[1] and board[4] and board[7])
            return true
        if(board[2] and board[5] and board[8])
            return true

        if(board[0] and board[4] and board[8])
            return true
        if(board[2] and board[4] and board[6])
            return true

        if(!board[0] and !board[1] and !board[2])
            return true
        if(!board[3] and !board[4] and !board[5])
            return true
        if(!board[6] and !board[7] and !board[8])
            return true

        if(!board[0] and !board[3] and !board[6])
            return true
        if(!board[1] and !board[4] and !board[7])
            return true
        if(!board[2] and !board[5] and !board[8])
            return true

        if(!board[0] and !board[4] and !board[8])
            return true
        if(!board[2] and !board[4] and !board[6])
            return true

        return false
    }
}

@Composable
fun BoardGameScreen(boardGame: BoardGame) {
    val scrollState = rememberScrollState()
    var result by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tic Tac Toe",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.background(Color.Red).padding(30.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(30.dp))
        for(i in 0..2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for(j in 0..2) {
                    val index = 3 * i + j
                    Text(
                        text = if(!boardGame.initialState[index]) "..." else { if (boardGame.board[index]) "1" else "0" },
                        modifier = Modifier.clickable {
                            boardGame.changeState(index)
                        }.background(Color.Cyan).padding(40.dp),
                        fontSize = 40.sp

                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = boardGame::resetBoard
            ) {
                Text("Reset board")
            }

            Button(
                onClick = {
                    result = boardGame.checkBoard()
                }
            ) {
                Text(
                    text = if(result) "Winner" else "No result"
                )
            }

        }
    }
}