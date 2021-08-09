package model.enums;

public enum Gender {
    UNKNOWN ("UNKNOWN"),
    MALE ("MALE"),
    FEMALE ("FEMALE");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
