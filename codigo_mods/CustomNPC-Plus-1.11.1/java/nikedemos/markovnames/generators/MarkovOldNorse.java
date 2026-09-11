/*
 * Decompiled with CFR 0.152.
 */
package nikedemos.markovnames.generators;

import java.util.Random;
import nikedemos.markovnames.MarkovDictionary;
import nikedemos.markovnames.generators.MarkovGenerator;

public class MarkovOldNorse
extends MarkovGenerator {
    public MarkovDictionary markov2;

    public MarkovOldNorse(int seqlen, Random rng) {
        this.rng = rng;
        this.markov = new MarkovDictionary("old_norse_bothgenders.txt", seqlen, rng);
    }

    public MarkovOldNorse(int seqlen) {
        this(seqlen, new Random());
    }

    public MarkovOldNorse() {
        this(4, new Random());
    }

    @Override
    public String fetch(int gender) {
        return this.markov.generateWord();
    }
}

