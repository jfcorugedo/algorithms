package com.jfcorugedo.algorithm.dinamicsearch;

import java.util.stream.IntStream;

/**
 * <p>
 * This implementation improves Quick-union algorithm applying the approach of path compression.
 * </p>
 * 
 * <p>
 * Just after computing the root of p, set the id of each examined node to point to that root.
 * </p>
 * 
 * <p>
 * Cost model: number of accesses (for read or write)
 * </p>
 * <img src="http://algs4.cs.princeton.edu/15uf/images/uf-performance.png" />
 * 
 * @author jfcorugedo
 *
 */
public class QuickUnionPathCompression implements DynamicConnectivity {

	private int[] tree;
	
	/**
	 * Initializes this algorithm to handle a set of object of this specific size
	 * @param size
	 */
	public QuickUnionPathCompression(int size) {
		
		tree = IntStream.range(0, size).toArray();
	}
	
	@Override
	public void union(int p, int q) {
		
		int rootP = findRoot(p);
		int rootQ = findRoot(q);
		
		tree[rootP] = rootQ;
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
			tree[currentElement] = tree[tree[currentElement]];
			currentElement = tree[currentElement];
		}
		
		return currentElement;
	}
}
