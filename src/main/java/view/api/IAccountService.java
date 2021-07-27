package view.api;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface IAccountService {
    void updateGeneralInfo(HashMap<String, String> info, HttpServletRequest request);
    void updatePassword(HashMap<String, String> info, HttpServletRequest request);
}
