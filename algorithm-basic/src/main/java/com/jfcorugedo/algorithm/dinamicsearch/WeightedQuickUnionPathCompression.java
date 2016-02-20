package com.jfcorugedo.algorithm.dinamicsearch;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * <p>
 * This is an improvement of quick-union algorithm using two techniques: Weighted and path compression
 * </p>
 * 
 * <p>
 * Cost model: number of accesses (for read or write)
 * </p>
 * <img src="http://algs4.cs.princeton.edu/15uf/images/uf-performance.png" />
 * 
 * @see QuickUnion
 * @see QuickUnionPathCompression
 * @see WeightedQuickUnionPathCompression
 * @author jfcorugedo
 *
 */
public class WeightedQuickUnionPathCompression implements DynamicConnectivity {

	private int[] tree;
	
	/** Mantains the size of each tree */
	private int[] treesSize;
	
	/**
	 * Initializes this algorithm to handle a set of object of this specific size
	 * @param size
	 */
	public WeightedQuickUnionPathCompression(int size) {
		
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
			tree[currentElement] = tree[tree[currentElement]];
			currentElement = tree[currentElement];
		}
		
		return currentElement;
	}
}
