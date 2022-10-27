package Square;

public enum SquareStatus {

    Empty(" "),
    Ship("o"),
    Hit("X"),
    Missed("*");

    private final String status;

    SquareStatus(String status) {
        this.status = status;
    }

    public String getCharacter(){
        return status;
    }

}
