package fr.bred.batchtotem.util;

import java.math.BigDecimal;

public class NumberUtil {
    private NumberUtil() {
    }

    public static BigDecimal getBigDecimalValue(String value) {
        return value == null || "".equals(value.trim()) //
                ? BigDecimal.ZERO //
                : new BigDecimal(value.trim().replace(",", ""));
    }
}
