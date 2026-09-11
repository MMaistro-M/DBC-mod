/*
 * Decompiled with CFR 0.152.
 */
package nikedemos.markovnames.generators;

import java.util.Random;
import nikedemos.markovnames.MarkovDictionary;
import nikedemos.markovnames.generators.MarkovGenerator;

public class MarkovWelsh
extends MarkovGenerator {
    public MarkovDictionary markov2;

    public MarkovWelsh(int seqlen, Random rng) {
        this.rng = rng;
        this.markov = new MarkovDictionary("welsh_male.txt", seqlen, rng);
        this.markov2 = new MarkovDictionary("welsh_female.txt", seqlen, rng);
    }

    public MarkovWelsh(int seqlen) {
        this(seqlen, new Random());
    }

    public MarkovWelsh() {
        this(3, new Random());
    }

    @Override
    public String fetch(int gender) {
        if (gender == 0) {
            gender = this.rng.nextBoolean() ? 1 : 2;
        }
        String seq1 = gender == 2 ? this.markov2.generateWord() : this.markov.generateWord();
        return seq1;
    }
}

