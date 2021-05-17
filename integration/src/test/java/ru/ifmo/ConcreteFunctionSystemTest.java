package ru.ifmo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ifmo.math.trigonometry.Sec;

import static org.mockito.Mockito.mock;

public class ConcreteFunctionSystemTest {

    ConcreteFunctionSystem system;

    @BeforeEach
    public void setUp() throws Exception {
        var secMock = mock(Sec.class);

        system = ConcreteFunctionSystem.builder()
                .sec(secMock)
                .cot()
                .log2()
                .log3()
                .log5()
                .log10()
                .PRECISION()
                .build();
    }

    @Test
    public void of() {

    }
}
