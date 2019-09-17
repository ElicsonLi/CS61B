import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void test(){
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                ads1.addLast(i);

            } else {
                sad1.addFirst(i);
                ads1.addFirst(i);
            }
        }


        for (int i = 0; i < 8; i += 1) {
            int rf = 0;
            int rl = 0;

            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                Integer a,b;
                a = sad1.removeFirst();
                b = ads1.removeFirst();
                rf ++;
                assertEquals("Oh noooo!\nThis is bad:\n   When I "+rf+"th remove the First Number, you give me " + b
                        + " but the right answer is " + a + "!",a,b);
            } else {
                Integer a,b;
                a = sad1.removeLast();
                b = ads1.removeLast();
                rl ++;
                assertEquals("Oh noooo!\nThis is bad:\n   When I "+rl+"th remove the Last Number, you give me " + b
                        + " but the right answer is " + a + "!",a,b);
            }
        }

        for (int i = 0;i < ads1.size();i++)
        {
            assertEquals(ads1.get(i),sad1.get(i));
        }

    }

}
