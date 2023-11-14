package signinpage;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;

public class JsonFrame {

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> new JsonViewer().showJsonFrame());
    }

    public void showJsonFrame() throws JSONException {
        JFrame frame = new JFrame("JSON Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        try {
            // Replace this URL with the actual URL of your JSON data
            String jsonUrl = "https://api.myjson.online/v1/records/7f1cd0a2-665c-4d1e-8fd4-78284ff2a266";
            String jsonData = fetchJsonData(jsonUrl);

            // Parse JSON data into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonData);

            // Format and display the JSON data
            String formattedData = formatJsonObject("", jsonObject);
            textArea.setText(formattedData);
        } catch (IOException e) {
            e.printStackTrace();
            textArea.setText("Error fetching JSON data.");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane);

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    private String fetchJsonData(String jsonUrl) throws IOException {
        URL url = new URL(jsonUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    private String formatJsonObject(String prefix, JSONObject jsonObject) throws JSONException {
    StringBuilder formattedData = new StringBuilder();

    // Use the JSONObject's names() method to iterate over keys
    String[] keys = JSONObject.getNames(jsonObject);
    if (keys != null) {
        for (String key : keys) {
            Object value = jsonObject.get(key);
            if (value instanceof JSONObject) {
                // Recursively format nested JSON objects
                formattedData.append(formatJsonObject(prefix + key + ".", (JSONObject) value));
            } else if (value instanceof JSONArray) {
                // Handle JSON arrays
                formattedData.append(formatJsonArray(prefix + key + ".", (JSONArray) value));
            } else {
                // Append the formatted key-value pair
                formattedData.append(prefix).append(key).append(": ").append(value).append("\n");
            }
        }
    }

    return formattedData.toString();
}

private String formatJsonArray(String prefix, JSONArray jsonArray) throws JSONException {
    StringBuilder formattedData = new StringBuilder();

    for (int i = 0; i < jsonArray.length(); i++) {
        Object value = jsonArray.get(i);
        if (value instanceof JSONObject) {
            // Recursively format nested JSON objects in the array
            formattedData.append(formatJsonObject(prefix + "[" + i + "].", (JSONObject) value));
        } else if (value instanceof JSONArray) {
            // Handle nested JSON arrays
            formattedData.append(formatJsonArray(prefix + "[" + i + "].", (JSONArray) value));
        } else {
            // Append the formatted array element
            formattedData.append(prefix).append("[").append(i).append("]: ").append(value).append("\n");
        }
    }

    return formattedData.toString();
}

}
