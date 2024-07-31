package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class OwnerDiffblueTest {
  /**
   * Method under test: {@link Owner#addPet(Pet)}
   */
  @Test
  void testAddPet() {
    // Arrange
    Owner owner = new Owner();

    PetType type = new PetType();
    type.setId(1);
    type.setName("Dog");
    Pet pet = mock(Pet.class);
    when(pet.isNew()).thenReturn(true);
    doNothing().when(pet).setId(Mockito.<Integer>any());
    doNothing().when(pet).setName(Mockito.<String>any());
    doNothing().when(pet).setBirthDate(Mockito.<LocalDate>any());
    doNothing().when(pet).setType(Mockito.<PetType>any());
    pet.setBirthDate(LocalDate.of(1970, 1, 1));
    pet.setId(1);
    pet.setName("Bella");
    pet.setType(type);

    // Act
    owner.addPet(pet);

    // Assert
    verify(pet).isNew();
    verify(pet).setId(eq(1));
    verify(pet).setName(eq("Bella"));
    verify(pet).setBirthDate(isA(LocalDate.class));
    verify(pet).setType(isA(PetType.class));
    List<Pet> pets = owner.getPets();
    assertEquals(1, pets.size());
    assertSame(pet, pets.get(0));
  }

  /**
   * Method under test: {@link Owner#getPet(Integer)}
   */
  @Test
  void testGetPet() {
    // Arrange, Act and Assert
    assertNull((new Owner()).getPet(1));
    assertNull((new Owner()).getPet("Bella"));
    assertNull((new Owner()).getPet("Bella", true));
  }

  /**
   * Methods under test:
   * <ul>
   *   <li>default or parameterless constructor of {@link Owner}
   *   <li>{@link Owner#setAddress(String)}
   *   <li>{@link Owner#setCity(String)}
   *   <li>{@link Owner#setTelephone(String)}
   *   <li>{@link Owner#toString()}
   *   <li>{@link Owner#getAddress()}
   *   <li>{@link Owner#getCity()}
   *   <li>{@link Owner#getPets()}
   *   <li>{@link Owner#getTelephone()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    Owner actualOwner = new Owner();
    actualOwner.setAddress("42 Main St");
    actualOwner.setCity("Oxford");
    actualOwner.setTelephone("6625550144");
    actualOwner.toString();
    String actualAddress = actualOwner.getAddress();
    String actualCity = actualOwner.getCity();
    List<Pet> actualPets = actualOwner.getPets();

    // Assert that nothing has changed
    assertEquals("42 Main St", actualAddress);
    assertEquals("6625550144", actualOwner.getTelephone());
    assertEquals("Oxford", actualCity);
    assertTrue(actualPets.isEmpty());
  }
}
