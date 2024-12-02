package edu.ucu.apps.visitor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTests {

    @Test
    void testStamping() {
        Group<Integer> group = new Group<>();
        Signature<Integer> sig1 = new Signature<>(System.out::println);
        Signature<Integer> sig2 = new Signature<>(x -> System.out.println(x * x));

        group.addTask(sig1).addTask(sig2);

        group.apply(10);

        assertNotNull(group.groupUuid, "Group UUID should not be null");

        assertEquals(group.groupUuid, sig1.getHeader("groupId"), "Signature 1 groupId should match Group UUID");
        assertEquals(group.groupUuid, sig2.getHeader("groupId"), "Signature 2 groupId should match Group UUID");
    }

    @Test
    void testNested() {
        Group<Integer> nestedGroup = new Group<>();
        Signature<Integer> sig1 = new Signature<>(System.out::println);
        nestedGroup.addTask(sig1);

        Group<Integer> parentGroup = new Group<>();
        parentGroup.addTask(nestedGroup);

        parentGroup.apply(10);

        assertNotNull(parentGroup.groupUuid, "Parent group UUID should not be null");

        assertEquals(parentGroup.groupUuid, nestedGroup.groupUuid, "Nested group UUID should match Parent group UUID");

        assertEquals(parentGroup.groupUuid, sig1.getHeader("groupId"), "Signature in nested group should match Parent group UUID");
    }

    @Test
    void testFreeze() {
        Group<Integer> group = new Group<>();
        Signature<Integer> sig = new Signature<>(System.out::println);
        group.addTask(sig);

        group.apply(10);

        assertTrue(group.isFrozen(), "Group should be frozen after apply");
        assertNotNull(group.getId(), "Group ID should not be null after freezing");
        assertNotNull(sig.getId(), "Signature ID should not be null after freezing");

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> {
            group.addTask(new Signature<>(x -> System.out.println(x * 2)));
        });

        assertNotNull(exception, "Adding tasks to a frozen group should throw an exception");
    }

    @Test
    void testMultSignsInGroup() {
        Group<Integer> group = new Group<>();
        Signature<Integer> sig1 = new Signature<>(x -> System.out.println("Task 1: " + x));
        Signature<Integer> sig2 = new Signature<>(x -> System.out.println("Task 2: " + x * 2));
        Signature<Integer> sig3 = new Signature<>(x -> System.out.println("Task 3: " + x * 3));

        group.addTask(sig1).addTask(sig2).addTask(sig3);

        group.apply(5);

        assertEquals(group.groupUuid, sig1.getHeader("groupId"), "Signature 1 groupId should match Group UUID");
        assertEquals(group.groupUuid, sig2.getHeader("groupId"), "Signature 2 groupId should match Group UUID");
        assertEquals(group.groupUuid, sig3.getHeader("groupId"), "Signature 3 groupId should match Group UUID");
    }
}
