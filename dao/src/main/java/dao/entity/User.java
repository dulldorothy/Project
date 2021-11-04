package dao.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String userName;
    private String password;
    private String lastName;
    private String firstName;
    private String role;


    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, lastName, firstName, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public static class UserBuilder {
        User user;
        public UserBuilder(){
            user = new User();
        }

        public UserBuilder setID(int id)
        {
            user.setId(id);
            return this;
        }
        public UserBuilder setUsername(String username)
        {
            user.setUserName(username);
            return this;
        }
        public UserBuilder setLastname(String lastname)
        {
            user.setLastName(lastname);
            return this;
        }

        public UserBuilder setFirstname(String firstname) {
            user.setFirstName(firstname);
            return this;
        }
        public UserBuilder setRole(String role) {
            user.setRole(role);
            return this;
        }
        public UserBuilder setPassword(String password)
        {
            user.setPassword(password);
            return this;
        }
        public User create()
        {
            return user;
        }




    }

    private void setId(int id) {
        this.id = id;
    }


    public List<Object> userToListOfParameters() {
        List<Object> resultList = new ArrayList<>();
        resultList.add(userName);
        resultList.add(password);
        resultList.add(lastName);
        resultList.add(firstName);
        resultList.add(role);
        return resultList;
    }
}
