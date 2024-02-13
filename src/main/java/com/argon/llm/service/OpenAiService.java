package com.argon.llm.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@Slf4j
public class OpenAiService {

    // API KEY 값, application-local.properties 에서 설정
    @Value("${openAi.api.key}")
    private String API_KEY;


    public void llmService() {

        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");

            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(3000);
            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.setRequestProperty("Authorization","Bearer "+API_KEY);
            urlConnection.setDoOutput(true);
            urlConnection.setInstanceFollowRedirects(true);

            OutputStream outputStream = urlConnection.getOutputStream();


            StringBuilder buffer = new StringBuilder();
            String readLine = "";
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
                while((readLine = bufferedReader.readLine()) != null)
                {
                    buffer.append(readLine).append("\n");
                }
            }
            else
            {
                buffer.append("\"code\" : \""+urlConnection.getResponseCode()+"\"");
                buffer.append(", \"message\" : \""+urlConnection.getResponseMessage()+"\"");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
