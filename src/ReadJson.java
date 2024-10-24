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
public class ReadJson implements ActionListener{

    private JFrame mainFrame;
    private  JTextArea pokemonoutput;
    private JPanel controlPanel2;
    private JPanel controlPanel3;
    private JTextArea pokemoninput;
    private int WIDTH = 800;
    private int HEIGHT = 700;

    public static void main(String args[]) throws ParseException{
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.
       // JFrame mainFrame;

        ReadJson api = new ReadJson();
        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", 1704310046);
        file.put("Tution Fees", 65400);


        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));
        System.out.println(file.get("Full Name"));


    }

    public ReadJson() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Pokideck");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(1, 2));
        pokemoninput = new JTextArea("Input name of Pokemon: ");
        pokemonoutput = new JTextArea("Pokemon info: ");
        controlPanel2 = new JPanel();
        controlPanel3 = new JPanel();
        controlPanel2.setLayout(new GridLayout(2, 1));
        controlPanel3.setLayout(new GridLayout(1, 2));

        JButton startbutton = new JButton("Start");
        startbutton.setActionCommand("Start");
        startbutton.addActionListener(new ReadJson.ButtonClickListener());

        JButton resetbutton = new JButton("Reset");
        resetbutton.setActionCommand("Reset");
        resetbutton.addActionListener(new ReadJson.ButtonClickListener());

        mainFrame.add(controlPanel2);
        mainFrame.add(pokemonoutput);
        controlPanel2.add(pokemoninput);
        controlPanel2.add(controlPanel3);
        controlPanel3.add(startbutton);
        controlPanel3.add(resetbutton);



        mainFrame.setVisible(true);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";
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


            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
                totlaJson += output;
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
        //System.out.println(jsonObject);

        long weight = 0;
        String name = null;
        String abilities1 = null;
        String moves1 = null;
        try {
            name = (String) jsonObject.get("name");
            System.out.println("name: " + name);
            pokemonoutput.append("\n");
            pokemonoutput.append("name: " + name);
            weight = (long) jsonObject.get("weight");
            System.out.println("weight: " + weight);
            pokemonoutput.append("\n");
            pokemonoutput.append("weight: " + weight);

            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("abilities");
            int n = msg.size(); //(msg).length();
            for (int i = 0; i < n; ++i) {
                JSONObject test = (JSONObject) msg.get(i);
                //System.out.println(test);
                JSONObject abilities = (JSONObject) test.get("ability");
                abilities1 = (String) abilities.get("name");
                System.out.println("ability: " + abilities1);
                pokemonoutput.append("\n");
                pokemonoutput.append("ability: " + abilities1);
                // System.out.println(person.getInt("key"));
            }

            org.json.simple.JSONArray msg1 = (org.json.simple.JSONArray) jsonObject.get("moves");
            for (int s = 0; s < n; ++s) {
                JSONObject test1 = (JSONObject) msg1.get(s);
                //System.out.println(test);
                JSONObject moves = (JSONObject) test1.get("move");
                moves1 = (String) moves.get("name");
                System.out.println("move: " + moves1);
                pokemonoutput.append("\n");
                pokemonoutput.append("move: " + moves1);
                // System.out.println(person.getInt("key"));
            }


            // org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("abilities");


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Start")) {
                try {
                    pull();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (command.equals("Reset")){

            }


        }
    }
}


