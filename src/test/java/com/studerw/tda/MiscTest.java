package com.studerw.tda;

import static org.assertj.core.api.Assertions.assertThat;

import com.studerw.tda.model.instrument.Query;
import com.studerw.tda.model.instrument.Query.QueryType;
import com.studerw.tda.parse.Utils;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Just to make sure Maven is building correctly and other random scratch tests
 */
public class MiscTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(MiscTest.class);

  @Test
  public void testUnicodeByteToStr(){
    byte[] bytes = "T".getBytes();
    assertThat(bytes.length == 1).isTrue();
    LOGGER.debug(Arrays.toString(bytes));
  }

  @Test
  public void BigDecimalTest(){
    BigDecimal b = new BigDecimal("-23.52");
    assertThat(b.longValue()).isLessThan(0);
    LOGGER.debug(b.toString());

    BigDecimal b2 = new BigDecimal("+23.52");
    assertThat(b2.longValue()).isGreaterThan(0);
    LOGGER.debug(b2.toString());

    BigDecimal b3 = new BigDecimal("0.234");
    LOGGER.debug(b3.toString());
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetList() {
    List<String> stuff = new ArrayList<>();
    String s = stuff.get(0);
    assertThat(s).isNull();

  }

  @Test
  public void testPrettyDate(){
    Long epoch = System.currentTimeMillis();
    String pretty = Utils.epochToStr(epoch);
    LOGGER.debug("epoch: {} -> {}", epoch, pretty);
  }

  @Test
  public void testIso8601Format(){
    ZonedDateTime now = ZonedDateTime.now();
    final String formatted = Utils.toTdaISO8601(now);
    LOGGER.debug(formatted);

    final String isoFormatted = now.format(DateTimeFormatter.ISO_INSTANT);
    LOGGER.debug(isoFormatted);

  }

  @Test
  public void testCopyVsRef(){
    String someStr = "abcde";
    String copy = doesCopy(someStr);
    LOGGER.debug("after the method: {}", someStr);
    assertThat(someStr).isEqualTo("abcde");
    assertThat(copy == someStr).isFalse();

  }

  private String doesCopy(String param) {
    param = StringUtils.upperCase(param);
    return param;
  }

  @Test
  public void getWeek(){
    long now = System.currentTimeMillis();
    LOGGER.debug("now: {}", String.valueOf(now));
    LOGGER.debug("now: {}", Utils.epochToStr(now));

    long weekAgo = now - (1_000 * 60 * 60 * 24 * 7);
    LOGGER.debug("Week ago: {}", String.valueOf(weekAgo));
    LOGGER.debug("Week ago: {}", Utils.epochToStr(weekAgo));
  }

  @Test
  public void javaEnumTest(){
    QueryType qt = QueryType.SYMBOL_REGEX;
    LOGGER.debug("{} - {}", qt.name(), qt.getQueryType());
  }
}
