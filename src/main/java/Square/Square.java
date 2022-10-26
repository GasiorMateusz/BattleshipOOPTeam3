package Square;

public class Square {

    private final int x;
    private final int y;
    SquareStatus status;
    public Square(int x,int y, SquareStatus status){
        this.x = x;
        this.y = y;
        this.status = status;
    }

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
