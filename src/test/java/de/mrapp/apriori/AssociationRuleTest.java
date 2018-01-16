/*
 * Copyright 2017 Michael Rapp
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.mrapp.apriori;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Tests the functionality of the class {@link AssociationRule}.
 *
 * @author Michael Rapp
 */
public class AssociationRuleTest {

    /**
     * Tests, if all class members are set correctly by the constructor.
     */
    @Test
    public final void testConstructor() {
        ItemSet<NamedItem> body = new ItemSet<>();
        body.add(new NamedItem("a"));
        body.add(new NamedItem("b"));
        ItemSet<NamedItem> head = new ItemSet<>();
        head.add(new NamedItem("c"));
        head.add(new NamedItem("d"));
        double support = 0.5;
        TimeInterval timeInterval = new TimeInterval(10, 100);
        AssociationRule<NamedItem> associationRule = new AssociationRule<>(body, head, support, timeInterval);
        assertEquals(body, associationRule.getBody());
        assertEquals(head, associationRule.getHead());
        assertEquals(support, associationRule.getSupport(), 0);
        assertEquals(timeInterval, associationRule.getTimeInterval());
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown, if the body, which is passed as a constructor
     * parameter, is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorThrowsExceptionWhenBodyIsNull() {
        new AssociationRule<>(null, new ItemSet<>(), 0.5, null);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown, if the head, which is passed as a constructor
     * parameter, is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorThrowsExceptionWhenHeadIsNull() {
        new AssociationRule<>(new ItemSet<>(), null, 0.5, null);
    }

    /**
     * Ensures, tha an {@link IllegalArgumentException} is thrown, if the support, which is passed as a constructor
     * parameter, is less than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorThrowsExceptionWhenSupportIsLessThanZero() {
        new AssociationRule<>(new ItemSet<>(), new ItemSet<>(), -1, null);
    }

    /**
     * Ensures, tha an {@link IllegalArgumentException} is thrown, if the support, which is passed as a constructor
     * parameter, is greater than 1.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorThrowsExceptionWhenSupportIsGreaterThanOne() {
        new AssociationRule<>(new ItemSet<>(), new ItemSet<>(), 1.1, null);
    }

    /**
     * Tests the functionality of the {@link AssociationRule#covers(Item[])} method.
     */
    @Test
    public final void testCoversWithArrayParameter() {
        NamedItem[] items = {new NamedItem("a"), new NamedItem("c"), new NamedItem("d"), new NamedItem(
                "e"), new NamedItem("f")};
        ItemSet<NamedItem> body = new ItemSet<>();
        body.add(new NamedItem("a"));
        body.add(new NamedItem("b"));
        ItemSet<NamedItem> head = new ItemSet<>();
        head.add(new NamedItem("c"));
        AssociationRule<NamedItem> associationRule = new AssociationRule<>(new ItemSet<>(), head, 0.5, null);
        assertTrue(associationRule.covers(items));
        associationRule = new AssociationRule<>(body, head, 0.5, null);
        assertFalse(associationRule.covers(items));
        items[1] = new NamedItem("b");
        assertTrue(associationRule.covers(items));
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the {@link AssociationRule#covers(Item[])} method,
     * if the given array is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testCoversWithArrayParameterThrowsException() {
        NamedItem[] items = null;
        new AssociationRule<>(new ItemSet<>(), new ItemSet<>(), 0.5, null).covers(items);
    }

    /**
     * Tests the functionality of the {@link AssociationRule#covers(Iterable)} method.
     */
    @Test
    public final void testCoversWithIterableParameter() {
        Collection<NamedItem> items = new LinkedList<>();
        items.add(new NamedItem("a"));
        items.add(new NamedItem("c"));
        items.add(new NamedItem("d"));
        items.add(new NamedItem("e"));
        items.add(new NamedItem("f"));
        ItemSet<NamedItem> body = new ItemSet<>();
        body.add(new NamedItem("a"));
        body.add(new NamedItem("b"));
        ItemSet<NamedItem> head = new ItemSet<>();
        head.add(new NamedItem("c"));
        AssociationRule<NamedItem> associationRule = new AssociationRule<>(new ItemSet<>(), head, 0.5, null);
        assertTrue(associationRule.covers(items));
        associationRule = new AssociationRule<>(body, head, 0.5, null);
        assertFalse(associationRule.covers(items));
        items.add(new NamedItem("b"));
        assertTrue(associationRule.covers(items));
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the {@link AssociationRule#covers(Iterable)}
     * method, if the given iterable is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testCoversWithIterableParameterThrowsException() {
        Iterable<NamedItem> iterable = null;
        new AssociationRule<>(new ItemSet<>(), new ItemSet<>(), 0.5, null).covers(iterable);
    }

    /**
     * Tests the functionality of the {@link AssociationRule#covers(Iterator)} method.
     */
    @Test
    public final void testCoversWithIteratorParameter() {
        Collection<NamedItem> items = new LinkedList<>();
        items.add(new NamedItem("a"));
        items.add(new NamedItem("c"));
        items.add(new NamedItem("d"));
        items.add(new NamedItem("e"));
        items.add(new NamedItem("f"));
        ItemSet<NamedItem> body = new ItemSet<>();
        body.add(new NamedItem("a"));
        body.add(new NamedItem("b"));
        ItemSet<NamedItem> head = new ItemSet<>();
        head.add(new NamedItem("c"));
        AssociationRule<NamedItem> associationRule = new AssociationRule<>(new ItemSet<>(), head, 0.5, null);
        assertTrue(associationRule.covers(items.iterator()));
        associationRule = new AssociationRule<>(body, head, 0.5, null);
        assertFalse(associationRule.covers(items.iterator()));
        items.add(new NamedItem("b"));
        assertTrue(associationRule.covers(items.iterator()));
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the {@link AssociationRule#covers(Iterator)}
     * method, if the given iterator is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testCoversWithIteratorParameterThrowsException() {
        Iterator<NamedItem> iterator = null;
        new AssociationRule<>(new ItemSet<>(), new ItemSet<>(), 0.5, null).covers(iterator);
    }

    @Test
    public final void testIsValidAt() {
        AssociationRule<NamedItem> associationRule = new AssociationRule<>(new ItemSet<>(), new ItemSet<>(), 0.5, null);
        assertTrue(associationRule.isValidAt(9));
        associationRule = new AssociationRule<>(new ItemSet<>(), new ItemSet<>(), 0.5, new TimeInterval(10, 100));
        assertFalse(associationRule.isValidAt(9));
        assertTrue(associationRule.isValidAt(10));
        assertTrue(associationRule.isValidAt(50));
        assertTrue(associationRule.isValidAt(100));
        assertFalse(associationRule.isValidAt(101));
    }

    @Test
    public final void testIsTemporal() {
        AssociationRule<NamedItem> associationRule = new AssociationRule<>(new ItemSet<>(), new ItemSet<>(), 0.5, null);
        assertFalse(associationRule.isTemporal());
        associationRule = new AssociationRule<>(new ItemSet<>(), new ItemSet<>(), 0.5, new TimeInterval(10, 100));
        assertTrue(associationRule.isTemporal());
    }

    /**
     * Tests the functionality of the compareTo-method.
     */
    @Test
    public final void testCompareTo() {
        NamedItem item1 = new NamedItem("a");
        NamedItem item2 = new NamedItem("b");
        ItemSet<NamedItem> body = new ItemSet<>();
        body.add(item1);
        ItemSet<NamedItem> head = new ItemSet<>();
        head.add(item2);
        AssociationRule<NamedItem> associationRule1 = new AssociationRule<>(body, head, 0.5, null);
        AssociationRule<NamedItem> associationRule2 = new AssociationRule<>(body, head, 0.5, null);
        assertEquals(0, associationRule1.compareTo(associationRule2));
        associationRule2 = new AssociationRule<>(body, head, 0.6, null);
        assertEquals(-1, associationRule1.compareTo(associationRule2));
        associationRule2 = new AssociationRule<>(body, head, 0.4, null);
        assertEquals(1, associationRule1.compareTo(associationRule2));
    }

    /**
     * Tests the functionality of the clone-method.
     */
    @Test
    public final void testClone() {
        NamedItem item1 = new NamedItem("a");
        NamedItem item2 = new NamedItem("b");
        ItemSet<NamedItem> body = new ItemSet<>();
        body.add(item1);
        ItemSet<NamedItem> head = new ItemSet<>();
        head.add(item2);
        double support = 0.5;
        TimeInterval timeInterval = new TimeInterval(10, 100);
        AssociationRule<NamedItem> associationRule1 = new AssociationRule<>(body, head, support, timeInterval);
        AssociationRule<NamedItem> associationRule2 = associationRule1.clone();
        assertEquals(body.size(), associationRule2.getBody().size());
        assertEquals(item1, associationRule2.getBody().first());
        assertEquals(head.size(), associationRule2.getHead().size());
        assertEquals(item2, associationRule2.getHead().first());
        assertEquals(support, associationRule2.getSupport(), 0);
        assertEquals(timeInterval, associationRule2.getTimeInterval());
    }

    /**
     * Tests the functionality of the toString-method.
     */
    @Test
    public final void testToString() {
        ItemSet<NamedItem> body = new ItemSet<>();
        body.add(new NamedItem("a"));
        body.add(new NamedItem("b"));
        ItemSet<NamedItem> head = new ItemSet<>();
        head.add(new NamedItem("c"));
        head.add(new NamedItem("d"));
        AssociationRule<NamedItem> associationRule = new AssociationRule<>(body, head, 0.5, null);
        assertEquals("[a, b] -> [c, d]", associationRule.toString());
    }

    /**
     * Tests the functionality of the toString-method.
     */
    @Test
    public final void testToStringIfTemporal() {
        ItemSet<NamedItem> body = new ItemSet<>();
        body.add(new NamedItem("a"));
        body.add(new NamedItem("b"));
        ItemSet<NamedItem> head = new ItemSet<>();
        head.add(new NamedItem("c"));
        head.add(new NamedItem("d"));
        TimeInterval timeInterval = new TimeInterval(10, 100);
        AssociationRule<NamedItem> associationRule = new AssociationRule<>(body, head, 0.5, timeInterval);
        assertEquals("[a, b] -> [c, d]; [10, 100]", associationRule.toString());
    }

    /**
     * Tests the functionality of the hashCode-method.
     */
    @Test
    public final void testHashCode() {
        ItemSet<NamedItem> body1 = new ItemSet<>();
        body1.add(new NamedItem("a"));
        ItemSet<NamedItem> body2 = new ItemSet<>();
        body2.add(new NamedItem("c"));
        ItemSet<NamedItem> head1 = new ItemSet<>();
        head1.add(new NamedItem("b"));
        ItemSet<NamedItem> head2 = new ItemSet<>();
        head2.add(new NamedItem("d"));
        TimeInterval timeInterval1 = new TimeInterval(10, 100);
        TimeInterval timeInterval2 = new TimeInterval(20, 90);
        AssociationRule<NamedItem> associationRule1 = new AssociationRule<>(body1, head1, 0.5, null);
        AssociationRule<NamedItem> associationRule2 = new AssociationRule<>(body1, head1, 0.5, null);
        assertEquals(associationRule1.hashCode(), associationRule1.hashCode());
        assertEquals(associationRule1.hashCode(), associationRule2.hashCode());
        associationRule2 = new AssociationRule<>(body2, head1, 0.5, null);
        assertNotEquals(associationRule1.hashCode(), associationRule2.hashCode());
        associationRule2 = new AssociationRule<>(body1, head2, 0.5, null);
        assertNotEquals(associationRule1.hashCode(), associationRule2.hashCode());
        associationRule2 = new AssociationRule<>(body2, head1, 0.6, null);
        assertNotEquals(associationRule1.hashCode(), associationRule2.hashCode());
        associationRule2 = new AssociationRule<>(body2, head1, 0.5, timeInterval1);
        assertNotEquals(associationRule1.hashCode(), associationRule2.hashCode());
        associationRule1 = new AssociationRule<>(body2, head1, 0.5, timeInterval1);
        associationRule2 = new AssociationRule<>(body2, head1, 0.5, timeInterval2);
        assertNotEquals(associationRule1.hashCode(), associationRule2.hashCode());
    }

    /**
     * Tests the functionality of the equals-method.
     */
    @Test
    public final void testEquals() {
        ItemSet<NamedItem> body1 = new ItemSet<>();
        body1.add(new NamedItem("a"));
        ItemSet<NamedItem> body2 = new ItemSet<>();
        body2.add(new NamedItem("c"));
        ItemSet<NamedItem> head1 = new ItemSet<>();
        head1.add(new NamedItem("b"));
        ItemSet<NamedItem> head2 = new ItemSet<>();
        head2.add(new NamedItem("d"));
        TimeInterval timeInterval1 = new TimeInterval(10, 100);
        TimeInterval timeInterval2 = new TimeInterval(20, 90);
        AssociationRule associationRule1 = new AssociationRule<>(body1, head1, 0.5, null);
        AssociationRule associationRule2 = new AssociationRule<>(body1, head1, 0.5, null);
        assertFalse(associationRule1.equals(null));
        assertFalse(associationRule1.equals(new Object()));
        assertTrue(associationRule1.equals(associationRule1));
        assertTrue(associationRule1.equals(associationRule2));
        associationRule2 = new AssociationRule<>(body2, head1, 0.5, null);
        assertFalse(associationRule1.equals(associationRule2));
        associationRule2 = new AssociationRule<>(body1, head2, 0.5, null);
        assertFalse(associationRule1.equals(associationRule2));
        associationRule2 = new AssociationRule<>(body2, head1, 0.6, null);
        assertFalse(associationRule1.equals(associationRule2));
        associationRule2 = new AssociationRule<>(body2, head1, 0.5, timeInterval1);
        assertFalse(associationRule1.equals(associationRule2));
        associationRule1 = new AssociationRule<>(body2, head1, 0.5, timeInterval1);
        associationRule2 = new AssociationRule<>(body2, head1, 0.5, timeInterval2);
        assertFalse(associationRule1.equals(associationRule2));
    }

}