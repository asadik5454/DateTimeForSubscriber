package com.example.datetimeforsubscriber;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class SendSms {


    static String smsUri= "http://172.28.193.139:8050/data";

    public static void  sendToProvider(String number, String text) throws JSONException {
        JSONObject jo = new JSONObject();
        try {
            System.out.println("Start sms");
            jo.put("msisdn", number);
            jo.put("source_addr","ZET-MOBILE");//"ZET-MOBILE"
            jo.put("text", text);

            String ip_address = "http://172.28.193.139:8050/data";
            byte[] data = jo.toString().getBytes( StandardCharsets.UTF_8 );
            int dataLength = jo.toString().length();

            URL obj = new URL(smsUri);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setDoOutput( true );
            con.setInstanceFollowRedirects( false );
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla5/0");
            con.setRequestProperty("Accept-Charset", "");
            con.setRequestProperty("Content-Length", Integer.toString(dataLength));
            try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
                wr.write( data );
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            System.out.println(number);
            System.out.println(number  +  "        "   + text  );
            System.out.println("response = " + response);

        } catch (Exception e) {
        }
    }

}