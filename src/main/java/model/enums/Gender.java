package model.enums;

public enum Gender {
    UNKNOWN ("unknown"),
    MALE ("male"),
    FEMALE ("female");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
