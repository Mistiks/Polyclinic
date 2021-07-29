package view.api;

public interface ITalonService {
    void add(Integer userId, String visitTime, Integer doctorId);
    void addTalonsForToday(Integer doctorId);
}
