package com.seiiti.lib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.Month;
import java.time.Year;

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
     * 1/1    - Inválido
     * 1/01   - janeiro de 2001
     * 01/01  - janeiro de 2001
     * 1/2018 - janeiro de 2018
     * 31/3   - 31 de março
     */
  }
  
  @Test
  public void parseMonthTest() {
    assertNull( this.sut.parseMonth( null ) );
    assertNull( this.sut.parseMonth( "" ) );
    assertNull( this.sut.parseMonth( "mar" ) );
    assertNull( this.sut.parseMonth( "13" ) );
    assertNull( this.sut.parseMonth( "003" ) );
    
    assertEquals( Month.of(  3 ), this.sut.parseMonth( "03"  ) );
    assertEquals( Month.of(  3 ), this.sut.parseMonth(  "3"  ) );
    assertEquals( Month.of(  3 ), this.sut.parseMonth( " 3 " ) );
    assertEquals( Month.of( 10 ), this.sut.parseMonth( "10"  ) );
  }
  
  @Test
  public void parseYearTest() {
    assertNull( this.sut.parseYear( null ) );
    assertNull( this.sut.parseYear( "" ) );
    assertNull( this.sut.parseYear( "ano" ) );
    assertNull( this.sut.parseYear( "3" ) );
    assertNull( this.sut.parseYear( "123" ) );
    
    assertEquals( Year.of( 2000 ), this.sut.parseYear( "00" ));
    assertEquals( Year.of( 2069 ), this.sut.parseYear( "69" ));
    assertEquals( Year.of( 1970 ), this.sut.parseYear( "70" ));
    assertEquals( Year.of( 1999 ), this.sut.parseYear( "99" ));
    
    assertEquals( Year.of( 1900 ), this.sut.parseYear( "1900" ));
    assertEquals( Year.of( 2069 ), this.sut.parseYear( "2069" ));
    assertEquals( Year.of( 1970 ), this.sut.parseYear( "1970" ));
    assertEquals( Year.of( 1999 ), this.sut.parseYear( "1999" ));
  }
}
