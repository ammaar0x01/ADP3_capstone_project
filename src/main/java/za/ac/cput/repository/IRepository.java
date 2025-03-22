package za.ac.cput.repository;

public interface IRepository<T, ID> {

    public void create(T t);
    public void read(ID roomID);
    public void update(ID roomID);public void delete(ID roomID);
    T del(ID id);

    ///returning an object type
}
