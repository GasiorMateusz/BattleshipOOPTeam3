package Ship;

public enum ShipType {

    Carrier(5),
    Battleship(4),
    Cruiser(3),
    Submarine(3),
    Destroyer(2);

    private final int shipLength;

    ShipType(int i) {
        shipLength = i;
    }
}
