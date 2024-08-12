import java.util.*;

public class Main {
    public static void backtracking(int index, int start, String[] arr, String[] teams, String[][] combinationOfTeams) {
        if (index == 2) {
            for (int i = 0; i < combinationOfTeams.length; i++) {
                if (combinationOfTeams[i][0] == null) {
                    combinationOfTeams[i][0] = arr[0];
                    combinationOfTeams[i][1] = arr[1];
                    break;
                }
            }
            return;
        }
        for (int i = start; i < teams.length; i++) {
            arr[index] = teams[i];
            backtracking(index + 1, i + 1, arr, teams, combinationOfTeams);
        }
    }
    public static void matchSchedule(Queue<Pair<String,String>> queue,Stack<Pair<String,String>> st) {
        while(!queue.isEmpty()) {
            Pair<String,String> element =  queue.remove();
            if(st.isEmpty()) {
                st.push(element);
            }
            else {
                Pair<String,String> pk = st.peek();
                if(pk.getFirst().equals(element.getFirst()) ||
                    pk.getSecond().equals(element.getSecond()) ||
                    pk.getFirst().equals(element.getSecond()) ||
                    pk.getSecond().equals(element.getFirst())) {
                    queue.add(element);
                }
                else {
                    st.push(element);
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of teams:");
        int teamNumber = sc.nextInt();
        if(teamNumber <= 4) {
            throw new IllegalArgumentException("Number of teams should be greater than 4");
        }
        String[] teams = new String[teamNumber];
        for (int i = 0; i < teamNumber; i++) {
            String str = sc.next();
            teams[i] = str;
        }

        String[][] combinationOfTeams = new String[(teamNumber * (teamNumber - 1)) / 2][2];
        String[] arr = new String[2];
        backtracking(0, 0, arr, teams, combinationOfTeams);

        Queue<Pair<String,String>> queue = new LinkedList<>();
        for(int i = 0; i < combinationOfTeams.length;i++) {
            queue.add(new Pair<>(combinationOfTeams[i][0],combinationOfTeams[i][1]));
        }
        Stack<Pair<String,String>> st = new Stack<>();
        matchSchedule(queue,st);
        HashMap<String,Integer> score = new HashMap<>();
        if(!st.isEmpty()) {
            MatchDay.matchDay(st.pop(),score);
        }
        System.out.println("Final Scores:");
        for (Map.Entry<String, Integer> entry : score.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
