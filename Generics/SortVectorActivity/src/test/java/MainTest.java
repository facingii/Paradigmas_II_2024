import mx.uv.fiee.iinf.poo.demos.sortvectoractivity.Utils;
import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void SortTest () {
        Integer [] input = { 101, 99, 12, 19, 21, 111, 345, 25, 77, 81 };
        Integer [] result = {12, 19, 21, 25, 77, 81, 99, 101, 111, 345};

        Utils.sort (input);

        Assert.assertArrayEquals (result, input);
    }

}
