package ex02;

public class UsersArrayList implements UsersList{
    private User[] arrUser = new User[10];
    private Integer size = 10;
    private Integer count = 0;

    @Override
    public void addUser(User user) {
        if (count < size - 1){
            arrUser[count] = user;
            count++;
        }
        else{
            this.arrUser = copyArr(arrUser);
            arrUser[count] = user;
            count++;
        }
    }

    public User[] copyArr(User[] arr){
        Integer newSize = size + (size / 2);

        if (size % 2 != 0)
            newSize++;
        this.size = newSize;

        User[] tmpArr = new User[size];
        for (int i = 0; i < arr.length; i++)
            tmpArr[i] = arr[i];
        return  tmpArr;
    }

    @Override
    public User retrieveById(Integer id) throws Exception, UserNotFoundException {
        for (int i = 0; i < count; i++){
            if (arrUser[i].getIdentifier() == id)
                return arrUser[i];
        }
        throw new UserNotFoundException();
    }

    @Override
    public User retrieveByIndex(Integer index) {
        if (index >= count || index < 0)
            throw new UserNotFoundException();
        return arrUser[index];
    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < count; i++){
            result += arrUser[i].getName() + " with id " + arrUser[i].getIdentifier() + "\n";
        }
        return "Array size = " + this.size + "\n Count = " + this.count + "\nAnd Contains:\n" + result;
    }
}
