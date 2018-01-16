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

import org.jetbrains.annotations.Nullable;

/**
 * Defines the interface, a transaction, which consists of multiple items, must implement. The Apriori algorithm
 * processes a set of transactions in order to find frequent item sets.
 *
 * @param <ItemType> The type of the items, the transaction consists of
 * @author Michael Rapp
 * @since 1.0.0
 */
public interface Transaction<ItemType extends Item> extends Iterable<ItemType> {

    /**
     * Returns the time interval, the transaction corresponds to.
     *
     * @return The time interval, the transaction corresponds to, as an instance of the class {@link TimeInterval} or
     * null, if the transaction does not correspond to a certain time interval
     */
    @Nullable
    default TimeInterval getTimeInterval() {
        return null;
    }

    /**
     * Returns, whether the transaction corresponds to a certain time interval, or not.
     *
     * @return True, if the transaction corresponds to a certain time interval, false otherwise
     */
    default boolean isTemporal() {
        return getTimeInterval() != null;
    }

}