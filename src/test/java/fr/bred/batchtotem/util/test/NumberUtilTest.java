package fr.bred.batchtotem.util.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import fr.bred.batchtotem.util.NumberUtil;

public class NumberUtilTest {

    @Test
    public void shouldIsNumericWorkCorrectly() {
        Assert.assertTrue(NumberUtil.isNumeric("123.12"));
        Assert.assertTrue(NumberUtil.isNumeric("123,12"));
        Assert.assertFalse(NumberUtil.isNumeric("12,c"));
        Assert.assertFalse(NumberUtil.isNumeric("abc"));
        Assert.assertFalse(NumberUtil.isNumeric(null));
    }

    @Test
    public void shouldGetBigDecimalValueWorkCorrectly() {
        Assert.assertEquals(new BigDecimal("123"), NumberUtil.getBigDecimalValue("123.12"));
        Assert.assertEquals(new BigDecimal("123.12"), NumberUtil.getBigDecimalValue("123,12"));
        Assert.assertEquals(new BigDecimal("12"), NumberUtil.getBigDecimalValue("12,c"));
        Assert.assertEquals(BigDecimal.ZERO, NumberUtil.getBigDecimalValue("abc"));
        Assert.assertEquals(BigDecimal.ZERO, NumberUtil.getBigDecimalValue(null));
    }
}
