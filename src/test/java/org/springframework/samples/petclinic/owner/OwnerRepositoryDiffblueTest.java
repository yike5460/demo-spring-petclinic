package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collection;
import java.util.List;
import org.hibernate.collection.spi.PersistentBag;
import org.hibernate.collection.spi.PersistentSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {OwnerRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"org.springframework.samples.petclinic.owner"})
@DataJpaTest
class OwnerRepositoryDiffblueTest {
  @Autowired
  private OwnerRepository ownerRepository;

  /**
   * Method under test: {@link OwnerRepository#findPetTypes()}
   */
  @Test
  void testFindPetTypes() {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");

    Owner owner2 = new Owner();
    owner2.setAddress("17 High St");
    owner2.setCity("London");
    owner2.setFirstName("John");
    owner2.setLastName("Smith");
    owner2.setTelephone("8605550118");
    ownerRepository.save(owner);
    ownerRepository.save(owner2);

    // Act
    List<PetType> actualFindPetTypesResult = ownerRepository.findPetTypes();

    // Assert
    assertEquals(6, actualFindPetTypesResult.size());
    PetType getResult = actualFindPetTypesResult.get(0);
    assertEquals("bird", getResult.getName());
    PetType getResult2 = actualFindPetTypesResult.get(1);
    assertEquals("cat", getResult2.getName());
    PetType getResult3 = actualFindPetTypesResult.get(2);
    assertEquals("dog", getResult3.getName());
    PetType getResult4 = actualFindPetTypesResult.get(3);
    assertEquals("hamster", getResult4.getName());
    PetType getResult5 = actualFindPetTypesResult.get(4);
    assertEquals("lizard", getResult5.getName());
    PetType getResult6 = actualFindPetTypesResult.get(5);
    assertEquals("snake", getResult6.getName());
    assertEquals(1, getResult2.getId().intValue());
    assertEquals(2, getResult3.getId().intValue());
    assertEquals(3, getResult5.getId().intValue());
    assertEquals(4, getResult6.getId().intValue());
    assertEquals(5, getResult.getId().intValue());
    assertEquals(6, getResult4.getId().intValue());
    assertFalse(getResult.isNew());
    assertFalse(getResult2.isNew());
    assertFalse(getResult3.isNew());
    assertFalse(getResult4.isNew());
    assertFalse(getResult5.isNew());
    assertFalse(getResult6.isNew());
  }

  /**
   * Method under test: {@link OwnerRepository#findByLastName(String, Pageable)}
   */
  @Test
  void testFindByLastName() {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");

    Owner owner2 = new Owner();
    owner2.setAddress("17 High St");
    owner2.setCity("London");
    owner2.setFirstName("John");
    owner2.setLastName("Smith");
    owner2.setTelephone("8605550118");
    ownerRepository.save(owner);
    ownerRepository.save(owner2);

    // Act
    Page<Owner> actualFindByLastNameResult = ownerRepository.findByLastName("Doe", Pageable.unpaged());

    // Assert
    assertTrue(actualFindByLastNameResult instanceof PageImpl);
    List<Owner> toListResult = actualFindByLastNameResult.toList();
    assertEquals(1, toListResult.size());
    assertSame(owner, toListResult.get(0));
  }

  /**
   * Method under test: {@link OwnerRepository#findById(Integer)}
   */
  @Test
  void testFindById() {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");
    ownerRepository.save(owner);

    Owner owner2 = new Owner();
    owner2.setAddress("17 High St");
    owner2.setCity("London");
    owner2.setFirstName("John");
    owner2.setLastName("Smith");
    owner2.setTelephone("8605550118");
    ownerRepository.save(owner2);

    Owner owner3 = new Owner();
    owner3.setAddress("42 Main St");
    owner3.setCity("Oxford");
    owner3.setFirstName("Jane");
    owner3.setLastName("Doe");
    owner3.setTelephone("6625550144");
    ownerRepository.save(owner3);

    // Act and Assert
    assertSame(owner3, ownerRepository.findById(owner3.getId()));
  }

