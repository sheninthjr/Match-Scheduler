import java.util.HashMap;
import java.util.Scanner;

public class MatchDay {
    public static void matchDay(Pair<String,String> teams, HashMap<String,Integer> score) {
        String teamA = teams.getFirst();
        String teamB = teams.getSecond();
        int min = 1;
        int max = 100;
        int value = min + (int) (Math.random() * ((max - min) + 1));
        boolean chooseTeamA = Math.random() < 0.5;
        String firstTeam = chooseTeamA ? teamA : teamB;
        String secondTeam = chooseTeamA ? teamB : teamA;
        System.out.println(firstTeam + " will start the match first.");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the run range between 1 to 100 for " + firstTeam + ":");
        int guessValueFirst = sc.nextInt();
        System.out.println("Next team will play now: " + secondTeam);
        System.out.println("Enter the run range between 1 to 100 for " + secondTeam + ":");
        int guessValueSecond = sc.nextInt();
        score.putIfAbsent(firstTeam, 0);
        score.putIfAbsent(secondTeam, 0);
        int diffFirst = Math.abs(value - guessValueFirst);
        int diffSecond = Math.abs(value - guessValueSecond);
        if(guessValueFirst == value && guessValueSecond == value) {
            score.put(firstTeam, score.get(firstTeam) + 1);
            score.put(secondTeam,score.get(secondTeam) + 1);
            return;
        }
        if (guessValueFirst == value) {
            score.put(firstTeam, score.get(firstTeam) + 2);
            System.out.println(firstTeam + " won the match");
        } else if (guessValueSecond == value) {
            score.put(secondTeam, score.get(secondTeam) + 2);
            System.out.println(secondTeam + " won the match");
        } else {
            if (diffFirst < diffSecond) {
                score.put(firstTeam, score.get(firstTeam) + 2);
                score.put(secondTeam, score.get(secondTeam));
                System.out.println(firstTeam + " won the match");
            } else if (diffSecond < diffFirst) {
                score.put(secondTeam, score.get(secondTeam) + 2);
                score.put(firstTeam, score.get(firstTeam));
                System.out.println(secondTeam + " won the match");
            } else {
                score.put(firstTeam, score.get(firstTeam) + 1);
                score.put(secondTeam,score.get(secondTeam) + 1);
                System.out.println("Both teams won 1 point");
            }
            System.out.println("Correct value is:" + value);
        }
    }
}
