package DAO;

import java.util.List;

public interface iDAO<T> {
	public boolean hasID(int id);
	public int insert(T a);
	public int delete(T a);
	public int update(T a);
	public List<T> findAll();
	public T findByID(int id);
}
