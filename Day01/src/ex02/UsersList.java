package ex02;

public interface UsersList {
    public void addUser(User user);
    public User retrieveById(Integer id) throws Exception, UserNotFoundException;
    public User retrieveByIndex(Integer index);
    public Integer getCount();
}
