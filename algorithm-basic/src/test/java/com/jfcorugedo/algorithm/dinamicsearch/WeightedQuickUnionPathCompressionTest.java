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
}
