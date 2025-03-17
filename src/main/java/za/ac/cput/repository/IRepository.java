package za.ac.cput.repository;

public interface IRepository<T, ID> {

    public void create(T t);
    public void read(ID id);
    public void update(ID id);
    public void delete(ID id);
    T del(ID id); ///returning an object type
}
