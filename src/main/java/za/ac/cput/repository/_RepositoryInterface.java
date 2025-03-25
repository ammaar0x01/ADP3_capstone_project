package za.ac.cput.repository;

public interface _RepositoryInterface<ClassType, Id>{
    public ClassType create(ClassType obj);

    public ClassType read(Id id);

//    public ClassType update(ClassType obj);

    public ClassType update(Id id, ClassType obj);

    //    public ClassType delete(Id id);
    public boolean delete(Id id);

    // public ClassType getAll();
}
