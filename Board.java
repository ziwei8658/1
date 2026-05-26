import static java.lang.System.out;
public class Board{
    private boolean gameover; //instance variable
    Piece[] board = new Piece[8]; //create new board array storing 8 Piece instances

    public Board(){
        board[0] = new Piece("BK", 1);
        board[1] = new Piece("BR", 1);
        board[2] = new Piece("BN", 1);
        board[5] = new Piece("WN", 2);
        board[6] = new Piece("WR", 2);
        board[7] = new Piece("WK", 2);
        gameover = false;
    }
    
    //method to update board
    public void printBoard(){
        System.out.println();
        System.out.print("\u200A\u2009");//escape sequence
        for (int i = 0; i < board.length; i++){
            if (board[i] == null) {
                System.out.print("[  ]");
            } else {
                System.out.print(board[i]);
            }
        }
        System.out.println();
        
        for(int j = 1; j < board.length+1; j++){
            System.out.print("  "+j+" ");
        }
        System.out.println();
        
    }
    
    //determine if the game has ended or not
    public boolean gameOver()
    {
        return gameover;
    }

    public boolean end(Piece p)
    {
        return(p.getType().contains("K"));
    }
    
    public boolean invalidRookMove(int from, int to)
    {
        //move to right
        if(from < to)
        {
            //exclude start and end, loop through the spaces in between
            for(int i = from+1; i<to; i++)
            {
                if(board[i]!=null)
                {
                    out.println("Invalid Rook Move! Rooks cannot teleport");
                    return true;
                }
            }
        }
        //move to left
        else
        {
            for(int i = from-1; i>to; i--)
            {
                if(board[i]!=null)
                {
                    out.println("Invalid Rook Move! Rooks cannot teleport");
                    return true;
                }
            }
        }
        return false;
        
    }
    //moving pieces and return a boolean to determine whether move is legal or not
    public boolean move(int from, int to, int currentPlayer)
    {
        //piece must be with in board(user input 1 to 8)
        if (from < 0 || to < 0 || from >= board.length || to >= board.length) {
            System.out.println("Out of bounds.");
            return false;
        }

        Piece piece = board[from]; //get the piece that is being moved
        
        //piece being moved must exist
        if(piece == null){
            System.out.println("No piece there");
            return false;
        }
        
        //you can only use your own pieces
        if(piece.getPlayer()!= currentPlayer){
            System.out.println("Wrong color, please move your own pieces");
            return false;
        }
        
        //cannot capture your own piece, end coordinate cannot be another one of your pieces
        if(board[to] != null && board[to].getPlayer() == currentPlayer){
            System.out.println("Can't capture your own piece.");
            return false;
        }
        
        int distance = Math.abs(to - from);//calculate squares moved and restrict piece movements
        String type = piece.getType();
        // king, only move 1 square
        if(type.contains("K") && distance != 1){                    
            out.println("Invalid King Move! Kings can only move one space");
            return false;
        }

        // rook, must move, need to fix penetrating bug
        if(type.contains("R") && invalidRookMove(from,to) && distance != 0){
            return false;
        }
        
        //knight, must move 2 squares
        if(type.contains("N") && distance != 2){
            out.println("Invalid Knight Move! Knights can only move 2 spaces");
            return false;
        }
        
        // capturing and winning
        if(board[to] != null){
            board[to].captured();
            gameover = end(board[to]);
        }

        // Move piece
        board[from].report(to);
        board[to] = piece;
        board[from] = null;
        

        //is valid move if the move passes all the filters
        return true;
    }
}
