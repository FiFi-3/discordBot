package discord.bot.fifi.service;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Server {
    private static final String SERVER_IP = "85.114.131.194";
    private static final String APP_ID = "251570";
    Process p1;
    Runtime r1 = Runtime.getRuntime();

    public void startServer() throws IOException {
        p1 = r1.exec("cmd /C start C:\\Users\\FiFi\\Desktop\\test.bat");
    }

    public void restartServer() throws IOException, InterruptedException {

        r1.exec("taskkill /F /IM WindowsTerminal.exe");
        p1 = Runtime.getRuntime().exec("cmd /C start C:\\Users\\FiFi\\Desktop\\test.bat");


    }

    private String APIResponse(String apiUrl) {
        //String apiUrl = "https://api.steampowered.com/ISteamApps/GetServersAtAddress/v1/?addr=SERVER_IP";
        //String apiUrl = "https://api.steampowered.com/ISteamApps/GetServersAtAddress/v1/?addr=195.82.158.190:30090";

        StringBuilder response = null;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println("API Response: " + response);

            } else {
                System.out.println("API request failed. Response Code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();

    }

    public boolean getStatus() {
        JSONObject jsonObject = new JSONObject(APIResponse("https://api.steampowered.com/ISteamApps/GetServersAtAddress/v1/?addr="+SERVER_IP));
        return !jsonObject.getJSONObject("response").getJSONArray("servers").isEmpty();
    }

    public String getNews() throws IOException {


        /*FileWriter file = new FileWriter("test.json");
        file.write(APIResponse("https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid=251570"));*/
        return JsonPath.read(APIResponse("https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?appid="+APP_ID), "$.appnews.newsitems[0].url");



    }

}
