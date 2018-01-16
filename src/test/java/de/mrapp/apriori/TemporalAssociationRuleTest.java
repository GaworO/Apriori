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

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the functionality of the class {@link TemporalAssociationRule}.
 *
 * @author Michael Rapp
 */
public class TemporalAssociationRuleTest extends AbstractAssociationRuleTest<TemporalAssociationRule<NamedItem>> {


    @Override
    protected TemporalAssociationRule<NamedItem> createAssociationRule(@NotNull final ItemSet<NamedItem> body,
                                                                       @NotNull final ItemSet<NamedItem> head,
                                                                       final double support) {
        return new TemporalAssociationRule<>(body, head, support, new TimeInterval(10, 100));
    }

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
        TemporalAssociationRule<NamedItem> associationRule = new TemporalAssociationRule<>(body, head, support,
                timeInterval);
        assertEquals(body, associationRule.getBody());
        assertEquals(head, associationRule.getHead());
        assertEquals(support, associationRule.getSupport(), 0);
        assertEquals(timeInterval, associationRule.getTimeInterval());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorThrowsExceptionWhenBodyIsNull() {
        new TemporalAssociationRule<>(null, new ItemSet<>(), 0.5, new TimeInterval(10, 100));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorThrowsExceptionWhenHeadIsNull() {
        new TemporalAssociationRule<>(new ItemSet<>(), null, 0.5, new TimeInterval(10, 100));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorThrowsExceptionWhenSupportIsLessThanZero() {
        new TemporalAssociationRule<>(new ItemSet<>(), new ItemSet<>(), -1, new TimeInterval(10, 100));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorThrowsExceptionWhenSupportIsGreaterThanOne() {
        new TemporalAssociationRule<>(new ItemSet<>(), new ItemSet<>(), 1.1, new TimeInterval(10, 100));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorThrowsExceptionIfTimeIntervalIsNull() {
        new TemporalAssociationRule<>(new ItemSet<>(), new ItemSet<>(), 0.5, null);
    }

    @Test
    public final void testIsValidAt() {
        TemporalAssociationRule<NamedItem> associationRule = new TemporalAssociationRule<>(new ItemSet<>(),
                new ItemSet<>(), 0.5, new TimeInterval(10, 100));
        assertFalse(associationRule.isValidAt(9));
        assertTrue(associationRule.isValidAt(10));
        assertTrue(associationRule.isValidAt(50));
        assertTrue(associationRule.isValidAt(100));
        assertFalse(associationRule.isValidAt(101));
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
        TemporalAssociationRule<NamedItem> associationRule1 = new TemporalAssociationRule<>(body, head, support,
                timeInterval);
        TemporalAssociationRule<NamedItem> associationRule2 = associationRule1.clone();
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
        TimeInterval timeInterval = new TimeInterval(10, 100);
        TemporalAssociationRule<NamedItem> associationRule = new TemporalAssociationRule<>(body, head, 0.5,
                timeInterval);
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
        TemporalAssociationRule<NamedItem> associationRule1 = new TemporalAssociationRule<>(body1, head1, 0.5,
                timeInterval1);
        TemporalAssociationRule<NamedItem> associationRule2 = new TemporalAssociationRule<>(body1, head1, 0.5,
                timeInterval1);
        assertEquals(associationRule1.hashCode(), associationRule1.hashCode());
        assertEquals(associationRule1.hashCode(), associationRule2.hashCode());
        associationRule2 = new TemporalAssociationRule<>(body2, head1, 0.5, timeInterval1);
        assertNotEquals(associationRule1.hashCode(), associationRule2.hashCode());
        associationRule2 = new TemporalAssociationRule<>(body1, head2, 0.5, timeInterval1);
        assertNotEquals(associationRule1.hashCode(), associationRule2.hashCode());
        associationRule2 = new TemporalAssociationRule<>(body1, head1, 0.6, timeInterval1);
        assertNotEquals(associationRule1.hashCode(), associationRule2.hashCode());
        associationRule2 = new TemporalAssociationRule<>(body1, head1, 0.5, timeInterval2);
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
        TemporalAssociationRule associationRule1 = new TemporalAssociationRule<>(body1, head1, 0.5, timeInterval1);
        TemporalAssociationRule associationRule2 = new TemporalAssociationRule<>(body1, head1, 0.5, timeInterval1);
        assertFalse(associationRule1.equals(null));
        assertFalse(associationRule1.equals(new Object()));
        assertTrue(associationRule1.equals(associationRule1));
        assertTrue(associationRule1.equals(associationRule2));
        associationRule2 = new TemporalAssociationRule<>(body2, head1, 0.5, timeInterval1);
        assertFalse(associationRule1.equals(associationRule2));
        associationRule2 = new TemporalAssociationRule<>(body1, head2, 0.5, timeInterval1);
        assertFalse(associationRule1.equals(associationRule2));
        associationRule2 = new TemporalAssociationRule<>(body1, head1, 0.6, timeInterval1);
        assertFalse(associationRule1.equals(associationRule2));
        associationRule2 = new TemporalAssociationRule<>(body1, head1, 0.5, timeInterval2);
        assertFalse(associationRule1.equals(associationRule2));
    }

}