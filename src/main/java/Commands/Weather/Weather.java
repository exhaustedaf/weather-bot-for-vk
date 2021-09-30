package Commands.Weather;

import Messenger.Command;
import Server.VKManager;
import com.vk.api.sdk.objects.messages.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Weather extends Command {
    public Weather(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) throws IOException {
        new VKManager().sendMessage(getWeather(message.getBody()), message.getUserId());
    }

    private String getWeather(String message) throws IOException {
        String weather = "";
        if (message.length() > 7)
            message = message.substring(7);
        else
            return "А где название города? Я мысли читать не умею:(";
        Model model = new Model();

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" +
                    message + "&units=metric&appid=YOUR_API");
            Scanner in = new Scanner((InputStream) url.getContent());
            if (in.toString() == "{\"cod\":\"404\",\"message\":\"city not found\"}")
                return "Такой город я не знаю, попробуйте что-нибудь другое";
            while(in.hasNext()) {
                weather += in.nextLine();
            }
        } catch (Exception e) {
            return "Произошла какая-то ошибка( Попробуйте ещё раз!";
        }
        JSONObject object = new JSONObject(weather);
        model.setName(object.getString("name"));
        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        JSONObject wind = object.getJSONObject("wind");
        model.setSpeed(wind.getDouble("speed"));
        JSONObject clouds = object.getJSONObject("clouds");
        model.setClouds(clouds.getDouble("all"));
        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setMain((String)obj.get("main"));
        }
        return "Погода в городе " + model.getName() + ":\n" +
                "Температура воздуха: " + model.getTemp() + " C\n" +
                "Состояние: " + model.getMain() + "\n" +
                "Скорость ветра: " + model.getSpeed() + " м/c\n" +
                "Влажность: " + model.getHumidity() + "%\n" +
                "Облачность: " + model.getClouds() + "%";
    }
}