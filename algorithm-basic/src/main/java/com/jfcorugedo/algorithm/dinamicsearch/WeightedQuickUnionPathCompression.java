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
	
	/** Maintains the size of each tree */
	private int[] treesSize;
	
	/**
	 * Initializes this algorithm to handle a set of object of this specific size
	 * @param size
	 */
	public WeightedQuickUnionPathCompression(int size) {
		
		tree = IntStream.range(0, size).toArray();
		treesSize = Arrays.stream(new int[size]).map(value -> 1).toArray();
	}
	
	@Override
	public void union(int p, int q) {
		
	    if(p == q){
            //Both elements are the same, and this structure is reflexive: 
            //each element is always connected to each self
            return;
        }
	    
		int rootP = findRoot(p);
		int rootQ = findRoot(q);
		
		if(rootP != rootQ) {
			if(treesSize[rootP] < treesSize[rootQ]) {
			    tree[rootP] = rootQ;
                treesSize[rootQ] += treesSize[rootP];
			} else {
			    tree[rootQ] = rootP;
                treesSize[rootP] += treesSize[rootQ];
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
	
	/**
     * This method should be used only for testing  
     * @return the array containing the parent of each node
     */
    protected int[] getTree() {
        return tree;
    }
    
    /**
     * This method should be used only for testing  
     * @return the array containing the size of each tree
     */
    protected int[] getTreesSize() {
        return treesSize;
    }
}
