package com.structurizr.dsl;

import com.structurizr.model.Container;
import com.structurizr.model.SoftwareSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerParserTests extends AbstractTests {

    private ContainerParser parser = new ContainerParser();

    @Test
    void test_parse_ThrowsAnException_WhenTheNameIsNotSpecified() {
        try {
            parser.parse(new SoftwareSystemDslContext(null), tokens("container"));
            fail();
        } catch (Exception e) {
            assertEquals("Expected: container <name> [description] [technology] [tags]", e.getMessage());
        }
    }

    @Test
    void test_parse_CreatesAContainer() {
        SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "Description");
        SoftwareSystemDslContext context = new SoftwareSystemDslContext(softwareSystem);
        parser.parse(context, tokens("container", "Name"));

        assertEquals(2, model.getElements().size());
        Container container = softwareSystem.getContainerWithName("Name");
        assertNotNull(container);
        assertEquals("", container.getDescription());
        assertEquals("", container.getTechnology());
        assertEquals("Element,Container", container.getTags());
    }

    @Test
    void test_parse_CreatesAContainerWithADescription() {
        SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "Description");
        SoftwareSystemDslContext context = new SoftwareSystemDslContext(softwareSystem);
        parser.parse(context, tokens("container", "Name", "Description"));

        assertEquals(2, model.getElements().size());
        Container container = softwareSystem.getContainerWithName("Name");
        assertNotNull(container);
        assertEquals("Description", container.getDescription());
        assertEquals("", container.getTechnology());
        assertEquals("Element,Container", container.getTags());
    }

    @Test
    void test_parse_CreatesAContainerWithADescriptionAndTechnology() {
        SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "Description");
        SoftwareSystemDslContext context = new SoftwareSystemDslContext(softwareSystem);
        parser.parse(context, tokens("container", "Name", "Description", "Technology"));

        assertEquals(2, model.getElements().size());
        Container container = softwareSystem.getContainerWithName("Name");
        assertNotNull(container);
        assertEquals("Description", container.getDescription());
        assertEquals("Technology", container.getTechnology());
        assertEquals("Element,Container", container.getTags());
    }

    @Test
    void test_parse_CreatesAContainerWithADescriptionAndTechnologyAndTags() {
        SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "Description");
        SoftwareSystemDslContext context = new SoftwareSystemDslContext(softwareSystem);
        parser.parse(context, tokens("container", "Name", "Description", "Technology", "Tag 1, Tag 2"));

        assertEquals(2, model.getElements().size());
        Container container = softwareSystem.getContainerWithName("Name");
        assertNotNull(container);
        assertEquals("Description", container.getDescription());
        assertEquals("Technology", container.getTechnology());
        assertEquals("Element,Container,Tag 1,Tag 2", container.getTags());
    }

}