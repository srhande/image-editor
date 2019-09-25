
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.EmptyStackException;


@FixMethodOrder(MethodSorters.JVM)
public class TestMyBasic {

    static Stack<Integer> obj = null;

    @Before
    public void testPush() {
        obj = new Stack(10);
        for(int i=0;i<6;i++)
            obj.push(new Integer(i));
    }

    @Test
    public void testpop() {
        Assert.assertEquals(obj.pop(), new Integer(5));
    }


    @Test
    public void testpeek() {
        Assert.assertEquals(obj.peek(), new Integer(5));
        Assert.assertEquals(obj.pop(), new Integer(5));
    }

    @Test
    public void testMultiPush() {
        Integer[] arr = new Integer[]{11,22,33};
        obj.multiPush(arr);
        Assert.assertEquals(obj.pop(), new Integer(33));
    }

}