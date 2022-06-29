package pro.sky.telegrambot.enams;

public enum ServiceEnams {
    LINK ("id"),
    DELIMITER("/");

    private String value;
    ServiceEnams(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
