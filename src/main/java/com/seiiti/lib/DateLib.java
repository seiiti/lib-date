package com.seiiti.lib;

import java.time.Month;
import java.time.Year;

import org.apache.commons.lang3.StringUtils;

public class DateLib {
  public DateLib() {}
  
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
   * <li>00 a 99 (obrigatório ter 2 dígitos)
   * <ul>
   * <li>00 a 69 => 2000 a 2069
   * <li>70 a 99 => 1970 a 1999
   * </ul>
   * </ul>
   * @param str
   * @return
   */
  public Year parseYear(String str) {
    if( str == null )
      return null;
    
    String s = str.trim();
    
    int len = s.length();
    
    if( len != 2 && len != 4 )
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
  
}
