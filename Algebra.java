public class Algebra {

    // a + b that supports negatives
    public static int plus(int a, int b) {
        int result = a;

        if (b >= 0) {
            while (b > 0) {
                result++;
                b--;
            }
        } else {
            while (b < 0) {
                result--;
                b++;
            }
        }
        return result;
    }

    // a - b = a + (-b)
    public static int minus(int a, int b) {
        return plus(a, -b);
    }

    // a * b with correct sign handling
    public static int times(int a, int b) {
        int result = 0;
        boolean negative = false;

        if (a < 0) { a = -a; negative = !negative; }
        if (b < 0) { b = -b; negative = !negative; }

        while (b > 0) {
            result = plus(result, a);
            b--;
        }

        if (negative) result = -result;

        return result;
    }

    // a^b, b >= 0 always
    public static int pow(int a, int b) {
        int result = 1;
        while (b > 0) {
            result = times(result, a);
            b--;
        }
        return result;
    }

    // integer division a / b with signs
    public static int div(int a, int b) {
        if (b == 0) return 0; // avoid crash

        boolean negative = false;

        if (a < 0) { a = -a; negative = !negative; }
        if (b < 0) { b = -b; negative = !negative; }

        int quotient = 0;
        int sum = 0;

        while (plus(sum, b) <= a) {
            sum = plus(sum, b);
            quotient++;
        }

        if (negative) quotient = -quotient;

        return quotient;
    }

    // a % b
    public static int mod(int a, int b) {
        int divValue = div(a, b);
        int mult = times(divValue, b);
        return minus(a, mult);
    }

    // integer sqrt(a)
    public static int sqrt(int a) {
        if (a < 0) return 0; // not defined

        int x = 0;
        while (times(x, x) <= a) {
            x++;
        }
        return --x;
    }

    // tests
    public static void main(String[] args) {

        System.out.println(plus(4, 3));     // 7
        System.out.println(plus(-5, -3));   // -8
        System.out.println(plus(5, -2));    // 3

        System.out.println(minus(9, 5));    // 4
        System.out.println(minus(-5, -3));  // -2

        System.out.println(times(6, 4));    // 24
        System.out.println(times(-3, 5));   // -15
        System.out.println(times(-3, -3));  // 9

        System.out.println(pow(2, 5));      // 32

        System.out.println(div(17, 3));     // 5
        System.out.println(div(-17, 3));    // -5
        System.out.println(div(17, -3));    // -5

        System.out.println(mod(17, 3));     // 2

        System.out.println(sqrt(17));       // 4
    }
}