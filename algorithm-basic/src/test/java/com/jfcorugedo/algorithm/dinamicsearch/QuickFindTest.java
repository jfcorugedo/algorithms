package com.jfcorugedo.algorithm.dinamicsearch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class QuickFindTest {

	@Test
	public void executeUnionOperationConnectsTwoElements() {
		
		QuickFind quickFindAlgorithm = new QuickFind(10);
		
		quickFindAlgorithm.union(0, 1);
		
		assertThat(quickFindAlgorithm.isConnected(0, 1)).isTrue();
	}
	
	@Test
	public void findUnconnnectedObjectsResturnsFalse() {
		
		QuickFind quickFindAlgorithm = new QuickFind(10);
		
		assertThat(quickFindAlgorithm.isConnected(0, 1)).isFalse();
	}
	
	@Test
	public void connectionsAreTransitive() {
		
		QuickFind quickFindAlgorithm = new QuickFind(10);
		
		/*
		 * Builds this graph:
		 * 
		 * 0   1 - 2   3 - 4
		 * |   |   |   |   |
		 * 5 - 6   7   8   9
		 */
		quickFindAlgorithm.union(0, 5);
		quickFindAlgorithm.union(5, 6);
		quickFindAlgorithm.union(6, 1);
		quickFindAlgorithm.union(1, 2);
		quickFindAlgorithm.union(2, 7);
		quickFindAlgorithm.union(9, 4);
		quickFindAlgorithm.union(4, 3);
		quickFindAlgorithm.union(3, 8);
		
		assertThat(quickFindAlgorithm.isConnected(0, 7)).isTrue();
	}
	
	@Test
	public void connectionsAreSymmetric() {
		
		QuickFind quickFindAlgorithm = new QuickFind(10);
		
		quickFindAlgorithm.union(0, 5);
		
		assertThat(quickFindAlgorithm.isConnected(0, 5)).isTrue();
		assertThat(quickFindAlgorithm.isConnected(5, 0)).isTrue();
	}
	
	@Test
	public void connectionsAreReflexive() {
		
		QuickFind quickFindAlgorithm = new QuickFind(10);
		
		for(int i = 0 ; i < 10 ; i++) {
			assertThat(quickFindAlgorithm.isConnected(i, i)).isTrue();	
		}		
	}
	
	@Test(timeout=1000)
	public void performanceTest() {
		
		QuickFind quickFindAlgorithm = new QuickFind(20000);
		
		for(int i = 1 ; i < 20000 ; i++) {
			quickFindAlgorithm.union(i, i-1);
			quickFindAlgorithm.isConnected(i, i-1);
		}		
	}
	
	@Test
	public void exercise1() {
		
		QuickFind quickfind = new QuickFind(10);
		
		quickfind.union(2, 8);
		quickfind.union(9, 4);
		quickfind.union(5, 7);
		quickfind.union(5, 1);
		quickfind.union(0, 5);
		quickfind.union(8, 0);
		
		assertThat(quickfind.getIds()).containsExactly(1,1,1,3,4,1,6,1,1,4);
	}
}
