package ru.ifmo.story.env;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class NatureEffectTest {

    @Test
    public void setIntensity() {
        Environment.Effect effect = NatureEffect.of(Environment.Effect.Intensity.UNIMAGINABLE, Environment.Effect.Type.HEAT);
        effect.setIntensity(Environment.Effect.Intensity.MILD);
        assertEquals(effect.getIntensity(), Environment.Effect.Intensity.MILD);
    }
}
