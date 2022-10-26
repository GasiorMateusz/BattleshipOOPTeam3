package Ship;

public enum ShipType {

    Carrier(5),
    Battleship(4),
    Cruiser(3),
    Submarine(3),
    Destroyer(2);
    private static final ShipType[] types = ShipType.values();

    private final int shipLength;

    ShipType(int i) {
        shipLength = i;
    }
    public static ShipType[] getTypes(){
        return types;
    }

    public int getShipLength() {
        return shipLength;
    }
}
