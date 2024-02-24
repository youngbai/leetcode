package experience.KAYAK;

public class Exercise2Part1CountUp {


    public void countUp(int start, int end) {
        // base case
        if (start > end) return;

        // recursion
        System.out.println(start);
        countUp(start + 1, end);
    }

    public static void main(String[] args) {
        // Part1: Unit test
        Exercise2Part1CountUp count = new Exercise2Part1CountUp();
        System.out.println("Part1: countUp, start is 0, end is 5");
        count.countUp(0, 5);

        System.out.println("Part1: countUp, start is 2, end is 5");
        count.countUp(2, 5);
    }


}
