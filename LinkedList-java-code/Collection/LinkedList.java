package collections;
// Singly, Double, Circular and Double Circular

public interface LinkedList<K> {
	public void addNode(K data);	
	public void deleteNode(K data);
	public int sizeOf();
	public K find();
	public void replace(K olddata, K newdata);
}
