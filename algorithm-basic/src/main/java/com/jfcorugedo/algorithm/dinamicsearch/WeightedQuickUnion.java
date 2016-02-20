package com.jfcorugedo.algorithm.dinamicsearch;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * <p>
 * This is a implementation of quick-union algorithm using an improvement called Weighted
 * </p>
 * 
 * <p>
 * Whenever a connected componets (trees) must be merged into another, it takes into account
 * the number of object of each tree, so always the smaller tree will be linked to the larger tree
 * </p>
 * <p>
 * It tries to avoid very tall trees to improve performance
 * </p>
 * <p>
 * <img src="http://algs4.cs.princeton.edu/15uf/images/weighted-quick-union-overview.png" />
 * </p>
 * 
 * <p>
 * This approach adds some overhead to union method but it drastically improves the performance of
 * find method, because the depth of any node x is at most log2 N.
 * </p>
 * 
 * <ul>
 * <li>The size of the tree containing x at least doubles since |T 2| ≥ |T 1|.</li>
 * <li>Size of tree containing x can double at most lg N times</li>
 * </ul>
 * Because 2^(log2 N) * 1 = N that the max number of nodes in the tree
 * 
 * <p>
 * <b>Cost model</b>: number of array accesses (for read or write)
 * <table>
 * 		<tr><th>algorithm</th><th>initialize</th><th>union</th><th>find</th></tr>
 * 		<tr><td>weighted q-u</td><td>N</td><td>log2N</td><td>log2N</td></tr>
 * </table>
 * </p>
 * @author jfcorugedo
 *
 */
public class WeightedQuickUnion implements DynamicConnectivity {

	private int[] tree;
	
	/** Mantains the size of each tree */
	private int[] treesSize;
	
	/**
	 * Initializes this algorithm to handle a set of object of this specific size
	 * @param size
	 */
	public WeightedQuickUnion(int size) {
		
		tree = IntStream.range(0, size).toArray();
		treesSize = new int[size];
		Arrays.stream(treesSize).map(value -> 1).toArray();
	}
	
	@Override
	public void union(int p, int q) {
		
		int rootP = findRoot(p);
		int rootQ = findRoot(q);
		
		if(rootP != rootQ) {
			if(treesSize[rootP] > treesSize[rootQ]) {
				tree[rootQ] = rootP;
				treesSize[rootP] += treesSize[rootQ];
			} else {
				tree[rootP] = rootQ;
				treesSize[rootQ] += treesSize[rootP];
			}
		}
	}

	@Override
	public boolean isConnected(int p, int q) {
		
		return findRoot(p) == findRoot(q);
	}

	/**
	 * Finds the root of the given object
	 * @return
	 */
	protected int findRoot(int element){
		
		int currentElement = element;
		//A root element is connected to itself
		while(tree[currentElement] != currentElement) {
			currentElement = tree[currentElement];
		}
		
		return currentElement;
	}
}
