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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * An abstract base class for all tests, which test association rules.
 *
 * @param <RuleType> The type of the association rules to be tested
 * @author Michael Rapp
 */
public abstract class AbstractAssociationRuleTest<RuleType extends AssociationRule<NamedItem>> {

    protected abstract RuleType createAssociationRule(@NotNull final ItemSet<NamedItem> body,
                                                      @NotNull final ItemSet<NamedItem> head, final double support);

    @Test
    public final void testCoversWithArrayParameter() {
        NamedItem[] items = {new NamedItem("a"), new NamedItem("c"), new NamedItem("d"), new NamedItem(
                "e"), new NamedItem("f")};
        ItemSet<NamedItem> body = new ItemSet<>();
        body.add(new NamedItem("a"));
        body.add(new NamedItem("b"));
        ItemSet<NamedItem> head = new ItemSet<>();
        head.add(new NamedItem("c"));
        AssociationRule<NamedItem> associationRule = createAssociationRule(new ItemSet<>(), head, 0.5);
        assertTrue(associationRule.covers(items));
        associationRule = createAssociationRule(body, head, 0.5);
        assertFalse(associationRule.covers(items));
        items[1] = new NamedItem("b");
        assertTrue(associationRule.covers(items));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testCoversWithArrayParameterThrowsException() {
        NamedItem[] items = null;
        createAssociationRule(new ItemSet<>(), new ItemSet<>(), 0.5).covers(items);
    }

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
        AssociationRule<NamedItem> associationRule = createAssociationRule(new ItemSet<>(), head, 0.5);
        assertTrue(associationRule.covers(items));
        associationRule = createAssociationRule(body, head, 0.5);
        assertFalse(associationRule.covers(items));
        items.add(new NamedItem("b"));
        assertTrue(associationRule.covers(items));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testCoversWithIterableParameterThrowsException() {
        Iterable<NamedItem> iterable = null;
        createAssociationRule(new ItemSet<>(), new ItemSet<>(), 0.5).covers(iterable);
    }

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
        AssociationRule<NamedItem> associationRule = createAssociationRule(new ItemSet<>(), head, 0.5);
        assertTrue(associationRule.covers(items.iterator()));
        associationRule = createAssociationRule(body, head, 0.5);
        assertFalse(associationRule.covers(items.iterator()));
        items.add(new NamedItem("b"));
        assertTrue(associationRule.covers(items.iterator()));
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testCoversWithIteratorParameterThrowsException() {
        Iterator<NamedItem> iterator = null;
        createAssociationRule(new ItemSet<>(), new ItemSet<>(), 0.5).covers(iterator);
    }

    @Test
    public final void testCompareTo() {
        NamedItem item1 = new NamedItem("a");
        NamedItem item2 = new NamedItem("b");
        ItemSet<NamedItem> body = new ItemSet<>();
        body.add(item1);
        ItemSet<NamedItem> head = new ItemSet<>();
        head.add(item2);
        AssociationRule<NamedItem> associationRule1 = createAssociationRule(body, head, 0.5);
        AssociationRule<NamedItem> associationRule2 = createAssociationRule(body, head, 0.5);
        assertEquals(0, associationRule1.compareTo(associationRule2));
        associationRule2 = createAssociationRule(body, head, 0.6);
        assertEquals(-1, associationRule1.compareTo(associationRule2));
        associationRule2 = createAssociationRule(body, head, 0.4);
        assertEquals(1, associationRule1.compareTo(associationRule2));
    }

}