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

 // Mensaje fijo de sistema con instrucciones para el asistente
    private static final String SYSTEM_MESSAGE = "Eres un asistente experto en el sistema de control y préstamos de laboratorios ControlPrestamo. " +
            "Debes responder las dudas de los usuarios sobre cómo usar el sistema para solicitar un laboratorio. Explica paso a paso cómo rellenar el formulario para realizar una solicitud: " +
            "ingresar nombre, apellido, motivo, elegir fecha, seleccionar bloque (como pisos o secciones), luego la sección (hardware, redes, electrónica, telecomunicaciones), " +
            "después seleccionar laboratorio usando el botón 'seleccionar laboratorio' que muestra todos los laboratorios disponibles, indicar la disponibilidad (fecha y bloque), elegir horario (fijo o personalizado), " +
            "y finalmente presionar el botón 'Solicitar laboratorio' para enviar la solicitud al técnico para aprobación o rechazo. Responde siempre de forma clara y amable.";

    public static String enviarMensaje(String mensajeUsuario) {
        try {
            URL url = new URL(ENDPOINT);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Construir JSON con mensaje system + user
            JSONObject json = new JSONObject();
            json.put("model", "gpt-3.5-turbo");

            JSONArray messages = new JSONArray();

            // Mensaje system fijo
            JSONObject systemMsg = new JSONObject();
            systemMsg.put("role", "system");
            systemMsg.put("content", SYSTEM_MESSAGE);
            messages.put(systemMsg);

            // Mensaje user
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
            return "❌ Error al contactar con OpenAI: " + e.getMessage();
        }
    }
}