package Square;

public enum SquareStatus {

    Empty(" "),
    Ship("O"),
    Hit("X"),
    Missed("*");

    private String status;

    SquareStatus(String status) {
        this.status = status;
    }

    public String getCharacter(){
        return status;
    }

}
