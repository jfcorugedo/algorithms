package com.jfcorugedo.algorithm.dinamicsearch;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * <p>
 * This is a implementation of quick-union algorithm using an improvement called Weighted
 * </p>
 * 
 * <p>
 * Whenever a connected components (trees) must be merged into another, it takes into account
 * the number of object of each tree, so always the smaller tree will be linked to the larger one
 * </p>
 * <p>
 * It tries to avoid very tall trees to improve performance
 * </p>
 * <p>
 * <img src="http://algs4.cs.princeton.edu/15uf/images/weighted-quick-union-overview.png" />
 * </p>
 * 
 * <p>
 * When joining two trees of equal size, this weighted quick union convention is to
 * make the root of the second tree point to the root of the first tree. Also, this weighted
 * quick union algorithm performs union by size (number of nodes) -  not union by height -
 * and does not do path compression.
 * </p>
 * 
 * <p>
 * To implement this approach, there will be two different arrays, one stores the root of each segment
 * and the other one stores the length associated with each root. 
 * </p>
 * <p>
 * For example: This array represents five elements without any connection (each element is its own root)
 * </p>
 * <pre> 
 * tree=[0, 1, 2, 3, 4]
 * </pre>
 * <p>
 * And this other array represents the length of each tree. Because there's not connections, each tree has a length of one
 * </p>
 * <pre> 
 * treesSize=[1, 1, 1, 1, 1]
 * </pre>
 * <p>
 * After executing this operation: union(2,3), the root of the third element will be connected to the root of the
 * second element.
 * <p>
 * <pre>
 * tree=[0, 1, 2, 2, 4]
 * treesSize=[1, 1, 2, 1, 1]
 * </pre>
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
	
	/** Maintains the size of each tree*/
	private int[] treesSize;
	
	/**
	 * Initializes this algorithm to handle a set of object of this specific size
	 * @param size
	 */
	public WeightedQuickUnion(int size) {
		
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
