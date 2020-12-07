import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) {
        Decorator dec = new Decorator();
        View.printTitle();
        BufferedReader bfReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            View.printReadyToWrite();
            try {
                dec.solveExpression(bfReader.readLine());
            } catch (IOException exception) {
                System.out.println(exception);
            }
        }
    }


}
