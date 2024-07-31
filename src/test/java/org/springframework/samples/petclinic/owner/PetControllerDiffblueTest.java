package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ContextConfiguration(classes = {PetController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PetControllerDiffblueTest {
  @MockBean
  private OwnerRepository ownerRepository;

  @Autowired
  private PetController petController;

  /**
   * Method under test: {@link PetController#populatePetTypes()}
   */
  @Test
  void testPopulatePetTypes() {
    // Arrange
    ArrayList<PetType> petTypeList = new ArrayList<>();
    when(ownerRepository.findPetTypes()).thenReturn(petTypeList);

    // Act
    Collection<PetType> actualPopulatePetTypesResult = petController.populatePetTypes();

    // Assert
    verify(ownerRepository).findPetTypes();
    assertTrue(actualPopulatePetTypesResult instanceof List);
    assertTrue(actualPopulatePetTypesResult.isEmpty());
    assertSame(petTypeList, actualPopulatePetTypesResult);
  }

  /**
   * Method under test: {@link PetController#populatePetTypes()}
   */
  @Test
  void testPopulatePetTypes2() {
    // Arrange
    when(ownerRepository.findPetTypes()).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> petController.populatePetTypes());
    verify(ownerRepository).findPetTypes();
  }

  /**
   * Method under test: {@link PetController#findOwner(int)}
   */
  @Test
  void testFindOwner() {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setId(1);
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");
    when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);

    // Act
    Owner actualFindOwnerResult = petController.findOwner(1);

    // Assert
    verify(ownerRepository).findById(eq(1));
    assertSame(owner, actualFindOwnerResult);
  }

  /**
   * Method under test: {@link PetController#findOwner(int)}
   */
  @Test
  void testFindOwner2() {
    // Arrange
    when(ownerRepository.findById(Mockito.<Integer>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> petController.findOwner(1));
    verify(ownerRepository).findById(eq(1));
  }

  /**
   * Method under test: {@link PetController#findPet(int, Integer)}
   */
  @Test
  void testFindPet() {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setId(1);
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");
    when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);

    // Act
    Pet actualFindPetResult = petController.findPet(1, 1);

    // Assert
    verify(ownerRepository).findById(eq(1));
    assertNull(actualFindPetResult);
  }

  /**
   * Method under test: {@link PetController#findPet(int, Integer)}
   */
  @Test
  void testFindPet2() {
    // Arrange and Act
    Pet actualFindPetResult = petController.findPet(1, null);

    // Assert
    Collection<Visit> visits = actualFindPetResult.getVisits();
    assertTrue(visits instanceof Set);
    assertNull(actualFindPetResult.getId());
    assertNull(actualFindPetResult.getName());
    assertNull(actualFindPetResult.getBirthDate());
    assertNull(actualFindPetResult.getType());
    assertTrue(visits.isEmpty());
    assertTrue(actualFindPetResult.isNew());
  }

  /**
   * Method under test: {@link PetController#findPet(int, Integer)}
   */
  @Test
  void testFindPet3() {
    // Arrange
    when(ownerRepository.findById(Mockito.<Integer>any())).thenThrow(new IllegalArgumentException("foo"));

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> petController.findPet(1, 1));
    verify(ownerRepository).findById(eq(1));
  }

  /**
   * Method under test: {@link PetController#initCreationForm(Owner, ModelMap)}
   */
  @Test
  void testInitCreationForm() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setId(1);
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");
    when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
    when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}/pets/new", 1);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(petController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(3))
        .andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"))
        .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
  }

  /**
   * Method under test:
   * {@link PetController#initUpdateForm(Owner, int, ModelMap, RedirectAttributes)}
   */
  @Test
  void testInitUpdateForm() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setId(1);
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");
    when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
    when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/{ownerId}/pets/{petId}/edit", 1,
        1);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(petController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(3))
        .andExpect(MockMvcResultMatchers.model().attributeExists("owner", "types"))
        .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
  }

  /**
   * Method under test:
   * {@link PetController#processCreationForm(Owner, Pet, BindingResult, ModelMap, RedirectAttributes)}
   */
  @Test
  void testProcessCreationForm() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setId(1);
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");
    when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
    when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 1);

    // Act and Assert
    MockMvcBuilders.standaloneSetup(petController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(3))
        .andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"))
        .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
  }

  /**
   * Method under test:
   * {@link PetController#processCreationForm(Owner, Pet, BindingResult, ModelMap, RedirectAttributes)}
   */
  @Test
  void testProcessCreationForm2() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setId(1);
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");
    when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
    when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 1)
        .param("birthDate", "2020-03-01");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(petController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(3))
        .andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"))
        .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
  }

  /**
   * Method under test:
   * {@link PetController#processCreationForm(Owner, Pet, BindingResult, ModelMap, RedirectAttributes)}
   */
  @Test
  void testProcessCreationForm3() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setId(1);
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");
    when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
    when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/new", 1)
        .param("name", "Bella");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(petController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.model().size(3))
        .andExpect(MockMvcResultMatchers.model().attributeExists("owner", "pet", "types"))
        .andExpect(MockMvcResultMatchers.view().name("pets/createOrUpdatePetForm"))
        .andExpect(MockMvcResultMatchers.forwardedUrl("pets/createOrUpdatePetForm"));
  }

  /**
   * Method under test:
   * {@link PetController#processUpdateForm(Pet, BindingResult, Owner, ModelMap, RedirectAttributes)}
   */
  @Test
  void testProcessUpdateForm() throws Exception {
    // Arrange
    Owner owner = new Owner();
    owner.setAddress("42 Main St");
    owner.setCity("Oxford");
    owner.setFirstName("Jane");
    owner.setId(1);
    owner.setLastName("Doe");
    owner.setTelephone("6625550144");
    when(ownerRepository.findPetTypes()).thenReturn(new ArrayList<>());
    when(ownerRepository.findById(Mockito.<Integer>any())).thenReturn(owner);
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners/{ownerId}/pets/42/edit",
        "Uri Variables", "Uri Variables");

    // Act
    ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(petController).build().perform(requestBuilder);

    // Assert
    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }
}
