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

import de.mrapp.apriori.Apriori.Configuration;
import org.junit.Test;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests the functionality of the class {@link Apriori}.
 *
 * @author Michael Rapp
 */
public class AprioriTest {

    /**
     * Tests, if all class members are set correctly by the default constructor of the inner class
     * {@link Configuration}.
     */
    @Test
    public final void testConfigurationDefaultConstructor() {
        Configuration configuration = new Configuration();
        assertEquals(0.0, configuration.getMinSupport());
        assertEquals(1.0, configuration.getMaxSupport());
        assertEquals(0.1, configuration.getSupportDelta());
        assertEquals(0, configuration.getFrequentItemSetCount());
        assertFalse(configuration.isGeneratingRules());
        assertEquals(0.0, configuration.getMinConfidence());
        assertEquals(1.0, configuration.getMaxConfidence());
        assertEquals(0.1, configuration.getConfidenceDelta());
        assertEquals(0, configuration.getRuleCount());
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the minimum support, if the support is less than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetMinSupportThrowsExceptionWhenSupportIsLessThanZero() {
        new Configuration().setMinSupport(-0.1);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the minimum support, if the support is greater than the maximum support.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetMinSupportThrowsExceptionWhenSupportIsGreaterThanMaxSupport() {
        Configuration configuration = new Configuration();
        configuration.setMaxSupport(0.8);
        configuration.setMinSupport(0.9);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the maximum support, if the support is greater than 1
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetMaxSupportThrowsExceptionWhenSupportIsGreaterThanOne() {
        new Configuration().setMaxSupport(1.1);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the maximum support, if the support is less than the minimum support.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetMaxSupportThrowsExceptionWhenSupportIsLessThanMinSupport() {
        Configuration configuration = new Configuration();
        configuration.setMaxSupport(configuration.getMinSupport() - 0.1);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the support delta, if the delta is not greater than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetSupportDeltaThrowsExceptionWhenDeltaIsNotGreaterThanOne() {
        new Configuration().setSupportDelta(0);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the frequent item set count, if the count is less than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetFrequentItemSetCountThrowsExceptionWhenCountIsLessThanZero() {
        new Configuration().setFrequentItemSetCount(-1);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the minimum confidence, if the confidence is less than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetMinConfidenceThrowsExceptionWhenConfidenceIsLessThanZero() {
        new Configuration().setMinConfidence(-0.1);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the minimum confidence, if the confidence is greater than the maximum confidence.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetMinConfidenceThrowsExceptionWhenConfidenceIsGreaterThanMaxConfidence() {
        Configuration configuration = new Configuration();
        configuration.setMaxConfidence(0.8);
        configuration.setMinConfidence(0.9);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the maximum confidence, if the confidence is greater than 1.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetMaxConfidenceThrowsExceptionWhenConfidenceIsGreaterThanOne() {
        new Configuration().setMaxConfidence(1.1);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the maximum confidence, if the confidence is less than the minimum confidence.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetMaxConfidenceThrowsExceptionWhenConfidenceIsLessThanMinConfidence() {
        Configuration configuration = new Configuration();
        configuration.setMaxConfidence(configuration.getMinConfidence() - 0.1);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the confidence delta, if the delta is not greater than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetConfidenceDeltaThrowsExceptionWhenDeltaIsNotGreaterThanZero() {
        new Configuration().setConfidenceDelta(0);
    }

    /**
     * Ensures, that an {@link IllegalArgumentException} is thrown by the method, which allows to
     * set the rule count, if the count is less than 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetRuleCountThrowsExceptionWhenCountIsLessThanZero() {
        new Configuration().setRuleCount(-1);
    }

    /**
     * Tests the functionality of the clone-method.
     */
    @Test
    public final void testClone() {
        double minSupport = 0.3;
        double maxSupport = 0.8;
        double supportDelta = 0.2;
        int frequentItemSetCount = 2;
        boolean generateRules = true;
        double minConfidence = 0.8;
        double maxConfidence = 0.9;
        double confidenceDelta = 0.2;
        int ruleCount = 2;
        Configuration configuration1 = new Configuration();
        configuration1.setMinSupport(minSupport);
        configuration1.setMaxSupport(maxSupport);
        configuration1.setSupportDelta(supportDelta);
        configuration1.setFrequentItemSetCount(frequentItemSetCount);
        configuration1.setGenerateRules(generateRules);
        configuration1.setMinConfidence(minConfidence);
        configuration1.setMaxConfidence(maxConfidence);
        configuration1.setConfidenceDelta(confidenceDelta);
        configuration1.setRuleCount(ruleCount);
        Configuration configuration2 = configuration1.clone();
        assertEquals(configuration1.getMinSupport(), configuration2.getMinSupport());
        assertEquals(configuration1.getMaxSupport(), configuration2.getMaxSupport());
        assertEquals(configuration1.getSupportDelta(), configuration2.getSupportDelta());
        assertEquals(configuration1.getFrequentItemSetCount(),
                configuration2.getFrequentItemSetCount());
        assertEquals(configuration1.isGeneratingRules(), configuration2.isGeneratingRules());
        assertEquals(configuration1.getMinConfidence(), configuration2.getMinConfidence());
        assertEquals(configuration1.getMaxConfidence(), configuration2.getMaxConfidence());
        assertEquals(configuration1.getConfidenceDelta(), configuration2.getConfidenceDelta());
        assertEquals(configuration1.getRuleCount(), configuration2.getRuleCount());
    }

    /**
     * Tests the functionality of the toString-method of the inner class {@link Configuration}.
     */
    @Test
    public final void testToString() {
        double minSupport = 0.3;
        double maxSupport = 0.8;
        double supportDelta = 0.2;
        int frequentItemSetCount = 2;
        boolean generateRules = true;
        double minConfidence = 0.8;
        double maxConfidence = 0.9;
        double confidenceDelta = 0.2;
        int ruleCount = 2;
        Configuration configuration = new Configuration();
        configuration.setMinSupport(minSupport);
        configuration.setMaxSupport(maxSupport);
        configuration.setSupportDelta(supportDelta);
        configuration.setFrequentItemSetCount(frequentItemSetCount);
        configuration.setGenerateRules(generateRules);
        configuration.setMinConfidence(minConfidence);
        configuration.setMaxConfidence(maxConfidence);
        configuration.setConfidenceDelta(confidenceDelta);
        configuration.setRuleCount(ruleCount);
        assertEquals("[minSupport=" + minSupport + ", maxSupport=" + maxSupport +
                ", supportDelta=" + supportDelta + ", frequentItemSetCount=" +
                frequentItemSetCount + ", generateRules=" + generateRules + ", minConfidence=" +
                minConfidence + ", maxConfidence=" + maxConfidence + ", confidenceDelta=" +
                confidenceDelta + ", ruleCount=" + ruleCount + "]", configuration.toString());
    }

    /**
     * Tests the functionality of the hashCode-method of the inner class {@link Configuration}.
     */
    @Test
    public final void testHashCode() {
        Configuration configuration1 = new Configuration();
        Configuration configuration2 = new Configuration();
        assertEquals(configuration1.hashCode(), configuration1.hashCode());
        assertEquals(configuration1.hashCode(), configuration2.hashCode());
        configuration1.setMinSupport(0.6);
        assertNotSame(configuration1.hashCode(), configuration2.hashCode());
        configuration2.setMinSupport(configuration1.getMinSupport());
        configuration1.setMaxSupport(0.9);
        assertNotSame(configuration1.hashCode(), configuration2.hashCode());
        configuration2.setMaxSupport(configuration1.getMaxSupport());
        configuration1.setSupportDelta(0.2);
        assertNotSame(configuration1.hashCode(), configuration2.hashCode());
        configuration2.setSupportDelta(configuration1.getSupportDelta());
        configuration1.setFrequentItemSetCount(2);
        assertNotSame(configuration1.hashCode(), configuration2.hashCode());
        configuration2.setFrequentItemSetCount(configuration1.getFrequentItemSetCount());
        configuration1.setGenerateRules(true);
        assertNotSame(configuration1.hashCode(), configuration2.hashCode());
        configuration2.setGenerateRules(configuration1.isGeneratingRules());
        configuration1.setMinConfidence(0.6);
        assertNotSame(configuration1.hashCode(), configuration2.hashCode());
        configuration2.setMinConfidence(configuration1.getMinConfidence());
        configuration1.setMaxConfidence(0.9);
        assertNotSame(configuration1.hashCode(), configuration2.hashCode());
        configuration2.setMaxConfidence(configuration1.getMaxConfidence());
        configuration1.setConfidenceDelta(0.2);
        assertNotSame(configuration1.hashCode(), configuration2.hashCode());
        configuration2.setConfidenceDelta(configuration1.getConfidenceDelta());
        configuration1.setRuleCount(2);
        assertNotSame(configuration1.hashCode(), configuration2.hashCode());
    }

    /**
     * Tests the functionality of the equals-method of the inner class {@link Configuration}.
     */
    @Test
    public final void testEquals() {
        Configuration configuration1 = new Configuration();
        Configuration configuration2 = new Configuration();
        assertFalse(configuration1.equals(null));
        assertFalse(configuration1.equals(new Object()));
        assertTrue(configuration1.equals(configuration1));
        assertTrue(configuration1.equals(configuration2));
        configuration1.setMinSupport(0.6);
        assertFalse(configuration1.equals(configuration2));
        configuration2.setMinSupport(configuration1.getMinSupport());
        configuration1.setMaxSupport(0.9);
        assertFalse(configuration1.equals(configuration2));
        configuration2.setMaxSupport(configuration1.getMaxSupport());
        configuration1.setSupportDelta(0.2);
        assertFalse(configuration1.equals(configuration2));
        configuration2.setSupportDelta(configuration1.getSupportDelta());
        configuration1.setFrequentItemSetCount(2);
        assertFalse(configuration1.equals(configuration2));
        configuration2.setFrequentItemSetCount(configuration1.getFrequentItemSetCount());
        configuration1.setGenerateRules(true);
        assertFalse(configuration1.equals(configuration2));
        configuration2.setGenerateRules(configuration1.isGeneratingRules());
        configuration1.setMinConfidence(0.6);
        assertFalse(configuration1.equals(configuration2));
        configuration2.setMinConfidence(configuration1.getMinConfidence());
        configuration1.setMaxConfidence(0.9);
        assertFalse(configuration1.equals(configuration2));
        configuration2.setMaxConfidence(configuration1.getMaxConfidence());
        configuration1.setConfidenceDelta(0.2);
        assertFalse(configuration1.equals(configuration2));
        configuration2.setConfidenceDelta(configuration1.getConfidenceDelta());
        configuration1.setRuleCount(2);
        assertFalse(configuration1.equals(configuration2));
    }

    /**
     * Tests the functionality of the builder, when configuring the Apriori algorithm to not
     * trying to find a specific number of frequent item sets.
     */
    @Test
    public final void testBuilderWhenNotTryingToFindASpecificNumberOfFrequentItemSets() {
        double minSupport = 0.3;
        double maxSupport = 0.8;
        double supportDelta = 0.2;
        int frequentItemSetCount = 0;
        Apriori<NamedItem> apriori = new Apriori.Builder<NamedItem>(minSupport)
                .maxSupport(maxSupport)
                .supportDelta(supportDelta)
                .frequentItemSetCount(frequentItemSetCount).create();
        Configuration configuration = apriori.getConfiguration();
        assertEquals(minSupport, configuration.getMinSupport());
        assertEquals(maxSupport, configuration.getMaxSupport());
        assertEquals(supportDelta, configuration.getSupportDelta());
        assertEquals(frequentItemSetCount, configuration.getFrequentItemSetCount());
        assertFalse(configuration.isGeneratingRules());
    }

    /**
     * Tests the functionality of the builder, when configuring the Apriori algorithm to trying
     * to find a specific number of frequent item sets.
     */
    @Test
    public final void testBuilderWhenTryingToFindASpecificNumberOfFrequentItemSets() {
        double minSupport = 0.3;
        double maxSupport = 0.8;
        double supportDelta = 0.2;
        int frequentItemSetCount = 2;
        Apriori<NamedItem> apriori = new Apriori.Builder<NamedItem>(frequentItemSetCount)
                .minSupport(minSupport)
                .maxSupport(maxSupport)
                .supportDelta(supportDelta).create();
        Configuration configuration = apriori.getConfiguration();
        assertEquals(minSupport, configuration.getMinSupport());
        assertEquals(maxSupport, configuration.getMaxSupport());
        assertEquals(supportDelta, configuration.getSupportDelta());
        assertEquals(frequentItemSetCount, configuration.getFrequentItemSetCount());
        assertFalse(configuration.isGeneratingRules());
    }

    /**
     * Tests the functionality of the builder, when configuring the Apriori algorithm to generate
     * association rules.
     */
    @Test
    public final void testBuilderWhenGeneratingAssociationRules() {
        double minSupport = 0.3;
        double maxSupport = 0.8;
        double supportDelta = 0.2;
        int frequentItemSetCount = 2;
        double minConfidence = 0.4;
        double maxConfidence = 0.8;
        double confidenceDelta = 0.2;
        int ruleCount = 0;
        Apriori<NamedItem> apriori = new Apriori.Builder<NamedItem>(frequentItemSetCount)
                .generateRules(minConfidence).minSupport(minSupport).maxSupport(maxSupport)
                .supportDelta(supportDelta).frequentItemSetCount(frequentItemSetCount)
                .maxConfidence(maxConfidence).confidenceDelta(confidenceDelta).ruleCount(ruleCount)
                .create();
        Configuration configuration = apriori.getConfiguration();
        assertEquals(minSupport, configuration.getMinSupport());
        assertEquals(maxSupport, configuration.getMaxSupport());
        assertEquals(supportDelta, configuration.getSupportDelta());
        assertEquals(frequentItemSetCount, configuration.getFrequentItemSetCount());
        assertFalse(configuration.isGeneratingRules());
        assertEquals(minConfidence, configuration.getMinConfidence());
        assertEquals(maxConfidence, configuration.getMaxConfidence());
        assertEquals(confidenceDelta, configuration.getConfidenceDelta());
        assertEquals(ruleCount, configuration.getRuleCount());
    }

    /**
     * Tests the functionality of the builder, when configuring the Apriori algorithm to trying to
     * generate a specific number of association rules.
     */
    @Test
    public final void testBuilderWhenTryingToGenerateASpecificNumberOfAssociationRules() {
        double minSupport = 0.3;
        double maxSupport = 0.8;
        double supportDelta = 0.2;
        int frequentItemSetCount = 2;
        double minConfidence = 0.4;
        double maxConfidence = 0.8;
        double confidenceDelta = 0.2;
        int ruleCount = 2;
        Apriori<NamedItem> apriori = new Apriori.Builder<NamedItem>(frequentItemSetCount)
                .generateRules(ruleCount).minSupport(minSupport).maxSupport(maxSupport)
                .supportDelta(supportDelta).frequentItemSetCount(frequentItemSetCount)
                .minConfidence(minConfidence).maxConfidence(maxConfidence)
                .confidenceDelta(confidenceDelta).create();
        Configuration configuration = apriori.getConfiguration();
        assertEquals(minSupport, configuration.getMinSupport());
        assertEquals(maxSupport, configuration.getMaxSupport());
        assertEquals(supportDelta, configuration.getSupportDelta());
        assertEquals(frequentItemSetCount, configuration.getFrequentItemSetCount());
        assertFalse(configuration.isGeneratingRules());
        assertEquals(minConfidence, configuration.getMinConfidence());
        assertEquals(maxConfidence, configuration.getMaxConfidence());
        assertEquals(confidenceDelta, configuration.getConfidenceDelta());
        assertEquals(ruleCount, configuration.getRuleCount());
    }

}