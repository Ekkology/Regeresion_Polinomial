import java.util.Arrays;
import java.util.function.IntToDoubleFunction;
import java.util.stream.IntStream;
import java.util.Scanner;

public class clsRegresion {
    private static void polyRegression(int[] x, int[] y) {
        int n = x.length;
        double xm = Arrays.stream(x).average().orElse(Double.NaN);
        double ym = Arrays.stream(y).average().orElse(Double.NaN);
        double x2m = Arrays.stream(x).map(a -> a * a).average().orElse(Double.NaN);
        double x3m = Arrays.stream(x).map(a -> a * a * a).average().orElse(Double.NaN);
        double x4m = Arrays.stream(x).map(a -> a * a * a * a).average().orElse(Double.NaN);
        double xym = 0.0;
        for (int i = 0; i < x.length && i < y.length; ++i) {
            xym += x[i] * y[i];
        }
        xym /= Math.min(x.length, y.length);
        double x2ym = 0.0;
        for (int i = 0; i < x.length && i < y.length; ++i) {
            x2ym += x[i] * x[i] * y[i];
        }
        x2ym /= Math.min(x.length, y.length);

        double sxx = x2m - xm * xm;
        double sxy = xym - xm * ym;
        double sxx2 = x3m - xm * x2m;
        double sx2x2 = x4m - x2m * x2m;
        double sx2y = x2ym - x2m * ym;

        double b = (sxy * sx2x2 - sx2y * sxx2) / (sxx * sx2x2 - sxx2 * sxx2);
        double c = (sx2y * sxx - sxy * sxx2) / (sxx * sx2x2 - sxx2 * sxx2);
        double a = ym - b * xm - c * x2m;

        IntToDoubleFunction abc = (int xx) -> a + b * xx + c * xx * xx;

        System.out.println("y = " + a + " + " + b + "x + " + c + "x^2");
        System.out.println(" Aproximaciones ");
        System.out.println(" x   y     y1");
        for (int i = 0; i < n; ++i) {
            System.out.printf("%2d %3d  %5.1f\n", x[i], y[i], abc.applyAsDouble(x[i]));
        }
    }

    public static void main(String[] args) {
        int n;
        Scanner objleer =  new Scanner (System.in);
        
        System.out.println("Cuantos valores tienes de X   y Y "); 
        n = objleer.nextInt();
        int[] x = new int [n];
        int[] y = new int [n];
        System.out.println("Dame  los valores de x "); 
        for (int i =  0; i < n ; i++)
        {
            x[i] = objleer.nextInt() ;
        }
        System.out.println("Dame los valores de y  ");
        for (int j =  0; j < n ; j++)
        {
            y[j] = objleer.nextInt() ;
        }


        
        polyRegression(x, y);
    }
}