/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sandile
 */
import java.util.*;

public class LeagueTable {
    private final Map<String, Integer> teamPoints;
    public enum Rules {
        WIN(3),
        DRAW(1),
        LOSS(0);

        final int points;

        Rules(int points) {
            this.points = points;
        }
    }

    public LeagueTable() {
        this.teamPoints = new HashMap<>();
    }

    public void addGameResult(String result) {
        String[] parts = result.split(", ");
       
        // System.out.println("parts = "+Arrays.toString(parts));
 
        String[] team1Info = parts[0].trim().split(" ");
        String[] team2Info = parts[1].trim().split(" ");
  
        // Extract team names and scores, then trim spaces
        String team1 = String.join(" ", Arrays.copyOfRange(team1Info, 0, team1Info.length - 1)).trim();
        String team2 = String.join(" ", Arrays.copyOfRange(team2Info, 0, team2Info.length - 1)).trim();
  
        int score1 = Integer.parseInt(team1Info[team1Info.length - 1]);
        int score2 = Integer.parseInt(team2Info[team2Info.length - 1]);

        updatePoints(team1, score1, score2);
        updatePoints(team2, score2, score1);
    }

    private void updatePoints(String team, int score, int opponentScore) {
        int points = 0;
        if (score > opponentScore) {
            // points = 3;
            points=Rules.WIN.points;
        } else if (score == opponentScore) {
            // points = 1;
            points=Rules.DRAW.points;
        }
        
        teamPoints.put(team, teamPoints.getOrDefault(team, 0) + points);
    }

    public List<String> getRanking() {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(teamPoints.entrySet());
        entries.sort((a, b) -> {
            int pointComparison = b.getValue().compareTo(a.getValue());
            if (pointComparison == 0) {
                return a.getKey().compareTo(b.getKey());
            }
            return pointComparison;
        });
        
        List<String> rankedTeams = new ArrayList<>();
        int currentRank = 1;
        int currentPoints = -1;
        int rankCounter = 0;

        for (Map.Entry<String, Integer> entry : entries) {
            int points = entry.getValue();
            if (points != currentPoints) {
                currentRank += rankCounter;
                rankCounter = 0;
                currentPoints = points;
            }
            rankCounter++;
            rankedTeams.add(String.format("%d. %s, %d %s", currentRank, entry.getKey(), points, points == 1 ? "pt" : "pts"));
        }

        return rankedTeams;
    }
}


