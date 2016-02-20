package com.jfcorugedo.algorithm.dinamicsearch;

import java.util.stream.IntStream;

/**
 * <p>
 * This implementation tries to solve Dynamic connectivity problem using an approach that
 * makes find operations very easy but union operations a bit expensive.
 * </p>
 * 
 * <p>
 * The implementation uses an internal structure of the same size of the objects represented.
 * Each index represents an object, so object 1 is represented by the element id[1] of the structure.
 * </p>
 * Two elements, p and q, are connected if and only if id[p] is equal to id[q].
 * 
 * <p>
 * In other words, all objects whitin the same connected component will have the same value in its index.
 * </p>
 * 
 * <p>
 * <img src="http://algs4.cs.princeton.edu/15uf/images/quick-find-overview.png" />
 * </p>
 * 
 * <p>
 * The find operation it's quit simple: just compare the index of the two elements.
 * </p>
 * 
 * The union operation, on the other hand, is by far much more expensive. It must traverse all the array
 * looking for all the elements with the same id and change all of them to merge two connected components
 * 
 * <p>
 * <b>Drawbacks</b>: Quick find is too slow. Union computation is too expensive, it has a quadratic 
 * complexity: It takes N^2 array accesses to process a sequence of N union commands  on N objects.
 * </p>
 * 
 * <p>
 * <b>Cost model</b>: number of array accesses (for read or write)
 * <table>
 * 		<tr><th>algorithm</th><th>initialize</th><th>union</th><th>find</th></tr>
 * 		<tr><td>quick-find</td><td>N</td><td>N</td><td>1</td></tr>
 * </table>
 * </p>
 * 
 * <p>
 * 		<b>Quadratic algorithm do not scale</b>
 * 		<div>New computers may be 10x fast, but has 10x as much memory, so the problem to solve is 10x as big.</div>
 * </p>
 * 
 * @author jfcorugedo
 *
 */
public class QuickFind implements DynamicConnectivity{

	private int[] ids;
	
	/**
	 * Creates a UnionFind algorithm with an initial pool of objects from 0 to size-1
	 * 
	 * @param size
	 */
	public QuickFind(int size) {
		this.ids = IntStream.range(0, size).toArray();
	}
	
	@Override
	public void union(int p, int q) {
		
		int idP = ids[p];
		int idQ = ids[q];
		for(int i = 0 ; i < ids.length ; i++) {
			if(ids[i] == idP) {
				ids[i] = idQ;
			}
		}
	}

	@Override
	public boolean isConnected(int p, int q) {
		
		return ids[p] == ids[q];
	}
}
