import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Program for print data in JSON format.
public class ReadJson {
    public static void main(String args[]) throws ParseException{
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.
       // JFrame mainFrame;


        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", 1704310046);
        file.put("Tution Fees", 65400);


        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));
        System.out.println(file.get("Full Name"));
        pull();

    }

    public static void pull() throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("https://pokeapi.co/api/v2/pokemon/bulbasaur");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {
            String name = (String)jsonObject.get("name");
            System.out.println("name: "+name);
            //String name = (String)jsonObject.get("name");
            long weight = (long) jsonObject.get("weight");
            System.out.println("weight: "+weight);

            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("abilities");
            int n =   msg.size(); //(msg).length();
            for (int i = 0; i < n; ++i) {
                JSONObject test = (JSONObject) msg.get(i);
                //System.out.println(test);
                JSONObject abilities = (JSONObject) test.get("ability");
                String abilities1 = (String)abilities.get("name");
                System.out.println("ability: "+abilities1);
                // System.out.println(person.getInt("key"));
            }

            org.json.simple.JSONArray msg1 = (org.json.simple.JSONArray) jsonObject.get("moves");
            for (int s = 0; s < n; ++s){
                JSONObject test1 = (JSONObject) msg1.get(s);
                //System.out.println(test);
                JSONObject moves = (JSONObject) test1.get("move");
                String moves1 = (String)moves.get("name");
                System.out.println("move: "+moves1);
                // System.out.println(person.getInt("key"));
            }


           // org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("abilities");


        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }
}


