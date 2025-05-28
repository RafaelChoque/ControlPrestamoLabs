/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OpenAi;
import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Erlan
 */
public class OpenAIClient {
    private static final String API_KEY = "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";
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions";

    private static String systemMessage = "Eres un asistente útil.";

    public static void setSystemMessage(String mensaje) {
        systemMessage = mensaje;
    }
    public static String enviarMensaje(String mensajeUsuario) {
        try {
            URL url = new URL(ENDPOINT);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            JSONObject json = new JSONObject();
            json.put("model", "gpt-3.5-turbo");
            JSONArray messages = new JSONArray();
            JSONObject systemMsg = new JSONObject();
            systemMsg.put("role", "system");
            systemMsg.put("content", systemMessage);
            messages.put(systemMsg);
            JSONObject userMsg = new JSONObject();
            userMsg.put("role", "user");
            userMsg.put("content", mensajeUsuario);
            messages.put(userMsg);
            json.put("messages", messages);
            String jsonInputString = json.toString();
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray choices = jsonResponse.getJSONArray("choices");
            JSONObject message = choices.getJSONObject(0).getJSONObject("message");
            String content = message.getString("content");
            return content.trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al contactar con OpenAI: " + e.getMessage();
        }
    }
}