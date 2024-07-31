package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

class PetValidatorDiffblueTest {
  /**
   * Method under test: {@link PetValidator#validate(Object, Errors)}
   */
  @Test
  void testValidate() {
    // Arrange
    PetValidator petValidator = new PetValidator();

    PetType type = new PetType();
    type.setId(1);
    type.setName("Dog");

    Pet pet = new Pet();
    pet.setBirthDate(LocalDate.of(1970, 1, 1));
    pet.setId(1);
    pet.setName("Bella");
    pet.setType(type);

    // Act
    assertDoesNotThrow(
        () -> petValidator.validate(pet, new BindException(pet, "org.springframework.samples.petclinic.owner.Pet")));
  }

  /**
   * Method under test: {@link PetValidator#validate(Object, Errors)}
   */
  @Test
  void testValidate2() {
    // Arrange
    PetValidator petValidator = new PetValidator();

    PetType type = new PetType();
    type.setId(1);
    type.setName("Dog");

    Pet pet = new Pet();
    pet.setBirthDate(null);
    pet.setId(1);
    pet.setName("Bella");
    pet.setType(type);
    BindException errors = new BindException(pet, "org.springframework.samples.petclinic.owner.Pet");

    // Act
    petValidator.validate(pet, errors);

    // Assert
    BindingResult bindingResult = errors.getBindingResult();
    assertTrue(bindingResult instanceof BeanPropertyBindingResult);
    FieldError fieldError = errors.getFieldError();
    assertEquals("birthDate", fieldError.getField());
    assertEquals("org.springframework.samples.petclinic.owner.Pet", fieldError.getObjectName());
    assertEquals(
        "org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
            + "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'birthDate': rejected"
            + " value [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.birthDate,required"
            + ".birthDate,required.java.time.LocalDate,required]; arguments []; default message [required]",
        errors.getLocalizedMessage());
    assertEquals(
        "org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
            + "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'birthDate': rejected"
            + " value [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.birthDate,required"
            + ".birthDate,required.java.time.LocalDate,required]; arguments []; default message [required]",
        errors.getMessage());
    assertEquals("required", fieldError.getCode());
    assertEquals("required", fieldError.getDefaultMessage());
    assertNull(fieldError.getArguments());
    assertNull(fieldError.getRejectedValue());
    List<ObjectError> allErrors = errors.getAllErrors();
    assertEquals(1, allErrors.size());
    List<FieldError> fieldErrors = errors.getFieldErrors();
    assertEquals(1, fieldErrors.size());
    List<ObjectError> allErrors2 = bindingResult.getAllErrors();
    assertEquals(1, allErrors2.size());
    List<FieldError> fieldErrors2 = bindingResult.getFieldErrors();
    assertEquals(1, fieldErrors2.size());
    assertEquals(1, errors.getErrorCount());
    assertEquals(1, errors.getFieldErrorCount());
    assertEquals(1, bindingResult.getErrorCount());
    assertEquals(1, bindingResult.getFieldErrorCount());
    assertFalse(fieldError.isBindingFailure());
    assertTrue(errors.hasErrors());
    assertTrue(errors.hasFieldErrors());
    assertTrue(bindingResult.hasErrors());
    assertTrue(bindingResult.hasFieldErrors());
    assertSame(fieldError, fieldErrors.get(0));
    assertSame(fieldError, fieldErrors2.get(0));
    assertSame(fieldError, allErrors.get(0));
    assertSame(fieldError, allErrors2.get(0));
    assertSame(fieldError, bindingResult.getFieldError());
    assertArrayEquals(new String[]{"required.org.springframework.samples.petclinic.owner.Pet.birthDate",
        "required.birthDate", "required.java.time.LocalDate", "required"}, fieldError.getCodes());
  }

