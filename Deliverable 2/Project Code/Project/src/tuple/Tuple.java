package tuple;

public class Tuple<T> {
	private T item1 = null;
	private T item2 = null;
	private T item3 = null;
	
	public Tuple(T item1, T item2, T item3) {
		if (item1 == null || item2 == null || item3 == null) {
			throw new IllegalArgumentException();
		}
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
	}

	public T getItem1() {
		return this.item1;
	}
	
	public T getItem2() {
		return this.item2;
	}
	
	public T getItem3() {
		return this.item3;
	}
	
	public boolean equal(Tuple<T> other) {
		if (this.item1.equals(other.getItem1()) && this.item2.equals(other.getItem2()) && this.item3.equals(other.getItem3())) {
			return true;
		}
		return false;
	}
	
	public int hashCode() {
		return this.item1.hashCode() * this.item2.hashCode();
	}
	
	public String toString() {
		return "(" + this.item1 + ", " + this.item2 + ", " + this.item3 + ")";
	}
}
