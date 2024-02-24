package experience.KAYAK;

public class Exercise2Part2CountUpAndDown {

    public void countUpAndDown(int start, int end) {
        // base case
        if (start > end) return;

        // recursion
        System.out.println(start);
        countUpAndDown(start + 1, end);
        if (start < end) System.out.println(start);
    }

    public static void main(String[] args) {
        // Part2: Unit test
        Exercise2Part2CountUpAndDown count = new Exercise2Part2CountUpAndDown();
        System.out.println("Part2: countUpAndDown, start is 0, end is 5");
        count.countUpAndDown(0, 5);

        System.out.println("Part2: countUpAndDown, start is 0, end is 5");
        count.countUpAndDown(2, 5);
    }


}
