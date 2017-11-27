package org.prowl.aprslib.position;

/**
 * Position ambiguity enum.
 * From aprs spec:
 * In some instances — for example, where the exact position is not known —
 * the sending station may wish to reduce the number of digits of precision in
 * the latitude and longitude. In this case, the mm and hh digits in the latitude
 * may be progressively replaced by a V (space) character as the amount of
 * imprecision increases. For example:
 * 4903.5VN represents latitude to nearest 1/10th of a minute.
 * 4903.VVN represents latitude to nearest minute.
 * 490V.VVN represents latitude to nearest 10 minutes.
 * 49VV.VVN represents latitude to nearest degree
 *
 * @author ihawkins g0tai
 */
public enum Ambiguity {

   /**
    * Accurate, no ambiguity
    */
   NONE(0),

   /**
    * 4903.5VN represents latitude to nearest 1/10th of a minute.
    */
   ONE_TENTH_MINUTE(1),

   /**
    * 4903.VVN represents latitude to nearest minute.
    */
   NEAREST_MINUTE(2),

   /**
    * 490V.VVN represents latitude to nearest 10 minutes.
    */
   NEAREST_10_MINUTES(3),

   /**
    * 49VV.VVN represents latitude to nearest degree
    */
   NEAREST_DEGREE(4);

   private int level;

   private Ambiguity(int level) {
      this.level = level;
   }

   public int getLevel() {
      return level;
   }

}
