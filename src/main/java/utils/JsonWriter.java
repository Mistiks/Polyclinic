package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import utils.api.IJsonWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
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

    @Override
    public HashMap<String, String> read(HttpServletRequest request) throws IOException {
        HashMap<String, String> receivedData;
        String data;
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();

        while( (data = br.readLine()) != null ){
            sb.append(data);
        }

        receivedData = mapper.readValue(sb.toString(), new TypeReference<>() {});
        br.close();

        return receivedData;
    }
}
