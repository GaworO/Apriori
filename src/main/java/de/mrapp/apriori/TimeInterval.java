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

import java.io.Serializable;

import static de.mrapp.util.Condition.ensureAtLeast;

/**
 * Represents a time interval, starting at a specific timestamp and ending at a specific timestamp. The timestamps are
 * given as {@link Long} values in order to provide maximum flexibility. E.g. the class {@link java.util.Date} can be
 * used to represent timestamps. Both timestamps are inclusive and may be equal.
 *
 * @author Michael Rapp
 * @since 2.0.0
 */
public class TimeInterval implements Serializable, Cloneable {

    /**
     * The constant serial version UID.
     */
    private static final long serialVersionUID = 3002597429386125047L;

    /**
     * The start timestamp.
     */
    private final long start;

    /**
     * The end timestamp.
     */
    private final long end;

    /**
     * Creates a new time interval with equal start and end timestamp. Such an interval represents a single moment in
     * time.
     *
     * @param timestamp The timestamp as a {@link Long} value
     */
    public TimeInterval(final long timestamp) {
        this(timestamp, timestamp);
    }

    /**
     * Creates a new time interval.
     *
     * @param start The start timestamp as a {@link Long} value
     * @param end   The end timestamp as a {@link Long} value. The end timestamp must be at least the start timestamp
     */
    public TimeInterval(final long start, final long end) {
        ensureAtLeast(end, start, "The end timestamp must be at least the start timestamp");
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start timestamp of the interval.
     *
     * @return The start timestamp as a {@link Long} value
     */
    public final long getStart() {
        return start;
    }

    /**
     * Returns the end timestamp of the interval.
     *
     * @return The end timestamp as a {@link Long} value
     */
    public final long getEnd() {
        return end;
    }

    /**
     * Returns the duration of the interval.
     *
     * @return The duration of the interval as a {@link Long} value
     */
    public final long getDuration() {
        return end - start;
    }

    @Override
    public final TimeInterval clone() {
        return new TimeInterval(start, end);
    }

    @Override
    public final String toString() {
        return "[" + start + ", " + end + "]";
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (start ^ (start >>> 32));
        result = prime * result + (int) (end ^ (end >>> 32));
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TimeInterval other = (TimeInterval) obj;
        return start == other.start && end == other.end;
    }

}