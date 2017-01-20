public class HelloNumbers {
    public static void main(String[] args) {
        int sum = 0;
        int i = 0;
        while (i < 10) {
            sum += i;
            System.out.print(sum + " ");
            i++;
        }
    }
}