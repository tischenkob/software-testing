package ru.ifmo.story.env;

import java.util.Objects;

class NatureEffect implements Environment.Effect {
    private Intensity intensity;
    private final Type type;


    private NatureEffect(Intensity intensity, Type type) {
        this.intensity = intensity;
        this.type = type;
    }

    public static NatureEffect of(Intensity intensity, Type type) {
        return new NatureEffect(intensity, type);
    }

    @Override
    public Intensity getIntensity() {
        return intensity;
    }

    @Override
    public void setIntensity(Intensity intensity) {
        this.intensity = intensity;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NatureEffect that = (NatureEffect) o;
        return intensity == that.intensity && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(intensity, type);
    }
}
