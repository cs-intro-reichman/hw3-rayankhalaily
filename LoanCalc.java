// Computes the periodical payment necessary to pay a given loan.
// feedback:
// your code is very clean and very readable 
// but in some places your variable names are not desciptive enough 
// when you write code you should ask yourself 
// "if i read it in a month will i undestand it?"
// apart from that really good
public class LoanCalc {

    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 

    public static void main(String[] args) {		
        double loan = Double.parseDouble(args[0]);
        double rate = Double.parseDouble(args[1]);
        int n = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

        // Brute force
        System.out.print("\nPeriodical payment, using brute force: ");
        double bf = bruteForceSolver(loan, rate, n, epsilon);
        System.out.println((int) bf);
        System.out.println("number of iterations: " + iterationCounter);

        // Bisection
        System.out.print("\nPeriodical payment, using bi-section search: ");
        double bs = bisectionSolver(loan, rate, n, epsilon);
        System.out.println((int) bs);
        System.out.println("number of iterations: " + iterationCounter);
    }

    private static double endBalance(double loan, double rate, int n, double payment) {	
        double balance = loan;
        for (int i = 0; i < n; i++) {
            balance = (balance - payment) * (1 + rate / 100);
        }
        return balance;
    }

    // FIXED VERSION
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;           // Reset global counter
        double g = loan / n;            // Initial guess

        while (endBalance(loan, rate, n, g) > 0) {
            g = g + epsilon;            // Increase payment guess
            iterationCounter++;         // Count iterations
        }

        return g;   // Return the payment, NOT the iteration counter
    }

    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
        double low = loan / n;
        double high = loan;

        iterationCounter = 0;

        while (high - low > epsilon) {
            iterationCounter++;

            double mid = (low + high) / 2;
            double balance = endBalance(loan, rate, n, mid);

            if (balance > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return (low + high) / 2;
    }
}