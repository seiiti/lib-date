package com.seiiti.lib;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class DateLib {
  public DateLib() {}
  
  public Object parse(String str) {
    int parts = countParts( str );
    
    if( parts == 0 )
      return null;
    
    if( parts == 1 ) {
      Month month = parseMonth( str );
      
      if( month != null )
        return month;
      
      Year year = parseYear( str );
      
      if( year != null )
        return year;
      
      return null;
    }
    
    if( parts == 2 ) {
      MonthDay monthDay = parseMonthDay( str );
      
      if( monthDay != null )
        return monthDay;
      
      YearMonth yearMonth = parseYearMonth( str );
      
      if( yearMonth != null )
        return yearMonth;
      
      return null;
    }
    
    if( parts == 3 ) {
      return parseYearMonthDay( str );
    }
    
    throw new IllegalStateException();
  }
  
  /**
   * Número de campos numéricos separados por {@code /}.
   * @param str
   * @return Inteiro {@code 0 <= i <= 3}
   */
  public int countParts(String str) {
    if( str == null )
      return 0;
    
    String[] tokens = StringUtils.splitPreserveAllTokens( str.trim(), '/' );
    
    int len = tokens.length;
    
    if( len > 3 )
      return 0;
    
    for( int i = 0; i < len; i++ )
      if( !StringUtils.isNumeric( tokens[i] ) )
        return 0;
    
    return len;
  }
  
  /**
   * Números de 1 a 12.
   * @param str
   * @return
   */
  public Month parseMonth(String str) {
    String s = StringUtils.trimToNull( str );
    
    if( s == null )
      return null;
    
    int len = s.length();
    
    if( len != 1 && len != 2 )
      return null;
    
    Integer i;
    try {
      i = Integer.parseUnsignedInt( s );
    }
    catch(NumberFormatException e) {
      i = null;
    }
    
    if( i == null )
      return null;
    
    if( i < 1 || i > 12 )
      return null;
    
    return Month.of( i );
  }
  
  /**
   * Números nos intervalos:
   * <ul>
   * <li>1900 a 2900
   * <li>0 a 99
   * <ul>
   * <li> 0 a 69 => 2000 a 2069
   * <li>70 a 99 => 1970 a 1999
   * </ul>
   * </ul>
   * Obrigatório ter 1, 2 ou 4 dígitos.
   * @param str
   * @return
   */
  public Year parseYear(String str) {
    if( str == null )
      return null;
    
    String s = str.trim();
    
    int len = s.length();
    
    if( len != 1 && len != 2 && len != 4 )
      return null;
    
    Integer i;
    try {
      i = Integer.parseUnsignedInt( s );
    }
    catch(NumberFormatException e) {
      i = null;
    }
    
    if( i == null )
      return null;
    
    if( i < 100 ) {
      int cutoff = 70;
      
      if( i < cutoff )
        i += 2000;
      else
        i += 1900;
    }
    
    return Year.of( i );
  }
  
  public LocalDate parseYearMonthDay(String str) {
    if( str == null )
      return null;
    
    Pattern pattern = Pattern.compile(
      "^" +
      "([0-9][0-9]?)" +
      "/" +
      "([0-9][0-9]?)" +
      "/" +
      "([0-9]{1,4})" +
      "$"
    );
    
    Matcher matcher = pattern.matcher( str.trim() );
    
    if( !matcher.matches() )
      return null;
    
    Month month = parseMonth( matcher.group( 2 ) );
    
    if( month == null )
      return null;
    
    Year year = parseYear( matcher.group( 3 ) );
    
    if( year == null )
      return null;
    
    YearMonth yearMonth = year.atMonth( month );
    
    int day = Integer.parseUnsignedInt( matcher.group( 1 ) );
    
    if( !yearMonth.isValidDay( day ) )
      return null;
    
    return yearMonth.atDay( day );
  }
  
  public YearMonth parseYearMonth(String str) {
    if( str == null )
      return null;
    
    Pattern pattern = Pattern.compile(
      "^" +
      "([0-9]{1,2})" +
      "/" +
      "([0-9]{1,4})" +
      "$"
    );
    
    Matcher matcher = pattern.matcher( str.trim() );
    
    if( !matcher.matches() )
      return null;
    
    Month month = parseMonth( matcher.group( 1 ) );
    
    if( month == null )
      return null;
    
    Year year = parseYear( matcher.group( 2 ) );
    
    if( year == null )
      return null;
    
    return year.atMonth( month );
  }
  
  public MonthDay parseMonthDay(String str) {
    if( str == null )
      return null;
    
    Pattern pattern = Pattern.compile(
      "^" +
      "([0-9]{1,2})" +
      "/" +
      "([0-9]{1,2})" +
      "$"
    );
    
    Matcher matcher = pattern.matcher( str.trim() );
    
    if( !matcher.matches() )
      return null;
    
    Month month = parseMonth( matcher.group( 2 ) );
    
    if( month == null )
      return null;
    
    int day = Integer.parseUnsignedInt( matcher.group( 1 ) );
    
    if( day < 1 || day > month.maxLength() )
      return null;
    
    return MonthDay.of( month, day );
  }
  
}
