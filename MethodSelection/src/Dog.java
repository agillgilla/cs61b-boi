/**
 * Created by Arjun on 2/14/2017.
 */
public class Dog {
    public String whatAmI = "DOG";
    public Dog() {}
    public void bark(Dog d) {System.out.println("bark");}
}

class Poodle extends Dog {
    public String whatAmI = "POODLE";
    public Poodle() {}
    @Override
    public void bark(Dog d) {System.out.println("woof");}

    public void bark(Poodle p) {System.out.println("yap");}
    //note that the above method does NOT override anything

    public void dance() {System.out.println("FIESTA");}
}
class Launcher {
    public static void main(String[] args) {
        Dog d = new Poodle();
        Poodle p = new Poodle();
        Dog actualDog = new Dog();

        // compile error:
        //Poodle compileFail = new Dog();
        // compile error:
        //d.dance();

        // run time error:
        //Dog runFail = (Poodle) actualDog;
        // run time error:
        //d.bark((Poodle) actualDog);

        d.bark(d); //woof
        d.bark(p); //woof
        p.bark(d); //woof
        d.bark((Poodle) d); //woof
        d.bark(d); //woof
        p.bark((Dog) p); //woof

        p.bark((Poodle) d); //yap
        p.bark(p); //yap
        ((Poodle) d).bark((Poodle) d); //yap

        actualDog.bark(p); //bark

        System.out.println(d.whatAmI); // DOG
        System.out.println(p.whatAmI); // POODLE

    }
}
