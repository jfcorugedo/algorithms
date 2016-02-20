package com.jfcorugedo.algorithm.dinamicsearch;

import java.util.stream.IntStream;

/**
 * <p>
 * This implementation tries to solve Dynamic connectivity problem using an approach that
 * strikes a balance between the complexity of union and find operations.
 * </p>
 * 
 * <p>
 * It holds a data structure of the same size of the number of objects represented. Each position
 * of this structure represents the parent of this specific object. So the parent of the object q
 * will be in tree[p].
 * </p>
 * <img src="http://algs4.cs.princeton.edu/15uf/images/quick-union-overview.png" />
 * <p>
 * Using this approach, union and find commands need to know the root of each specific object.
 * Two different objects are connected (are in the same connected component) if they share the same root.
 * To connect two objects, just link one of the roots to the other.   
 * </p>
 * <p>
 * A root element is connected to itself. So if tree[q] is equal to q then q is a root.
 * </p>
 * 
 * <p>
 * 	<b>Drawbacks:</b> Quick-union is better that quick find but it's also too slow. Its problems are that
 * trees can get tall and find command is too expensive (could be N array access to find the root)
 * </p>
 * 
 * <p>
 * Cost model: number of accesses (for read or write)
 * <table>
 * 		<tr><th>algorithm</th><th>initialize</th><th>union</th><th>find</th></tr>
 * 		<tr><td>quick-union</td><td>N</td><td>N</td><td>N (worst case)</td></tr>
 * </table>
 * </p>
 * @author jfcorugedo
 *
 */
public class QuickUnion implements DynamicConnectivity {

	private int[] tree;
	
	/**
	 * Initializes this algorithm to handle a set of object of this specific size
	 * @param size
	 */
	public QuickUnion(int size) {
		
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
			currentElement = tree[currentElement];
		}
		
		return currentElement;
	}
}
