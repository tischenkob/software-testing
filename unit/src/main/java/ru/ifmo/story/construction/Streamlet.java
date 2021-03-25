package ru.ifmo.story.construction;

import java.util.Objects;

final class Streamlet {

    private final Liquid liquid;

    private Streamlet(Liquid liquid) {
        this.liquid = liquid;
    }

    public static Streamlet of(Liquid liquid) {
        return new Streamlet(liquid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Streamlet streamlet = (Streamlet) o;
        return liquid == streamlet.liquid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(liquid);
    }

    @Override
    public String toString() {
        return "Streamlet of " + liquid;
    }
}