  /**
   * Method under test: {@link OwnerRepository#save(Owner)}
   */
  @Test
  void testSave() {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");

    // Act
    ownerRepository.save(owner);

    // Assert
    List<PetType> findPetTypesResult = ownerRepository.findPetTypes();
    assertEquals(6, findPetTypesResult.size());
    PetType getResult = findPetTypesResult.get(0);
    assertEquals("bird", getResult.getName());
    PetType getResult2 = findPetTypesResult.get(1);
    assertEquals("cat", getResult2.getName());
    PetType getResult3 = findPetTypesResult.get(2);
    assertEquals("dog", getResult3.getName());
    PetType getResult4 = findPetTypesResult.get(3);
    assertEquals("hamster", getResult4.getName());
    PetType getResult5 = findPetTypesResult.get(4);
    assertEquals("lizard", getResult5.getName());
    PetType getResult6 = findPetTypesResult.get(5);
    assertEquals("snake", getResult6.getName());
    assertEquals(1, getResult2.getId().intValue());
    assertEquals(2, getResult3.getId().intValue());
    assertEquals(3, getResult5.getId().intValue());
    assertEquals(4, getResult6.getId().intValue());
    assertEquals(5, getResult.getId().intValue());
    assertEquals(6, getResult4.getId().intValue());
    assertFalse(getResult.isNew());
    assertFalse(getResult2.isNew());
    assertFalse(getResult3.isNew());
    assertFalse(getResult4.isNew());
    assertFalse(getResult5.isNew());
    assertFalse(getResult6.isNew());
  }

