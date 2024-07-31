package org.springframework.samples.petclinic.vet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.hibernate.collection.spi.PersistentSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {VetRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"org.springframework.samples.petclinic.vet"})
@DataJpaTest
class VetRepositoryDiffblueTest {
  @Autowired
  private VetRepository vetRepository;

  /**
   * Method under test: {@link VetRepository#findAll()}
   */
  @Test
  void testFindAll() throws DataAccessException {
    // Arrange and Act
    Collection<Vet> actualFindAllResult = vetRepository.findAll();

    // Assert
    assertTrue(actualFindAllResult instanceof List);
    assertEquals(6, actualFindAllResult.size());
    Vet getResult = ((List<Vet>) actualFindAllResult).get(0);
    Set<Specialty> specialtiesInternal = getResult.getSpecialtiesInternal();
    assertTrue(specialtiesInternal instanceof PersistentSet);
    Vet getResult2 = ((List<Vet>) actualFindAllResult).get(1);
    Set<Specialty> specialtiesInternal2 = getResult2.getSpecialtiesInternal();
    assertEquals(1, specialtiesInternal2.size());
    assertTrue(specialtiesInternal2 instanceof PersistentSet);
    Vet getResult3 = ((List<Vet>) actualFindAllResult).get(2);
    Set<Specialty> specialtiesInternal3 = getResult3.getSpecialtiesInternal();
    assertEquals(2, specialtiesInternal3.size());
    assertTrue(specialtiesInternal3 instanceof PersistentSet);
    Vet getResult4 = ((List<Vet>) actualFindAllResult).get(3);
    Set<Specialty> specialtiesInternal4 = getResult4.getSpecialtiesInternal();
    assertEquals(1, specialtiesInternal4.size());
    assertTrue(specialtiesInternal4 instanceof PersistentSet);
    Vet getResult5 = ((List<Vet>) actualFindAllResult).get(4);
    Set<Specialty> specialtiesInternal5 = getResult5.getSpecialtiesInternal();
    assertEquals(1, specialtiesInternal5.size());
    assertTrue(specialtiesInternal5 instanceof PersistentSet);
    Vet getResult6 = ((List<Vet>) actualFindAllResult).get(5);
    Set<Specialty> specialtiesInternal6 = getResult6.getSpecialtiesInternal();
    assertTrue(specialtiesInternal6 instanceof PersistentSet);
    assertEquals("Carter", getResult.getLastName());
    assertEquals("Douglas", getResult3.getLastName());
    assertEquals("Helen", getResult2.getFirstName());
    assertEquals("Henry", getResult5.getFirstName());
    assertEquals("James", getResult.getFirstName());
    assertEquals("Jenkins", getResult6.getLastName());
    assertEquals("Leary", getResult2.getLastName());
    assertEquals("Linda", getResult3.getFirstName());
    assertEquals("Ortega", getResult4.getLastName());
    assertEquals("Rafael", getResult4.getFirstName());
    assertEquals("Sharon", getResult6.getFirstName());
    assertEquals("Stevens", getResult5.getLastName());
    List<Specialty> specialties = getResult3.getSpecialties();
    assertEquals(2, specialties.size());
    Specialty getResult7 = specialties.get(0);
    assertEquals("dentistry", getResult7.getName());
    List<Specialty> specialties2 = getResult2.getSpecialties();
    assertEquals(1, specialties2.size());
    Specialty getResult8 = specialties2.get(0);
    assertEquals("radiology", getResult8.getName());
    Specialty getResult9 = specialties.get(1);
    assertEquals("surgery", getResult9.getName());
    assertEquals(0, getResult.getNrOfSpecialties());
    assertEquals(0, getResult6.getNrOfSpecialties());
    assertEquals(1, getResult8.getId().intValue());
    assertEquals(1, getResult.getId().intValue());
    List<Specialty> specialties3 = getResult4.getSpecialties();
    assertEquals(1, specialties3.size());
    assertEquals(1, getResult2.getNrOfSpecialties());
    assertEquals(1, getResult4.getNrOfSpecialties());
    assertEquals(1, getResult5.getNrOfSpecialties());
    assertEquals(2, getResult9.getId().intValue());
    assertEquals(2, getResult2.getId().intValue());
    assertEquals(2, getResult3.getNrOfSpecialties());
    assertEquals(3, getResult7.getId().intValue());
    assertEquals(3, getResult3.getId().intValue());
    assertEquals(4, getResult4.getId().intValue());
    assertEquals(5, getResult5.getId().intValue());
    assertEquals(6, getResult6.getId().intValue());
    assertFalse(getResult8.isNew());
    assertFalse(getResult7.isNew());
    assertFalse(getResult9.isNew());
    assertFalse(getResult.isNew());
    assertFalse(getResult2.isNew());
    assertFalse(getResult3.isNew());
    assertFalse(getResult4.isNew());
    assertFalse(getResult5.isNew());
    assertFalse(getResult6.isNew());
    assertTrue(getResult.getSpecialties().isEmpty());
    assertTrue(getResult6.getSpecialties().isEmpty());
    assertTrue(specialtiesInternal.isEmpty());
    assertTrue(specialtiesInternal6.isEmpty());
    assertEquals(specialties2, getResult5.getSpecialties());
    assertSame(getResult9, specialties3.get(0));
  }

