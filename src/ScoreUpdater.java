import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ScoreUpdater {

    private Game game;
    private HashMap kills;
    private HashMap deaths;
    private PrintWriter output;
    private ArrayList<String[]> finalResult;
    private String[] header = {"Name","Kills", "Deaths", "K/D"};

    public ScoreUpdater(Game game, HashMap kills, HashMap deaths)
    {
        this.game = game;
        this.kills = kills;
        this.deaths = deaths;
        init();
    }
    private void init()
    {


       finalResult = setScoreBoard(getScoreBoard());

       PrintScoreBoard(SelectionSort(finalResult));


    }

        public void PrintScoreBoard(ArrayList<String[]> result) {


            try {
                output = new PrintWriter("result.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        result.add(0, header);

        for(String[] e: result)
        {


            for(int i = 0; i < e.length; i++)
            {

                output.print(e[i].toString());
                for(int w = 0; w < 20-e[i].toString().length(); w++)
                {
                    output.print(" ");
                }
            }

            output.print("\n");
            for(int m = 0; m <65; m++)
                output.print("-");
            output.print("\n");
        }
        output.close();


    }
    public ArrayList<String[]> getScoreBoard()
    {
        ArrayList<String[]> tempResult = new ArrayList<>();
        String regex = "(\\s)+";
        String containsNumber = "[a-zA-Z ]*\\d+.*";
        try {
            BufferedReader br = new BufferedReader(new FileReader("result.txt"));
            String line;

            //Lägger till första raden



            while ((line = br.readLine()) != null)
            {
                if(line.toString().matches(containsNumber)) {
                    String[] splittedLine = line.split(regex);
                    tempResult.add(splittedLine);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempResult;

    }
    public ArrayList<String[]> setScoreBoard(ArrayList<String[]> currentScore)
    {
        Iterator itKills = kills.entrySet().iterator();
        Iterator itDeaths = deaths.entrySet().iterator();
        Boolean exist = false;


        while(itKills.hasNext())
        {
            HashMap.Entry pairK = (HashMap.Entry)itKills.next();
            HashMap.Entry pairD = (HashMap.Entry)itDeaths.next();
            exist = false;

            for(String[] e: currentScore)
            {
                if(pairK.getKey().toString().equals(e[0].toString()))
                {

                    e[1] = Integer.toString((int) pairK.getValue() + Integer.parseInt(e[1]));
                    e[2] = Integer.toString((int) pairD.getValue() + Integer.parseInt(e[2]));

                    double kd = Double.parseDouble(e[1])/Math.max(1,Double.parseDouble(e[2]));

                    e[3] = Double.toString(Math.round(kd * 1000d) / 1000d);
                    System.out.println(e[3]);
                    exist = true;
                }
                }
                if(exist == false)
                {
                    String[] tempArray = {pairK.getKey().toString(), pairK.getValue().toString(), pairD.getValue().toString(),pairK.getValue().toString()};
                    System.out.println(tempArray);
                    currentScore.add(tempArray);
                }
            }
            return currentScore;
        }

        public ArrayList SelectionSort(ArrayList<String[]> list)
        {
            int index = list.size();
            ArrayList<String[]> result = new ArrayList<>();
            for(int i = 0; i < index; i++)
            {
                String[] tempPlayer = null;
                int highestScore = 0;
                for(String[] e: list)
                {
                    if(Integer.parseInt(e[1]) >= highestScore)
                    {
                        tempPlayer = e;
                        highestScore = Integer.parseInt(e[1]);
                    }

                }
                list.remove(tempPlayer);
                result.add(tempPlayer);

            }



            return result;
        }



}
