package ru.ifmo.story.construction;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ComputerBankTest {

    Building bank;
    Wall front;
    Wall left;
    Wall right;
    Wall back;


    @BeforeEach
    public void setUp() {

        front = new ComputerBankWall();
        left = new ComputerBankWall();
        right = new ComputerBankWall();
        back = new ComputerBankWall();
        bank = new ComputerBank(front, left, right, back);
    }

    @Test
    public void hasFrontWall_doesntHaveOrphanWall() {
        assertTrue(bank.hasWall(front));

        Wall orphanWall = new ComputerBankWall();
        assertFalse(bank.hasWall(orphanWall));
    }

    @Test
    public void hasNoWallsAfterCollapse() {
        boolean bankHasAllWalls = bank.hasWall(front) && bank.hasWall(left) && bank.hasWall(right) && bank.hasWall(back);
        assertTrue(bankHasAllWalls);

        bank.collapse();
        boolean bankHasNoWalls = !(bank.hasWall(front) || bank.hasWall(left) || bank.hasWall(right) || bank.hasWall(back));
        assertTrue(bankHasNoWalls);
    }

    @Test
    public void hasNoFrontWallAfterMelt() {
        assertTrue(bank.hasWall(front));
        bank.observe(List.of(front));
        front.melt();
        assertFalse(bank.hasWall(front));
    }
}
