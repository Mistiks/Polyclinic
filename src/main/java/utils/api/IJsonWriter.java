package utils.api;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public interface IJsonWriter {
    public void write (HashMap<String, String> result, HttpServletResponse response) throws IOException;
}