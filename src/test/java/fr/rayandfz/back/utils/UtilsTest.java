package fr.rayandfz.back.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void testCopyNonNullProperties() {
        SourceObject src = new SourceObject();
        src.setName("John Doe");
        src.setAge(30);
        src.setEmail(null);

        TargetObject target = new TargetObject();
        target.setName("Initial Name");
        target.setAge(25);
        target.setEmail("initial@example.com");

        Utils.copyNonNullProperties(src, target);

        assertEquals("John Doe", target.getName());
        assertEquals(30, target.getAge());

        assertEquals("initial@example.com", target.getEmail());
    }

    @Test
    void testCopyNonNullPropertiesWithAllNullsInSource() {
        SourceObject src = new SourceObject();

        TargetObject target = new TargetObject();
        target.setName("Initial Name");
        target.setAge(25);
        target.setEmail("initial@example.com");

        Utils.copyNonNullProperties(src, target);

        assertEquals("Initial Name", target.getName());
        assertEquals(25, target.getAge());
        assertEquals("initial@example.com", target.getEmail());
    }
}
