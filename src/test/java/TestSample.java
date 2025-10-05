import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TestSample {

    @Test
    public void testSample() {
        DataDriven dataDriven = new DataDriven();
        try {
            ArrayList al = dataDriven.getData("Add Profile");
            System.out.println(al.get(0));
            System.out.println(al.get(1));
            System.out.println(al.get(2));
            System.out.println(al.get(3));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
