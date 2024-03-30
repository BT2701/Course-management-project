package BUS;

import java.util.List;

public interface iBUS<T> {
	public String insert(T a);
	public String delete(T a);
	public String update(T a);
	public List<T> findAll();
	public T findById(int id);
}
