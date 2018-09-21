package com.github.skapral.poetryclub.core.time;

import com.github.skapral.poetryclub.core.config.ConfigProperty;
import com.github.skapral.poetryclub.core.config.Cp_FAKE_SYSTIME;

public class SystimeAbstractedOutByProperty extends SystimeInferred {
    public SystimeAbstractedOutByProperty(ConfigProperty configProperty) {
        super(
            new Inference(configProperty)
        );
    }

    public SystimeAbstractedOutByProperty() {
        this(
            new Cp_FAKE_SYSTIME()
        );
    }

    private static class Inference implements SystemTime.Inference {
        private final ConfigProperty configProperty;

        public Inference(ConfigProperty configProperty) {
            this.configProperty = configProperty;
        }

        @Override
        public final SystemTime systemTime() {
            if(configProperty.optionalValue().isDefined()) {
                return new SystimeFaked();
            } else {
                return new SystimeReal();
            }
        }
    }
}
