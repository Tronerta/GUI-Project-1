import objects.*;

public class Main {

    static void greeting(){
        System.out.println("**********************");
        System.out.println("Witamy w MansionAdmin!");
        System.out.println("**********************");
    }

    public static void main(String[] args) {
        Pomieszczenie p1 = new Pomieszczenie(34.66);

        System.out.println(p1.getClass());
    }
}
