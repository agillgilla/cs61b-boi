/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import java.util.ArrayList;
public class GuitarHeroLite {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString stringA = new synthesizer.GuitarString(CONCERT_A);
        synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);

        ArrayList<synthesizer.GuitarString> strings = new ArrayList<>();
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                /*if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                }*/

                if (keyboard.contains(String.valueOf(key))) {
                    double frequency = 440 * Math.pow(2, ((keyboard.indexOf(key) - 24.0) / 12));
                    synthesizer.GuitarString newString = new synthesizer.GuitarString(frequency);
                    strings.add(newString);
                    newString.pluck();
                }
            }

        /* compute the superposition of samples */
        double sampleSum = 0;
        for (synthesizer.GuitarString string : strings) {
            sampleSum += string.sample();
        }
        /* play the sample on standard audio */
            StdAudio.play(sampleSum);

        /* advance the simulation of each guitar string by one step */
            for (synthesizer.GuitarString string : strings) {
                string.tic();
            }
        }
    }
}

