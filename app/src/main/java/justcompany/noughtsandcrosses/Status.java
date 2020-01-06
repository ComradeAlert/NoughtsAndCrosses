package justcompany.noughtsandcrosses;

public enum Status {
    CLEAR(0),
    CROSS(1),
    NOUGHT(2);

    public Integer value;

    Status(Integer value) {
        this.value = value;
    }
}