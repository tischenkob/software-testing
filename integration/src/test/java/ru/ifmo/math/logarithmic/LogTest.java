package ru.ifmo.math.logarithmic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogTest {

    @Test
    public void mockedOfTest() {
        Logarithmic innerLogMock = mock(Logarithmic.class);
        when(innerLogMock.of(4.0)).thenReturn(4.0);
        when(innerLogMock.of(2.0)).thenReturn(2.0);
        Log log = new Log(innerLogMock, 2);
        assertEquals(2.0, log.of(4.0));
    }
}
