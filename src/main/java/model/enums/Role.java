package model.enums;

public enum Role {
    USER ("user"),
    DOCTOR ("doctor"),
    ADMIN ("admin");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
