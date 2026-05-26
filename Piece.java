import static java.lang.System.out;

/*
    record: simple, short way to create an immutable data class.
*/
public record Piece(String type, int player) {
    //methods
    public void captured()
    {
        System.out.println(type + " has been capured");
        if(type.equals("BK"))
        {
            System.out.println("White is victorious");
        }else if(type.equals("WK"))
        {
            System.out.println("Black is victorious");
        }
    }

    public void report(int endpoint)
    {
        switch(type)
        {
            case "BK": out.println("Black King moved to " + (endpoint+1)); break;
            case "BR": out.println("Black Rook moved to " + (endpoint+1));break;
            case "BN": out.println("Black Knight moved to " + (endpoint+1));break;
            case "WK": out.println("White King moved to " + (endpoint+1));break;
            case "WR": out.println("White Rook moved to " + (endpoint+1));break;
            case "WN": out.println("White Knight moved to " + (endpoint+1));break;
            
        }
    }

    @Override
        public String toString()
        {
            return("[" + type + "]");
        }
}
