package org.example.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CbrApiWorker {
    public String getJsonCurrentExchangeRate() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://www.cbr-xml-daily.ru/daily_json.js"))
                .build();

        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String bodyAsString = (String) httpResponse.body();

        return bodyAsString;
    }

    public ExchangeRate parseCurrentExchangeRate(String json) throws Exception {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

        double usd = jsonObject.getAsJsonObject("Valute").getAsJsonObject("USD").get("Value").getAsDouble();
        double eur = jsonObject.getAsJsonObject("Valute").getAsJsonObject("EUR").get("Value").getAsDouble();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String dateAsString = jsonObject.get("Date").toString().replace("\"", "");
        Date date = dateFormat.parse(dateAsString);

        ExchangeRate exchangeRate = new ExchangeRate(date, usd, eur);

        return exchangeRate;
    }
}
