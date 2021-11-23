package domain.entity;

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
    private String image;

    public User() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        public UserBuilder setImage(String image)
        {
            user.setImage(image);
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
        resultList.add("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");
        return resultList;
    }
}
