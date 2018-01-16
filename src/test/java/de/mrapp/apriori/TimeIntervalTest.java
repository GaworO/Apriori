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

import static org.junit.Assert.*;

/**
 * Tests the functionality fo the class {@link TimeInterval}.
 *
 * @author Michael Rapp
 */
public class TimeIntervalTest {

    @Test
    public final void testConstructorWithTimestampParameter() {
        long timestamp = 10;
        TimeInterval timeInterval = new TimeInterval(timestamp);
        assertEquals(timestamp, timeInterval.getStart());
        assertEquals(timestamp, timeInterval.getStart());
        assertEquals(0, timeInterval.getDuration());
    }

    @Test
    public final void testConstructorWithStartAndEndParameters() {
        long start = 10;
        long end = 100;
        TimeInterval timeInterval = new TimeInterval(start, end);
        assertEquals(start, timeInterval.getStart());
        assertEquals(start, timeInterval.getStart());
        assertEquals(90, timeInterval.getDuration());
    }

    @Test(expected = IllegalArgumentException.class)
    public final void testConstructorThrowsException() {
        new TimeInterval(100, 10);
    }

    @Test
    public final void testIncludes() {
        TimeInterval timeInterval = new TimeInterval(10, 100);
        assertFalse(timeInterval.includes(9));
        assertTrue(timeInterval.includes(10));
        assertTrue(timeInterval.includes(50));
        assertTrue(timeInterval.includes(100));
        assertFalse(timeInterval.includes(101));
    }

    @Test
    public final void testClone() {
        long start = 10;
        long end = 100;
        TimeInterval timeInterval = new TimeInterval(start, end);
        TimeInterval clone = timeInterval.clone();
        assertFalse(timeInterval == clone);
        assertEquals(start, timeInterval.getStart());
        assertEquals(end, timeInterval.getEnd());
    }

    @Test
    public final void testToString() {
        long start = 10;
        long end = 100;
        TimeInterval timeInterval = new TimeInterval(start, end);
        assertEquals("[" + start + ", " + end + "]", timeInterval.toString());
    }

    @Test
    public final void testHashCode() {
        TimeInterval timeInterval1 = new TimeInterval(10, 100);
        TimeInterval timeInterval2 = new TimeInterval(10, 100);
        assertEquals(timeInterval1.hashCode(), timeInterval1.hashCode());
        assertEquals(timeInterval1.hashCode(), timeInterval2.hashCode());
        timeInterval2 = new TimeInterval(20, 100);
        assertNotEquals(timeInterval1.hashCode(), timeInterval2.hashCode());
        timeInterval2 = new TimeInterval(10, 200);
        assertNotEquals(timeInterval1.hashCode(), timeInterval2.hashCode());
    }

    @Test
    public final void testEquals() {
        TimeInterval timeInterval1 = new TimeInterval(10, 100);
        TimeInterval timeInterval2 = new TimeInterval(10, 100);
        assertFalse(timeInterval1.equals(null));
        assertFalse(timeInterval1.equals(new Object()));
        assertTrue(timeInterval1.equals(timeInterval1));
        assertTrue(timeInterval1.equals(timeInterval2));
        timeInterval2 = new TimeInterval(20, 100);
        assertFalse(timeInterval1.equals(timeInterval2));
        timeInterval2 = new TimeInterval(10, 200);
        assertFalse(timeInterval1.equals(timeInterval2));
    }

}