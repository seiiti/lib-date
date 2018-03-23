package com.seiiti.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;

import org.junit.Before;
import org.junit.Test;

public class DateLibTest {
  public DateLibTest() {}
  
  private DateLib sut;
  
  @Before
  public void setup() {
    this.sut = new DateLib();
  }
  
  @Test
  public void test() {
    /*
     *  1
     * 12
     * 13
     * 1900
     * 2900
     * 1/1    - (d/m) 1 de janeiro    (preferido)
     *          (m/a) janeiro de 2001
     * 1/01   - (d/m) 1 de janeiro
     *          (m/a) janeiro de 2001
     * 01/01  - (d/m) 1 de janeiro
     *          (m/a) janeiro de 2001
     * 1/13   - (m/a) janeiro de 2013 (d/m: mês não pode ser maior que 12)
     * 13/1   - (d/m) 13 de janeiro (m/a: mês não pode ser maior que 12)
     * 1/2018 - (m/a) janeiro de 2018
     * 31/3   - 31 de março
     */
  }
  
  @Test
  public void parseMonthTest() {
    assertEquals( Month.of(  3 ), this.sut.parseMonth( "03"  ) );
    assertEquals( Month.of(  3 ), this.sut.parseMonth(  "3"  ) );
    assertEquals( Month.of(  3 ), this.sut.parseMonth( " 3 " ) );
    assertEquals( Month.of( 10 ), this.sut.parseMonth( "10"  ) );
    
    assertNull( this.sut.parseMonth( null  ) );
    assertNull( this.sut.parseMonth( ""    ) );
    assertNull( this.sut.parseMonth( "mar" ) );
    assertNull( this.sut.parseMonth( "13"  ) );
    assertNull( this.sut.parseMonth( "003" ) );
  }
  
  @Test
  public void parseYearTest() {
    assertEquals( Year.of( 2000 ), this.sut.parseYear(    "0" ));
    assertEquals( Year.of( 2003 ), this.sut.parseYear(    "3" ));
    
    assertEquals( Year.of( 2000 ), this.sut.parseYear(   "00" ));
    assertEquals( Year.of( 2069 ), this.sut.parseYear(   "69" ));
    assertEquals( Year.of( 1970 ), this.sut.parseYear(   "70" ));
    assertEquals( Year.of( 1999 ), this.sut.parseYear(   "99" ));
    
    assertEquals( Year.of( 1900 ), this.sut.parseYear( "1900" ));
    assertEquals( Year.of( 2069 ), this.sut.parseYear( "2069" ));
    assertEquals( Year.of( 1970 ), this.sut.parseYear( "1970" ));
    assertEquals( Year.of( 1999 ), this.sut.parseYear( "1999" ));
    
    assertNull( this.sut.parseYear( null  ) );
    assertNull( this.sut.parseYear( ""    ) );
    assertNull( this.sut.parseYear( "ano" ) );
    assertNull( this.sut.parseYear( "123" ) );
  }
  
  @Test
  public void parseYearMonthDayTest() {
    assertEquals( LocalDate.of( 1972, 3, 31 ), this.sut.parseYearMonthDay( "31/3/72"   ) );
    assertEquals( LocalDate.of( 1972, 3, 31 ), this.sut.parseYearMonthDay( "31/3/1972" ) );
    assertEquals( LocalDate.of( 2001, 3, 31 ), this.sut.parseYearMonthDay( "31/3/1"    ) );
    assertEquals( LocalDate.of( 2000, 1,  1 ), this.sut.parseYearMonthDay(  "1/1/0"    ) );
    assertEquals( LocalDate.of( 2001, 1,  1 ), this.sut.parseYearMonthDay(  "1/1/1"    ) );
    assertEquals( LocalDate.of( 2016, 2, 29 ), this.sut.parseYearMonthDay( "29/2/16"   ) );
    
    assertNull( this.sut.parseYearMonthDay( "29/2/2018" ) );
    assertNull( this.sut.parseYearMonthDay( "30/2/2018" ) );
    assertNull( this.sut.parseYearMonthDay( "31/3/972"  ) );
    assertNull( this.sut.parseYearMonthDay( "31/3/001"  ) );
  }
  
