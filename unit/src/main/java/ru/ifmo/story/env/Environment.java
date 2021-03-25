package ru.ifmo.story.env;

import java.util.Objects;
import java.util.Set;

interface Environment {
    Set<Effect> getEffects();

    boolean hasEffect(Effect effect);

    boolean tryAddEffect(Effect effect);

    boolean tryRemoveEffect(Effect effect);

    interface Effect {
        enum Type {
            NOISE, HEAT, COLD
        }

        enum Intensity {
            MILD, SERIOUS, UNIMAGINABLE
        }

        Intensity getIntensity();

        void setIntensity(Intensity intensity);

        Type getType();
    }
}