  /**
   * Method under test: {@link VetRepository#findAll(Pageable)}
   */
  @Test
  void testFindAll2() throws DataAccessException {
    // Arrange and Act
    Page<Vet> actualFindAllResult = vetRepository.findAll(Pageable.unpaged());

    // Assert
    List<Vet> toListResult = actualFindAllResult.toList();
    assertEquals(6, toListResult.size());
    Vet getResult = toListResult.get(0);
    Set<Specialty> specialtiesInternal = getResult.getSpecialtiesInternal();
    assertTrue(specialtiesInternal instanceof PersistentSet);
    Vet getResult2 = toListResult.get(1);
    Set<Specialty> specialtiesInternal2 = getResult2.getSpecialtiesInternal();
    assertEquals(1, specialtiesInternal2.size());
    assertTrue(specialtiesInternal2 instanceof PersistentSet);
    Vet getResult3 = toListResult.get(2);
    Set<Specialty> specialtiesInternal3 = getResult3.getSpecialtiesInternal();
    assertEquals(2, specialtiesInternal3.size());
    assertTrue(specialtiesInternal3 instanceof PersistentSet);
    Vet getResult4 = toListResult.get(3);
    Set<Specialty> specialtiesInternal4 = getResult4.getSpecialtiesInternal();
    assertEquals(1, specialtiesInternal4.size());
    assertTrue(specialtiesInternal4 instanceof PersistentSet);
    Vet getResult5 = toListResult.get(4);
    Set<Specialty> specialtiesInternal5 = getResult5.getSpecialtiesInternal();
    assertEquals(1, specialtiesInternal5.size());
    assertTrue(specialtiesInternal5 instanceof PersistentSet);
    Vet getResult6 = toListResult.get(5);
    Set<Specialty> specialtiesInternal6 = getResult6.getSpecialtiesInternal();
    assertTrue(specialtiesInternal6 instanceof PersistentSet);
    assertTrue(actualFindAllResult instanceof PageImpl);
    assertEquals("Carter", getResult.getLastName());
    assertEquals("Douglas", getResult3.getLastName());
    assertEquals("Helen", getResult2.getFirstName());
    assertEquals("Henry", getResult5.getFirstName());
    assertEquals("James", getResult.getFirstName());
    assertEquals("Jenkins", getResult6.getLastName());
    assertEquals("Leary", getResult2.getLastName());
    assertEquals("Linda", getResult3.getFirstName());
    assertEquals("Ortega", getResult4.getLastName());
    assertEquals("Rafael", getResult4.getFirstName());
    assertEquals("Sharon", getResult6.getFirstName());
    assertEquals("Stevens", getResult5.getLastName());
    List<Specialty> specialties = getResult3.getSpecialties();
    assertEquals(2, specialties.size());
    Specialty getResult7 = specialties.get(0);
    assertEquals("dentistry", getResult7.getName());
    List<Specialty> specialties2 = getResult2.getSpecialties();
    assertEquals(1, specialties2.size());
    Specialty getResult8 = specialties2.get(0);
    assertEquals("radiology", getResult8.getName());
    Specialty getResult9 = specialties.get(1);
    assertEquals("surgery", getResult9.getName());
    assertEquals(0, getResult.getNrOfSpecialties());
    assertEquals(0, getResult6.getNrOfSpecialties());
    assertEquals(1, getResult8.getId().intValue());
    assertEquals(1, getResult.getId().intValue());
    List<Specialty> specialties3 = getResult4.getSpecialties();
    assertEquals(1, specialties3.size());
    assertEquals(1, getResult2.getNrOfSpecialties());
    assertEquals(1, getResult4.getNrOfSpecialties());
    assertEquals(1, getResult5.getNrOfSpecialties());
    assertEquals(2, getResult9.getId().intValue());
    assertEquals(2, getResult2.getId().intValue());
    assertEquals(2, getResult3.getNrOfSpecialties());
    assertEquals(3, getResult7.getId().intValue());
    assertEquals(3, getResult3.getId().intValue());
    assertEquals(4, getResult4.getId().intValue());
    assertEquals(5, getResult5.getId().intValue());
    assertEquals(6, getResult6.getId().intValue());
    assertFalse(getResult8.isNew());
    assertFalse(getResult7.isNew());
    assertFalse(getResult9.isNew());
    assertFalse(getResult.isNew());
    assertFalse(getResult2.isNew());
    assertFalse(getResult3.isNew());
    assertFalse(getResult4.isNew());
    assertFalse(getResult5.isNew());
    assertFalse(getResult6.isNew());
    assertTrue(getResult.getSpecialties().isEmpty());
    assertTrue(getResult6.getSpecialties().isEmpty());
    assertTrue(specialtiesInternal.isEmpty());
    assertTrue(specialtiesInternal6.isEmpty());
    assertEquals(specialties2, getResult5.getSpecialties());
    assertSame(getResult9, specialties3.get(0));
  }
}
