import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.Random;


@FixMethodOrder(MethodSorters.JVM)
public class TestImageEditor {

    static int[][] mat = new int[6][7];

    ImageEditor obj;


    @Before
    public void populate(){
        obj = new ImageEditor(mat);
        Random r = new Random(1234);
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                mat[i][j] = r.nextInt(255);
            }
        }
    }

    @Test
    public void testDelete() {
        int temp = mat[0][0];
        obj.delete(0,0);
        obj.undo();
        Assert.assertEquals(temp, mat[0][0], 1);
    }



    @Test
    public void testRedo() {
        Assert.assertFalse(obj.redo());
    }

}
