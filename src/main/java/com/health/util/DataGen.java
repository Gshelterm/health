package com.health.util;

import static java.lang.Math.log;
import static java.lang.Math.random;
import static java.lang.Math.sqrt;

public class DataGen {
    public static void main(String[] args) {
        System.out.println(generateDate());
    }

    public static String generateDate() {
        double avgPress = gaussRand(75, 5.81) / 3 + 2 * gaussRand(115, 9.68)/3;
        String pressure = formatDouble(avgPress);
        String breath = formatDouble(gaussRand(18, 2.32));
        String temperature = formatDouble(gaussRand(36.5, 0.193));
        return pressure +  "_" + breath + "_" + temperature;
    }

    private static double gaussRand(double expectation, double variance)
    {
        return (gaussRand()*variance + expectation);
    }

    static double V1, V2, S;
    static int phase = 0;
    private static double gaussRand() {

        double X;

        if (phase == 0) {
            do {
                double U1 = random();
                double U2 = random();

                V1 = 2 * U1 - 1;
                V2 = 2 * U2 - 1;
                S = V1 * V1 + V2 * V2;
            } while (S >= 1 || S == 0);

            X = V1 * sqrt(-2 * log(S) / S);
        }
        else
            X = V2 * sqrt(-2 * log(S) / S);

        phase = 1 - phase;

        return X;
    }

    private static String formatDouble(double d) {
        return String.format("%.3f", d);
    }

}

