package com.github.skapral.poetryclub.core.time;

import com.pragmaticobjects.oo.atom.anno.NotAtom;

import java.time.LocalDateTime;

public class SystimeFaked implements SystemTime {
    @Override
    public final LocalDateTime time() {
        return Memory.TIME;
    }

    @NotAtom
    public static class Memory {
        public static volatile LocalDateTime TIME = LocalDateTime.now();
    }
}
