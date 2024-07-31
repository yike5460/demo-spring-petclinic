package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class VisitDiffblueTest {
  /**
   * Methods under test:
   * <ul>
   *   <li>{@link Visit#setDate(LocalDate)}
   *   <li>{@link Visit#setDescription(String)}
   *   <li>{@link Visit#getDate()}
   *   <li>{@link Visit#getDescription()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange
    Visit visit = new Visit();
    LocalDate date = LocalDate.of(1970, 1, 1);

    // Act
    visit.setDate(date);
    visit.setDescription("The characteristics of someone or something");
    LocalDate actualDate = visit.getDate();
    String actualDescription = visit.getDescription();

    // Assert that nothing has changed
    assertEquals("1970-01-01", actualDate.toString());
    assertEquals("The characteristics of someone or something", actualDescription);
    assertSame(date, actualDate);
  }

  /**
   * Method under test: default or parameterless constructor of {@link Visit}
   */
  @Test
  void testNewVisit() {
    // Arrange and Act
    Visit actualVisit = new Visit();

    // Assert
    assertNull(actualVisit.getId());
    assertNull(actualVisit.getDescription());
    assertTrue(actualVisit.isNew());
  }
}
