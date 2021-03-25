package ru.ifmo.story.env;

import java.util.HashSet;
import java.util.Set;

class ChapterEnvironment implements Environment {

    private final Set<Effect> effects = new HashSet<>();

    private ChapterEnvironment(Effect... effects) {
        this.effects.addAll(Set.of(effects));
    }

    public ChapterEnvironment of(Effect... effects) {
        return new ChapterEnvironment(effects);
    }

    @Override
    public Set<Effect> getEffects() {
        return effects;
    }

    @Override
    public boolean hasEffect(Effect effect) {
        return effects.contains(effect);
    }

    @Override
    public boolean tryAddEffect(Effect effect) {
        return effects.add(effect);
    }

    @Override
    public boolean tryRemoveEffect(Effect effect) {
        return effects.remove(effect);
    }
}
