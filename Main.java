// import Object.Start;

// public class Main {
//     public static void main(String[] args) {
//         Start.mainStart();
//     }
// }

public class Main {
    public static void main(String[] args) {
        int sum = 0;
        System.out.println("Numbers between 100 and 200, divisible by 9:");
        for (int i = 100; i <= 200; i++) {
            if (i % 9 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        System.out.println(sum);
    }
}
