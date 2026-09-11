/*
 * Decompiled with CFR 0.152.
 */
package nikedemos.markovnames.generators;

import java.util.Random;
import nikedemos.markovnames.MarkovDictionary;
import nikedemos.markovnames.generators.MarkovGenerator;

public class MarkovAztec
extends MarkovGenerator {
    public MarkovAztec(int seqlen, Random rng) {
        this.rng = rng;
        this.markov = new MarkovDictionary("aztec_given.txt", seqlen, rng);
    }

    public MarkovAztec(int seqlen) {
        this(seqlen, new Random());
    }

    public MarkovAztec() {
        this(3, new Random());
    }

    @Override
    public String fetch(int gender) {
        return this.markov.generateWord();
    }
}

