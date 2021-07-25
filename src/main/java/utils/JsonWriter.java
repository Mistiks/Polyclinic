package utils;

import net.minidev.json.JSONObject;
import utils.api.IJsonWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class JsonWriter implements IJsonWriter {

    @Override
    public void write(HashMap <String, String> result, HttpServletResponse response) throws IOException {
        JSONObject responseData = new JSONObject(result);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(responseData);
        out.flush();
    }
}
