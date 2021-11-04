package dao.entity;

import java.util.Objects;

public class UserDTO {
    private int id;
    private String userName;
    private String lastName;
    private String firstName;
    private String role;

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && Objects.equals(userName, userDTO.userName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(role, userDTO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, lastName, firstName, role);
    }

    public static class UserDTOBuilder {
        UserDTO userDTO;

        public UserDTOBuilder() {
            userDTO = new UserDTO();
        }

        public UserDTOBuilder setID(int id) {
            userDTO.setId(id);
            return this;
        }

        public UserDTOBuilder setUsername(String username) {
            userDTO.setUserName(username);
            return this;
        }

        public UserDTOBuilder setLastname(String lastname) {
            userDTO.setLastName(lastname);
            return this;
        }

        public UserDTOBuilder setFirstname(String firstname) {
            userDTO.setFirstName(firstname);
            return this;
        }

        public UserDTOBuilder setRole(String role) {
            userDTO.setRole(role);
            return this;
        }

        public UserDTO create() {
            return userDTO;
        }


    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
