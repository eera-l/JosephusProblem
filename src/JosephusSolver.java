import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Federica on 20/04/2018.
 */
public class JosephusSolver {

    private ArrayList<Integer> people = new ArrayList<>();
    private CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
    private int numOfPeople;
    private int passageStep;

    public void askUser() {
        Scanner input = new Scanner(System.in);
        System.out.print("How many people in the sequence? ");
        numOfPeople = input.nextInt();
        System.out.print("How big is the passage step? ");
        passageStep = input.nextInt();
        for (int i = 0; i < numOfPeople; i++) {
            people.add(1);
        }

    }

    //Josephus problem solved without removing the elements. "Eliminated" elements are set to 0 instead of 1.
    //The advantage is that "eliminating" each element uses constant time.
    //The disadvantage is that we then need to scan the elements when we want to
    //increase our counter to check if they have been eliminated or not,
    //and in the worst case scenario (when all but two elements have been eliminated) this uses linear time.
    public int findSurvivorWithoutRemoving() {
        int survivor = 0;
        int counter = 0;

        for (int i = passageStep; counter < numOfPeople;) {

            people.set(i, 0);
            counter++;
            if (counter == numOfPeople - 1)
                break;

            int p = passageStep + 1;


            while (p > 0) {
                if (i == numOfPeople - 1) {
                    i = 0;
                } else {
                    i++;
                }
                if (people.get(i) == 1)
                    p--;
            }
        }

        for (int i = 0; i < numOfPeople; i++) {
            if (people.get(i) == 1) {
                survivor = i;
                break;
            }
        }
        return survivor;
    }

    //Josephus problem solved removing the elements in the array as they are eliminated.
    //Removing the elements uses linear time in worst case scenario (i.e. the first element is removed)
    public int findSurvivorWithRemoving() {
        people.clear();
        for (int i = 0; i < numOfPeople; i++) {
            people.add(i); //every element of the array is a number which corresponds to its index in the array
        }
        int counter = 0;

        for (int i = passageStep; counter < numOfPeople;) {

            people.remove(i); //the number is eliminated, all following elements go back one place
            counter++; //just a counter to make sure we have one survivor
            if (counter == numOfPeople - 1)
                break;

            int p;
            if (i == people.size()) //if the last eliminated element was the last one in the array. This means we have to step
                //one time more, otherwise the counting doesn't add up
                p = passageStep + 1;
            else
                p = passageStep;
            while (p > 0) { //I used a while loop because for every step we need to make sure that i loops around if it gets to the end
                if (i >= people.size() - 1)
                    i = 0;
                else
                    i++;
                p--;
            }
        }

        int survivor = people.get(0);
        return survivor;
    }

    public int findSurvivorLinkedList() {

        circularLinkedList.clear();
        for (int i = 0; i < numOfPeople; i++) {
            circularLinkedList.addLast(i); //every element of the array is a number which corresponds to its index in the array
        }

        int counter = 0;
        boolean iEqualsSize = false;
        for (int i = passageStep; counter < numOfPeople; i += passageStep) {

            //to avoid looping through the linked list
            //a pointless number of times
            while (i > circularLinkedList.size()) {
                i -= circularLinkedList.size();
            }

            //if i=size, the next iteration i will have to be decreased once
            //otherwise the counting doesn't add up
            if (iEqualsSize) {
                i--;
                iEqualsSize = false;
            }

            if (i == circularLinkedList.size()) {
                iEqualsSize = true;
            }

            circularLinkedList.removeAt(i); //the number is eliminated, all following elements go back one place

            counter++;
            if (counter == numOfPeople - 1)
                break;
        }

        int survivor = circularLinkedList.get(0);
        return survivor;
    }

}
