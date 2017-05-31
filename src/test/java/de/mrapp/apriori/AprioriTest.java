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

import java.io.File;

/**
 * Tests the functionality of the class {@link Apriori}.
 *
 * @author Michael Rapp
 */
public class AprioriTest extends AbstractDataTest {

    /**
     * Tests the functionality of the execute-method.
     */
    @Test
    public final void testExecute() {
        File inputFile = getInputFile(INPUT_FILE_2);
        DataIterator dataIterator = new DataIterator(inputFile);

        Apriori<NamedItem> apriori = new Apriori.Builder<NamedItem>(0.5).frequentItemSetCount(0)
                .generateRules(0.5).supportDelta(0.1).create();
    }

}