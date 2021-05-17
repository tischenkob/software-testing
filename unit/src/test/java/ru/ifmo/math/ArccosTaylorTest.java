package ru.ifmo.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.Math.*;



public class ArccosTaylorTest {

    private ArccosTaylor arccos;
    private final double PRECISION = 0.001;

    private final Map<Double, Double> expectedMap = new HashMap<>();

    {
        expectedMap.put(0d, 1.5707963267948966192313216916397514420985846996875529104874722961d);
        expectedMap.put(0.177013d, 1.3928456349522194916248599765297875405288411247838939133519320284d);
        expectedMap.put(0.314d, 1.2513931277771908929723378968780129480817656623703777457006283964d);
        expectedMap.put(0.42d, 1.1373510067250106428228819037772353936510025970796365356654958162d);
        expectedMap.put(0.5d, 1.0471975511965977461542144610931676280657231331250352736583148641);
        expectedMap.put(0.69d, 0.8093072740472633909256398985468721643482832181619775852618102470d);
        expectedMap.put(0.99d, 0.1415394733244273d);
        expectedMap.put(1.d, 0d);
        expectedMap.put(-0.177013d, 1.7487470186375737468377834067497153436683282745912119076230125638d);
        expectedMap.put(-0.314d, 1.8901995258126023454903054864014899361154037370047280752743161958d);
        expectedMap.put(-0.42d, 2.0042416468647825956397614795022674905461668022954692853094487760d);
        expectedMap.put(-0.5d, 2.0943951023931954923084289221863352561314462662500705473166297282d);
        expectedMap.put(-0.69d, 2.3322853795425298475370034847326307198488861812131282357131343452d);
        expectedMap.put(-0.99d, 3.000053180265366d);
        expectedMap.put(-1.d, 3.1415926535897932384626433832795028841971693993751058209749445923d);
    }


    @BeforeEach
    public void setUp() {
        arccos = ArccosTaylor.with(PRECISION);
    }

    @Test
    public void testValues() throws Exception{
        for (var entry : expectedMap.entrySet()) {
            var key = entry.getKey();
            var expectedValue = entry.getValue();
            var actualValue = arccos.at(key);
            assertTrue(abs(expectedValue - actualValue) < PRECISION);
        }
    }

    @Test
    public void testThrows() {
        assertThrows(IllegalArgumentException.class, () -> arccos.at(-2));
        assertThrows(IllegalArgumentException.class, () -> arccos.at(2));
        assertThrows(IllegalArgumentException.class, () -> arccos.at(-1.00001));
        assertThrows(IllegalArgumentException.class, () -> arccos.at(1.00001));
    }
}
