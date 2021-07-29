package model.enums;

public enum TalonTime {
    ONE ("08:00"),
    TWO ("09:00"),
    ThREE ("10:00"),
    FOUR ("11:00"),
    FIVE ("12:00"),
    SIX ("13:00"),
    SEVEN ("14:00"),
    EIGHT ("15:00"),
    NINE ("16:00"),
    TEN ("17:00");

    private String time;

    TalonTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
