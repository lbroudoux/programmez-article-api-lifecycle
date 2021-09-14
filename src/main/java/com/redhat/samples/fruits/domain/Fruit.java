package com.redhat.samples.fruits.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fruits")
/**
 * Fruit persistent domain object.
 * @author laurent
 */
public class Fruit extends PanacheEntityBase {

   @Id
   @GeneratedValue(generator="UUID")
   @GenericGenerator(name="UUID", strategy="org.hibernate.id.UUIDGenerator")
   public String id;
   public String name;
   public Origin origin;
}