  @Test
  public void parseYearMonthTest() {
    assertEquals( YearMonth.of( 1972, 3 ), this.sut.parseYearMonth(  "3/1972" ) );
    assertEquals( YearMonth.of( 1972, 3 ), this.sut.parseYearMonth( "03/1972" ) );
    assertEquals( YearMonth.of( 1972, 3 ), this.sut.parseYearMonth(  "3/72"   ) );
    assertEquals( YearMonth.of( 1972, 3 ), this.sut.parseYearMonth( "03/72"   ) );
    assertEquals( YearMonth.of( 2002, 3 ), this.sut.parseYearMonth(  "3/2"    ) );
    assertEquals( YearMonth.of( 2002, 3 ), this.sut.parseYearMonth( "03/2"    ) );
    assertEquals( YearMonth.of( 2000, 3 ), this.sut.parseYearMonth(  "3/0"    ) );
    
    assertNull( this.sut.parseYearMonth(  "0/0"    ) );
    assertNull( this.sut.parseYearMonth(  "0/1"    ) );
    assertNull( this.sut.parseYearMonth(  "0/72"   ) );
    assertNull( this.sut.parseYearMonth(  "0/1990" ) );
    assertNull( this.sut.parseYearMonth( "13/1990" ) );
  }
  
  @Test
  public void parseMonthDayTest() {
    assertEquals( MonthDay.of( 1, 1 ), this.sut.parseMonthDay(  "1/1"  ) );
    assertEquals( MonthDay.of( 1, 1 ), this.sut.parseMonthDay(  "1/01" ) );
    assertEquals( MonthDay.of( 1, 1 ), this.sut.parseMonthDay( "01/1"  ) );
    assertEquals( MonthDay.of( 1, 1 ), this.sut.parseMonthDay( "01/01" ) );
    
    assertEquals( MonthDay.of(  2, 29 ), this.sut.parseMonthDay( "29/2"  ) );
    assertEquals( MonthDay.of( 12, 31 ), this.sut.parseMonthDay( "31/12" ) );
    
    assertNull( this.sut.parseMonthDay(  "0/2" ) );
    assertNull( this.sut.parseMonthDay(  "2/0" ) );
    assertNull( this.sut.parseMonthDay( "30/2" ) );
    assertNull( this.sut.parseMonthDay( "31/4" ) );
  }
  
  @Test
  public void parseTest() {
    assertEquals( Month.of(  1 ), this.sut.parse(  "1" ) );
    assertEquals( Month.of( 12 ), this.sut.parse( "12" ) );
    
    assertEquals( Year.of( 2000 ), this.sut.parse(    "0" ) );
    assertEquals( Year.of( 2013 ), this.sut.parse(   "13" ) );
    assertEquals( Year.of( 1972 ), this.sut.parse(   "72" ) );
    assertEquals( Year.of( 1999 ), this.sut.parse(   "99" ) );
    assertEquals( Year.of( 1972 ), this.sut.parse( "1972" ) );
    assertEquals( Year.of( 1990 ), this.sut.parse( "1990" ) );
    assertEquals( Year.of( 2000 ), this.sut.parse( "2000" ) );
    
    assertEquals( MonthDay.of(  1,  1 ), this.sut.parse(  "1/1"  ) );
    assertEquals( MonthDay.of( 12,  1 ), this.sut.parse(  "1/12" ) );
    assertEquals( MonthDay.of(  1, 12 ), this.sut.parse( "12/1"  ) );
    assertEquals( MonthDay.of(  1, 13 ), this.sut.parse( "13/1"  ) );
    assertEquals( MonthDay.of(  2, 29 ), this.sut.parse( "29/2"  ) );
    assertEquals( MonthDay.of(  3, 31 ), this.sut.parse( "31/3"  ) );
    
    assertEquals( YearMonth.of( 2000,  1 ), this.sut.parse(  "1/0"    ) );
    assertEquals( YearMonth.of( 2013,  1 ), this.sut.parse(  "1/13"   ) );
    assertEquals( YearMonth.of( 2018, 12 ), this.sut.parse( "12/18"   ) );
    assertEquals( YearMonth.of( 2018,  1 ), this.sut.parse(  "1/2018" ) );
    
    assertEquals( LocalDate.of( 2000, 1,  1 ), this.sut.parse(  "1/1/0"    ) );
    assertEquals( LocalDate.of( 2016, 2, 29 ), this.sut.parse( "29/2/2016" ) );
    assertEquals( LocalDate.of( 2016, 2, 29 ), this.sut.parse( "29/2/16"   ) );
    
    assertNull( this.sut.parse(  null ) );
    assertNull( this.sut.parse( "100" ) );
    
    assertNull( this.sut.parse(  "0/1"    ) );
    assertNull( this.sut.parse( "30/2"    ) );
    assertNull( this.sut.parse( "31/4"    ) );
    assertNull( this.sut.parse( "13/18"   ) );
    assertNull( this.sut.parse(  "1/972"  ) );
    assertNull( this.sut.parse( "13/2018" ) );
    
    assertNull( this.sut.parse( "31/3/972"  ) );
    assertNull( this.sut.parse( "29/2/2018" ) );
    assertNull( this.sut.parse( "31/4/2018" ) );
  }
}
