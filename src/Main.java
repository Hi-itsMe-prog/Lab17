import java.util.Scanner;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter x: ");
        double x = in.nextDouble();
        System.out.println("Enter y: ");
        double y = in.nextDouble();
        System.out.println("Enter command: ");
        String s = in.next();

        if (s.equals("Save"))
        {
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("obj.txt")))
            {
                Function f1 = new Function(x, y);
                oos.writeObject(f1);
                System.out.println("Object saved successfully");
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        else if (s.equals("Upload"))
        {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.txt")))
            {
                Function f1 = (Function)ois.readObject();
                System.out.println("x = " + f1.x + ", y = " + f1.y);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        else {
            System.out.println("Unknown command");
        }
    }
}

class Function implements Serializable {
    public double x;
    public double y;

    Function(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double calc() {
        this.y = this.x - Math.sin(this.x);
        return this.y;
    }
}