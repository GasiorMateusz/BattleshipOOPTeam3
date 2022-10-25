package Square;

public class Square {

    private int X;
    private int Y;
    SquareStatus status;

    public String display () {
        return status.getCharacter();
    }

    public void updateSquareStatus(){
        if(status == SquareStatus.Empty){

        }else{
            //...
        }
    }
    public SquareStatus getStatus(){
        return status;
    }

}
