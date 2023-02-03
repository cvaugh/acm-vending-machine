public enum Coin {
    PENNY("Penny", 0.01d),
    NICKEL("Nickel", 0.05d),
    DIME("Dime", 0.1d),
    QUARTER("Quarter", 0.25d);

    String prettyName;
    double value;

    Coin(String name, double value) {
        this.prettyName = name;
        this.value = value;
    }

    public String toString() {
        return String.format("%s ($%.2f)", prettyName, value);
    }
}