  /**
   * Method under test: {@link PetValidator#validate(Object, Errors)}
   */
  @Test
  void testValidate3() {
    // Arrange
    PetValidator petValidator = new PetValidator();

    PetType type = new PetType();
    type.setId(1);
    type.setName("Dog");

    Pet pet = new Pet();
    pet.setBirthDate(LocalDate.of(1970, 1, 1));
    pet.setId(null);
    pet.setName("Bella");
    pet.setType(type);

    // Act
    assertDoesNotThrow(
        () -> petValidator.validate(pet, new BindException(pet, "org.springframework.samples.petclinic.owner.Pet")));
  }

  /**
   * Method under test: {@link PetValidator#validate(Object, Errors)}
   */
  @Test
  void testValidate4() {
    // Arrange
    PetValidator petValidator = new PetValidator();

    PetType type = new PetType();
    type.setId(1);
    type.setName("Dog");

    Pet pet = new Pet();
    pet.setBirthDate(LocalDate.of(1970, 1, 1));
    pet.setId(1);
    pet.setName(null);
    pet.setType(type);
    BindException errors = new BindException(pet, "org.springframework.samples.petclinic.owner.Pet");

    // Act
    petValidator.validate(pet, errors);

    // Assert
    BindingResult bindingResult = errors.getBindingResult();
    assertTrue(bindingResult instanceof BeanPropertyBindingResult);
    FieldError fieldError = errors.getFieldError();
    assertEquals("name", fieldError.getField());
    assertEquals("org.springframework.samples.petclinic.owner.Pet", fieldError.getObjectName());
    assertEquals("org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
        + "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'name': rejected value"
        + " [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.name,required.name,required"
        + ".java.lang.String,required]; arguments []; default message [required]", errors.getLocalizedMessage());
    assertEquals("org.springframework.validation.BeanPropertyBindingResult: 1 errors\n"
        + "Field error in object 'org.springframework.samples.petclinic.owner.Pet' on field 'name': rejected value"
        + " [null]; codes [required.org.springframework.samples.petclinic.owner.Pet.name,required.name,required"
        + ".java.lang.String,required]; arguments []; default message [required]", errors.getMessage());
    assertEquals("required", fieldError.getCode());
    assertEquals("required", fieldError.getDefaultMessage());
    assertNull(fieldError.getArguments());
    assertNull(fieldError.getRejectedValue());
    List<ObjectError> allErrors = errors.getAllErrors();
    assertEquals(1, allErrors.size());
    List<FieldError> fieldErrors = errors.getFieldErrors();
    assertEquals(1, fieldErrors.size());
    List<ObjectError> allErrors2 = bindingResult.getAllErrors();
    assertEquals(1, allErrors2.size());
    List<FieldError> fieldErrors2 = bindingResult.getFieldErrors();
    assertEquals(1, fieldErrors2.size());
    assertEquals(1, errors.getErrorCount());
    assertEquals(1, errors.getFieldErrorCount());
    assertEquals(1, bindingResult.getErrorCount());
    assertEquals(1, bindingResult.getFieldErrorCount());
    assertFalse(fieldError.isBindingFailure());
    assertTrue(errors.hasErrors());
    assertTrue(errors.hasFieldErrors());
    assertTrue(bindingResult.hasErrors());
    assertTrue(bindingResult.hasFieldErrors());
    assertSame(fieldError, fieldErrors.get(0));
    assertSame(fieldError, fieldErrors2.get(0));
    assertSame(fieldError, allErrors.get(0));
    assertSame(fieldError, allErrors2.get(0));
    assertSame(fieldError, bindingResult.getFieldError());
    assertArrayEquals(new String[]{"required.org.springframework.samples.petclinic.owner.Pet.name", "required.name",
        "required.java.lang.String", "required"}, fieldError.getCodes());
  }

  /**
   * Method under test: {@link PetValidator#supports(Class)}
   */
  @Test
  void testSupports() {
    // Arrange
    PetValidator petValidator = new PetValidator();
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertFalse(petValidator.supports(clazz));
  }

  /**
   * Method under test: {@link PetValidator#supports(Class)}
   */
  @Test
  void testSupports2() {
    // Arrange
    PetValidator petValidator = new PetValidator();
    Class<Pet> clazz = Pet.class;

    // Act and Assert
    assertTrue(petValidator.supports(clazz));
  }
}
