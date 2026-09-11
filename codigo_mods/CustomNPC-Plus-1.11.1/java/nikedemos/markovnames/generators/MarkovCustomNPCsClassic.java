/*
 * Decompiled with CFR 0.152.
 */
package nikedemos.markovnames.generators;

import java.util.Random;
import nikedemos.markovnames.MarkovDictionary;
import nikedemos.markovnames.generators.MarkovGenerator;

public class MarkovCustomNPCsClassic
extends MarkovGenerator {
    public MarkovCustomNPCsClassic(int seqlen, Random rng) {
        this.rng = rng;
        this.markov = new MarkovDictionary("customnpcs_classic.txt", seqlen, rng);
    }

    public MarkovCustomNPCsClassic(int seqlen) {
        this(seqlen, new Random());
    }

    public MarkovCustomNPCsClassic() {
        this(3, new Random());
    }

    @Override
    public String fetch(int gender) {
        return this.markov.generateWord();
    }
}

