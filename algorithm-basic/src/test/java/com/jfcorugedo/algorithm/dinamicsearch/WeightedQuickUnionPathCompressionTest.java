package com.jfcorugedo.algorithm.dinamicsearch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class WeightedQuickUnionPathCompressionTest {

	@Test
	public void executeUnionOperationConnectsTwoElements() {
		
		WeightedQuickUnionPathCompression quickUnionAlgorithm = new WeightedQuickUnionPathCompression(10);
		
		quickUnionAlgorithm.union(0, 1);
		
		assertThat(quickUnionAlgorithm.isConnected(0, 1)).isTrue();
	}
	
	@Test
	public void findUnconnnectedObjectsResturnsFalse() {
		
		WeightedQuickUnionPathCompression quickUnionAlgorithm = new WeightedQuickUnionPathCompression(10);
		
		assertThat(quickUnionAlgorithm.isConnected(0, 1)).isFalse();
	}
	
	@Test
	public void connectionsAreTransitive() {
		
		WeightedQuickUnionPathCompression quickUnionAlgorithm = new WeightedQuickUnionPathCompression(10);
		
		/*
		 * Builds this tree:
		 * 
		 *  0   1    6   7   8
		 *       ___ |
		 *      |    |
		 *      9	 5
		 *    __|
		 *    | |
		 *    2 4
		 *      |
		 *      3      
		 */
		quickUnionAlgorithm.union(2, 9);
		quickUnionAlgorithm.union(4, 9);
		quickUnionAlgorithm.union(3, 4);
		quickUnionAlgorithm.union(5, 6);
		quickUnionAlgorithm.union(9, 6);
		
		assertThat(quickUnionAlgorithm.isConnected(3, 5)).isTrue();
	}
	
	@Test
	public void connectionsAreSymmetric() {
		
		WeightedQuickUnionPathCompression quickUnionAlgorithm = new WeightedQuickUnionPathCompression(10);
		
		quickUnionAlgorithm.union(1, 8);
		
		assertThat(quickUnionAlgorithm.isConnected(1, 8)).isTrue();
		assertThat(quickUnionAlgorithm.isConnected(8, 1)).isTrue();
	}
	
	@Test
	public void connectionsAreReflexive() {
		
		WeightedQuickUnionPathCompression quickUnionAlgorithm = new WeightedQuickUnionPathCompression(10);
		
		//Each node is always connected to itself
		for(int i = 0 ; i < 10 ; i++) {
			assertThat(quickUnionAlgorithm.isConnected(i, i)).isTrue();	
		}		
	}
	
	@Test//(timeout=200)
	public void performanceTest() {
		
		WeightedQuickUnionPathCompression quickUnionAlgorithm = new WeightedQuickUnionPathCompression(10000000);
		
		for(int i = 1 ; i < 10000000 ; i++) {
			quickUnionAlgorithm.union(i, i-1);	
			quickUnionAlgorithm.isConnected(0, 1);//it's the worst case
		}		
	}
	
	/**
     * Given a set of 10 items, execute this union operations: 1-6 5-7 2-4.
     * The result must be [0, 1, 2, 3, 2, 5, 1, 5, 8, 9]
     * 
     */
    @Test
    public void examQuestionTest() {
        
        WeightedQuickUnionPathCompression quickUnionAlgorithm = new WeightedQuickUnionPathCompression(10);
        
        quickUnionAlgorithm.union(1, 6);
        quickUnionAlgorithm.union(5, 7);
        quickUnionAlgorithm.union(2, 4);
        
        assertThat(quickUnionAlgorithm.getTree()).containsExactly(0, 1, 2, 3, 2, 5, 1, 5, 8, 9);
        assertThat(quickUnionAlgorithm.getTreesSize()).containsExactly(1, 2, 2, 1, 1, 2, 1, 1, 1, 1);
    }
    
    /**
     * Given a set of 10 items, execute this union operations: 7-3 9-1 6-2 6-7 5-4 8-0 7-1 8-4 5-6.
     * The result must be [8, 9, 6, 7, 5, 8, 6, 6, 6, 6]
     * 
     */
    @Test
    public void examQuestion2Test() {
        
        WeightedQuickUnionPathCompression quickUnionAlgorithm = new WeightedQuickUnionPathCompression(10);
        
        quickUnionAlgorithm.union(7, 3);
        quickUnionAlgorithm.union(9, 1);
        quickUnionAlgorithm.union(6, 2);
        quickUnionAlgorithm.union(6, 7);
        quickUnionAlgorithm.union(5, 4);
        quickUnionAlgorithm.union(8, 0);
        quickUnionAlgorithm.union(7, 1);
        quickUnionAlgorithm.union(8, 4);
        quickUnionAlgorithm.union(5, 6);
        
        assertThat(quickUnionAlgorithm.getTree()).containsExactly(8, 9, 6, 7, 5, 8, 6, 6, 6, 6);
        assertThat(quickUnionAlgorithm.getTreesSize()).containsExactly(1, 1, 1, 1, 1, 2, 10, 2, 4, 2);
    }
}
