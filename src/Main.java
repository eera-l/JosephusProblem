import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

/**
 * Created by Federica on 20/04/2018.
 */
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String ans;
        do {
            JosephusSolver js = new JosephusSolver();
            js.askUser();
            Instant start = Instant.now();
            System.out.println("--- ArrayList without remotion --- The survivor is number: " + (js.findSurvivorWithoutRemoving() + 1));
            Instant stop = Instant.now();
            System.out.println("Time elapsed: " + Duration.between(start, stop).toMillis() + " ms.");
            start = Instant.now();
            System.out.println("--- ArrayList with remotion --- The survivor is number: " + (js.findSurvivorWithRemoving() + 1));
            stop = Instant.now();
            System.out.println("Time elapsed: " + Duration.between(start, stop).toMillis() + " ms.");
            start = Instant.now();
            System.out.println("--- CircularLinkedList with remotion --- The survivor is number: " + (js.findSurvivorLinkedList() + 1));
            stop = Instant.now();
            System.out.println("Time elapsed: " + Duration.between(start, stop).toMillis() + " ms.");
            System.out.print("Enter 0 to leave or any other key to continue: ");
            ans = input.nextLine();
        } while (!ans.equals("0"));
    }
}
