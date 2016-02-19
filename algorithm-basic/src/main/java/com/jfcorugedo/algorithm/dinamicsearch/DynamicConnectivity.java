package com.jfcorugedo.algorithm.dinamicsearch;

/**
 * Algorithm that tries to solve the problems associated to Dynamic connectivity.
 * 
 * This problem has two different operations:
 * <ul>
 * 		<li>Union Command: connects two objects</li>
 * 		<li>Find/connected Query: connects two objects</li>
 * </ul>
 * 
 * The input is a sequence of pairs of integers, where each integer represents an object of some type 
 * and we are to interpret the pair p q as meaning p is connected to q. 
 * 
 * We assume that "is connected to" is an equivalence relation:
 * <p>
 * <ul>
 * 		<li>symmetric: If p is connected to q, then q is connected to p.</li>
 * 		<li>transitive: If p is connected to q and q is connected to r, then p is connected to r.</li>
 * 		<li>reflexive: p is connected to p.</li>
 * </ul>
 * </p>
 * 
 * Constraints:
 * <p>
 * <ul>
 * 		<li>There is a huge amount of objects</li>
 * 		<li>There is a huge amount of commands</li>
 * 		<li>Union and Find commands can be mixed</li>
 * </ul>
 * </p>
 * <p>
 * An example of some union operation over a set of ten objects:
 * </p>
 * <img src="http://algs4.cs.princeton.edu/15uf/images/dynamic-connectivity-tiny.png" />
 * 
 * @author jfcorugedo
 *
 */
public interface DynamicConnectivity {

	/**
	 * Connects two objects.
	 * 
	 * We assume that "is connected to" is an equivalence relation:
	 * <p>
	 * <ul>
	 * 		<li>symmetric: If p is connected to q, then q is connected to p.</li>
	 * 		<li>transitive: If p is connected to q and q is connected to r, then p is connected to r.</li>
	 * 		<li>reflexive: p is connected to p.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param p Object to be connected to q
	 * @param q Object to be connected to p
	 */
	void union(int p, int q);
	
	/**
	 * Returns true only if there's a path between p and q
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	boolean connected(int p, int q);
}
