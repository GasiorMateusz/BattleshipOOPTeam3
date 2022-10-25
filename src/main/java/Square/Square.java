package Square;

public class Square {

    private int X;
    private int Y;
    SquareStatus status;

    public String display () {
        return status.getCharacter();
    }

    public void updateSquareStatus(){
        if (status == SquareStatus.Empty) {
            status = SquareStatus.Missed;
        } else if(status == SquareStatus.Ship){
            status = SquareStatus.Hit;
        }
    }
    public SquareStatus getStatus(){
        return status;
    }

}
