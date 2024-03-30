package DAO;

import java.util.ArrayList;

public interface DaoInterface<T> {
	public int add(T t);
	public int delete(T t);
	public int update(T t);
	public ArrayList<T> getList();
	public T getById(String id);
}
