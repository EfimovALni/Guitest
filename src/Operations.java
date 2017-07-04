import javax.swing.*;

/**
 * Created by Uzivatel on 7/3/2017.
 */
public class Operations extends JFrame{
    public int a = 9;
    public int b = 3;


    public Operations() {
        int o = 2;
        int l = 3;

        System.out.println(o << l);


        System.out.println("Class Operation: " + (a + b));
        for (int i = 0; i < 5; ++i) {
            System.err.println("i: " + i);
        }
    }
}
