package com.redhat.samples.fruits.domain;

/**
 * Possible origins enumeration.
 * @author laurent
 */
public enum Origin {
   FRANCE("France"),
   ITALY("Italy"),
   SPAIN("Spain"),
   MOROCCO("Morocco");

   private String value;

   Origin(String value) {
      this.value = value;
   }

   public String getValue() {
      return value;
   }

   @Override
   public String toString() {
      return String.valueOf(value);
   }

   public static Origin fromValue(String value) {
      for (Origin origin : Origin.values()) {
         if (origin.value.equals(value)) {
            return origin;
         }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
   }
}
