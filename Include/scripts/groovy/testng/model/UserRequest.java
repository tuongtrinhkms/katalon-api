package testng.model;

public class UserRequest {
    public String username;
    public String password;
    public int age;
    public String gender;
    public String avatar;

    public UserRequest(String username, String password, int age, String gender, String avatar) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.avatar = avatar;
    }
}