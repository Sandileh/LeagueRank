/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sandile
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LeagueTable leagueTable = new LeagueTable();
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                leagueTable.addGameResult(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        List<String> ranking = leagueTable.getRanking();
        for (String entry : ranking) {
            System.out.println(entry);
        }
    }
}