  /**
   * Method under test: {@link OwnerRepository#findAll(Pageable)}
   */
  @Test
  void testFindAll() {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");

    Owner owner2 = new Owner();
    owner2.setAddress("17 High St");
    owner2.setCity("London");
    owner2.setFirstName("John");
    owner2.setLastName("Smith");
    owner2.setTelephone("8605550118");
    ownerRepository.save(owner);
    ownerRepository.save(owner2);

    // Act
    Page<Owner> actualFindAllResult = ownerRepository.findAll(Pageable.unpaged());

    // Assert
    List<Owner> toListResult = actualFindAllResult.toList();
    assertEquals(12, toListResult.size());
    Owner getResult = toListResult.get(0);
    List<Pet> pets = getResult.getPets();
    assertEquals(1, pets.size());
    assertTrue(pets instanceof PersistentBag);
    Owner getResult2 = toListResult.get(1);
    List<Pet> pets2 = getResult2.getPets();
    assertEquals(1, pets2.size());
    assertTrue(pets2 instanceof PersistentBag);
    Owner getResult3 = toListResult.get(2);
    List<Pet> pets3 = getResult3.getPets();
    assertEquals(2, pets3.size());
    assertTrue(pets3 instanceof PersistentBag);
    Owner getResult4 = toListResult.get(3);
    List<Pet> pets4 = getResult4.getPets();
    assertEquals(1, pets4.size());
    assertTrue(pets4 instanceof PersistentBag);
    Owner getResult5 = toListResult.get(4);
    List<Pet> pets5 = getResult5.getPets();
    assertEquals(1, pets5.size());
    assertTrue(pets5 instanceof PersistentBag);
    Owner getResult6 = toListResult.get(5);
    List<Pet> pets6 = getResult6.getPets();
    assertEquals(2, pets6.size());
    assertTrue(pets6 instanceof PersistentBag);
    Owner getResult7 = toListResult.get(6);
    List<Pet> pets7 = getResult7.getPets();
    assertEquals(1, pets7.size());
    assertTrue(pets7 instanceof PersistentBag);
    Owner getResult8 = toListResult.get(7);
    List<Pet> pets8 = getResult8.getPets();
    assertEquals(1, pets8.size());
    assertTrue(pets8 instanceof PersistentBag);
    Owner getResult9 = toListResult.get(8);
    List<Pet> pets9 = getResult9.getPets();
    assertEquals(1, pets9.size());
    assertTrue(pets9 instanceof PersistentBag);
    Owner getResult10 = toListResult.get(9);
    List<Pet> pets10 = getResult10.getPets();
    assertEquals(2, pets10.size());
    assertTrue(pets10 instanceof PersistentBag);
    Pet getResult11 = pets.get(0);
    Collection<Visit> visits = getResult11.getVisits();
    assertTrue(visits instanceof PersistentSet);
    Pet getResult12 = pets2.get(0);
    Collection<Visit> visits2 = getResult12.getVisits();
    assertTrue(visits2 instanceof PersistentSet);
    Pet getResult13 = pets3.get(0);
    Collection<Visit> visits3 = getResult13.getVisits();
    assertTrue(visits3 instanceof PersistentSet);
    Pet getResult14 = pets3.get(1);
    Collection<Visit> visits4 = getResult14.getVisits();
    assertTrue(visits4 instanceof PersistentSet);
    Pet getResult15 = pets4.get(0);
    Collection<Visit> visits5 = getResult15.getVisits();
    assertTrue(visits5 instanceof PersistentSet);
    Pet getResult16 = pets5.get(0);
    Collection<Visit> visits6 = getResult16.getVisits();
    assertTrue(visits6 instanceof PersistentSet);
    Pet getResult17 = pets6.get(0);
    Collection<Visit> visits7 = getResult17.getVisits();
    assertEquals(2, visits7.size());
    assertTrue(visits7 instanceof PersistentSet);
    Pet getResult18 = pets6.get(1);
    Collection<Visit> visits8 = getResult18.getVisits();
    assertEquals(2, visits8.size());
    assertTrue(visits8 instanceof PersistentSet);
    Pet getResult19 = pets7.get(0);
    Collection<Visit> visits9 = getResult19.getVisits();
    assertTrue(visits9 instanceof PersistentSet);
    Pet getResult20 = pets8.get(0);
    Collection<Visit> visits10 = getResult20.getVisits();
    assertTrue(visits10 instanceof PersistentSet);
    Pet getResult21 = pets9.get(0);
    Collection<Visit> visits11 = getResult21.getVisits();
    assertTrue(visits11 instanceof PersistentSet);
    Pet getResult22 = pets10.get(0);
    Collection<Visit> visits12 = getResult22.getVisits();
    assertTrue(visits12 instanceof PersistentSet);
    Pet getResult23 = pets10.get(1);
    Collection<Visit> visits13 = getResult23.getVisits();
    assertTrue(visits13 instanceof PersistentSet);
    assertTrue(actualFindAllResult instanceof PageImpl);
    assertEquals("105 N. Lake St.", getResult6.getAddress());
    assertEquals("110 W. Liberty St.", getResult.getAddress());
    assertEquals("1450 Oak Blvd.", getResult7.getAddress());
    assertEquals("2335 Independence La.", getResult10.getAddress());
    assertEquals("2387 S. Fair Way", getResult5.getAddress());
    assertEquals("2693 Commerce St.", getResult3.getAddress());
    assertEquals("2749 Blackhawk Trail", getResult9.getAddress());
    assertEquals("345 Maple St.", getResult8.getAddress());
    assertEquals("563 Friendly St.", getResult4.getAddress());
    assertEquals("6085551023", getResult.getTelephone());
    assertEquals("6085551749", getResult2.getTelephone());
    assertEquals("6085552654", getResult6.getTelephone());
    assertEquals("6085552765", getResult5.getTelephone());
    assertEquals("6085553198", getResult4.getTelephone());
    assertEquals("6085555387", getResult7.getTelephone());
    assertEquals("6085555487", getResult10.getTelephone());
    assertEquals("6085557683", getResult8.getTelephone());
    assertEquals("6085558763", getResult3.getTelephone());
    assertEquals("6085559435", getResult9.getTelephone());
    assertEquals("638 Cardinal Ave.", getResult2.getAddress());
    assertEquals("Basil", getResult12.getName());
    assertEquals("Betty", getResult2.getFirstName());
    assertEquals("Black", getResult7.getLastName());
    assertEquals("Carlos", getResult10.getFirstName());
    assertEquals("Coleman", getResult6.getLastName());
    assertEquals("David", getResult9.getFirstName());
    assertEquals("Davis", getResult2.getLastName());
    assertEquals("Davis", getResult4.getLastName());
    assertEquals("Eduardo", getResult3.getFirstName());
    assertEquals("Escobito", getResult8.getLastName());
    assertEquals("Estaban", getResult10.getLastName());
    assertEquals("Franklin", getResult.getLastName());
    assertEquals("Freddy", getResult21.getName());
    assertEquals("George", getResult16.getName());
    assertEquals("George", getResult.getFirstName());
    assertEquals("Harold", getResult4.getFirstName());
    assertEquals("Iggy", getResult15.getName());
    assertEquals("Jean", getResult6.getFirstName());
    assertEquals("Jeff", getResult7.getFirstName());
    assertEquals("Jewel", getResult13.getName());
    assertEquals("Leo", getResult11.getName());
    assertEquals("Lucky", getResult19.getName());
    assertEquals("Lucky", getResult22.getName());
    assertEquals("Madison", getResult.getCity());
    assertEquals("Madison", getResult5.getCity());
    assertEquals("Madison", getResult8.getCity());
    assertEquals("Madison", getResult9.getCity());
    assertEquals("Maria", getResult8.getFirstName());
    assertEquals("Max", getResult17.getName());
    assertEquals("McFarland", getResult3.getCity());
    assertEquals("McTavish", getResult5.getLastName());
    assertEquals("Monona", getResult6.getCity());
    assertEquals("Monona", getResult7.getCity());
    assertEquals("Mulligan", getResult20.getName());
    assertEquals("Peter", getResult5.getFirstName());
    assertEquals("Rodriquez", getResult3.getLastName());
    assertEquals("Rosy", getResult14.getName());
    assertEquals("Samantha", getResult18.getName());
    assertEquals("Schroeder", getResult9.getLastName());
    assertEquals("Sly", getResult23.getName());
    assertEquals("Sun Prairie", getResult2.getCity());
    assertEquals("Waunakee", getResult10.getCity());
    assertEquals("Windsor", getResult4.getCity());
    PetType type = getResult19.getType();
    assertEquals("bird", type.getName());
    PetType type2 = getResult11.getType();
    assertEquals("cat", type2.getName());
    PetType type3 = getResult13.getType();
    assertEquals("dog", type3.getName());
    PetType type4 = getResult12.getType();
    assertEquals("hamster", type4.getName());
    PetType type5 = getResult15.getType();
    assertEquals("lizard", type5.getName());
    PetType type6 = getResult16.getType();
    assertEquals("snake", type6.getName());
    assertEquals(1, getResult.getId().intValue());
    assertEquals(1, getResult11.getId().intValue());
    assertEquals(1, type2.getId().intValue());
    assertEquals(10, getResult10.getId().intValue());
    assertEquals(10, getResult20.getId().intValue());
    assertEquals(11, getResult21.getId().intValue());
    assertEquals(12, getResult22.getId().intValue());
    assertEquals(13, getResult23.getId().intValue());
    assertEquals(2, getResult2.getId().intValue());
    assertEquals(2, getResult12.getId().intValue());
    assertEquals(2, type3.getId().intValue());
    assertEquals(3, getResult3.getId().intValue());
    assertEquals(3, getResult14.getId().intValue());
    assertEquals(3, type5.getId().intValue());
    assertEquals(4, getResult4.getId().intValue());
    assertEquals(4, getResult13.getId().intValue());
    assertEquals(4, type6.getId().intValue());
    assertEquals(5, getResult5.getId().intValue());
    assertEquals(5, getResult15.getId().intValue());
    assertEquals(5, type.getId().intValue());
    assertEquals(6, getResult6.getId().intValue());
    assertEquals(6, getResult16.getId().intValue());
    assertEquals(6, type4.getId().intValue());
    assertEquals(7, getResult7.getId().intValue());
    assertEquals(7, getResult18.getId().intValue());
    assertEquals(8, getResult8.getId().intValue());
    assertEquals(8, getResult17.getId().intValue());
    assertEquals(9, getResult9.getId().intValue());
    assertEquals(9, getResult19.getId().intValue());
    assertFalse(getResult.isNew());
    assertFalse(getResult2.isNew());
    assertFalse(getResult3.isNew());
    assertFalse(getResult4.isNew());
    assertFalse(getResult5.isNew());
    assertFalse(getResult6.isNew());
    assertFalse(getResult7.isNew());
    assertFalse(getResult8.isNew());
    assertFalse(getResult9.isNew());
    assertFalse(getResult10.isNew());
    assertFalse(getResult11.isNew());
    assertFalse(getResult12.isNew());
    assertFalse(getResult13.isNew());
    assertFalse(getResult14.isNew());
    assertFalse(getResult15.isNew());
    assertFalse(getResult16.isNew());
    assertFalse(getResult17.isNew());
    assertFalse(getResult18.isNew());
    assertFalse(getResult19.isNew());
    assertFalse(getResult20.isNew());
    assertFalse(getResult21.isNew());
    assertFalse(getResult22.isNew());
    assertFalse(getResult23.isNew());
    assertFalse(type2.isNew());
    assertFalse(type4.isNew());
    assertFalse(type3.isNew());
    assertFalse(type5.isNew());
    assertFalse(type6.isNew());
    assertFalse(type.isNew());
    assertTrue(visits.isEmpty());
    assertTrue(visits2.isEmpty());
    assertTrue(visits3.isEmpty());
    assertTrue(visits4.isEmpty());
    assertTrue(visits5.isEmpty());
    assertTrue(visits6.isEmpty());
    assertTrue(visits9.isEmpty());
    assertTrue(visits10.isEmpty());
    assertTrue(visits11.isEmpty());
    assertTrue(visits12.isEmpty());
    assertTrue(visits13.isEmpty());
    assertSame(owner, toListResult.get(10));
    assertSame(owner2, toListResult.get(11));
    assertSame(type3, getResult14.getType());
    assertSame(type3, getResult20.getType());
    assertSame(type3, getResult22.getType());
    assertSame(type, getResult21.getType());
  }
}
