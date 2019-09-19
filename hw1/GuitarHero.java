import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {

    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[]  strings = new GuitarString[keyboard.length()];
        for(int i = 0; i < keyboard.length(); i++){
            strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
        }
        /* create two guitar strings, for concert A and C */
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index > 0) {
                    strings[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for(int i = 0; i < keyboard.length(); i++){
                sample += strings[i].sample();
            }
            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(int i = 0; i < keyboard.length(); i++){
                strings[i].tic();
            }
        }
    }
}
