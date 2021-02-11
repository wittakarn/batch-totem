package fr.bred.batchtotem.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import org.apache.commons.lang3.math.NumberUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NumberUtil {
    private NumberUtil() {
    }

    public static boolean isNumeric(String value) {
        return value != null && NumberUtils.isParsable(value.replace(",", ""));
    }

    public static BigDecimal getBigDecimalValue(String value) {
        BigDecimal bigDecimalValue = BigDecimal.ZERO;

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        String pattern = "#,##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        try {
            bigDecimalValue = (BigDecimal) decimalFormat.parse(value);
        } catch (ParseException e) {
            log.error("Cannot parse " + value + " to numeric");
        }

        return bigDecimalValue;
    }
}
