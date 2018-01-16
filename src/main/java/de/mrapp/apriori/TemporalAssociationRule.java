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

import static de.mrapp.util.Condition.ensureNotNull;

/**
 * An {@link AssociationRule} which is only valid for a certain time interval.
 *
 * @param <ItemType> The type of the items, the association rule's body and head consist of
 * @author Michael Rapp
 * @since 2.0.0
 */
public class TemporalAssociationRule<ItemType extends Item> extends AssociationRule<ItemType> {

    /**
     * The constant serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The time interval, the association rule is valid for.
     */
    private final TimeInterval timeInterval;

    /**
     * Creates a new association rule, which is only valid for a certain time interval.
     *
     * @param body         An item set, which contains the items, which are contained by the association rule's body, as
     *                     an instance of the class {@link ItemSet}. The item set may not be null
     * @param head         An item set, which contains the items, which are contained by the association rule's head, as
     *                     an instance of the class {@link ItemSet}. The item set may no
     * @param support      The support of the association rule as a {@link Double} value. The support
     * @param timeInterval The time interval, the association rule is valid for, as an instance of the class {@link
     *                     TimeInterval}. The time interval may not be null
     */
    public TemporalAssociationRule(@NotNull final ItemSet<ItemType> body, @NotNull final ItemSet<ItemType> head,
                                   final double support, @NotNull final TimeInterval timeInterval) {
        super(body, head, support);
        ensureNotNull(timeInterval, "The time interval may not be null");
        this.timeInterval = timeInterval;
    }

    /**
     * Returns the time interval, the association rule is valid for.
     *
     * @return The time interval, the association rule is valid for, as an instance of the class {@link TimeInterval}.
     * The time interval may not be null
     */
    @NotNull
    public final TimeInterval getTimeInterval() {
        return timeInterval;
    }

    /**
     * Returns, whether the association rule is valid at a specific moment in time, or not.
     *
     * @param timestamp The timestamp, which specifies the moment in time to be checked, as a {@link Long} value
     * @return True, if the association rule is valid at the given moment in time, false otherwise
     */
    public final boolean isValidAt(final long timestamp) {
        return timeInterval.includes(timestamp);
    }

    @Override
    public final TemporalAssociationRule<ItemType> clone() {
        return new TemporalAssociationRule<>(getBody().clone(), getHead().clone(), getSupport(), timeInterval.clone());
    }

    @Override
    public final String toString() {
        return super.toString() + "; " + timeInterval.toString();
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + timeInterval.hashCode();
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!super.equals(obj))
            return false;
        TemporalAssociationRule<?> other = (TemporalAssociationRule<?>) obj;
        return timeInterval.equals(other.timeInterval);
    }

}