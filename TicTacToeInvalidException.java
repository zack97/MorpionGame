package MorpionGame;

public class TicTacToeInvalidException extends Throwable {

    public TicTacToeInvalidException(){
        super();
    }

    public TicTacToeInvalidException(String message) {
        super(message);

    }
}
