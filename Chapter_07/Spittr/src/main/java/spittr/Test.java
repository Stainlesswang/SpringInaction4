package spittr;

public class Test {
    public static void main(String[] args)
            throws Exception {
        try {
            try {
                throw new Sn();
            }
            catch ( As a ) {
                System.out.println("Caught Annoyance");
                throw a;
            }
        }
        catch ( Sn s ) {
            System.out.println("Caught Sneeze");
            return ;
        }
        finally {
            System.out.println("Hello World!");
        }
    }
}
class  As extends Exception{}
class Sn extends As{}