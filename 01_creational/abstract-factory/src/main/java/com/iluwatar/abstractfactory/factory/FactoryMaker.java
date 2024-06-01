package com.iluwatar.abstractfactory.factory;

import com.iluwatar.abstractfactory.factory.kingdom.impl.ElfKingdomFactory;
import com.iluwatar.abstractfactory.factory.kingdom.impl.OrcKingdomFactory;

public class FactoryMaker {

    /**
     * The factory method to create KingdomFactory concrete objects.
     */
    public static KingdomFactory makeFactory(final KingdomType type) {
        return switch (type) {
            case ELF -> new ElfKingdomFactory();
            case ORC -> new OrcKingdomFactory();
        };
    }
}
