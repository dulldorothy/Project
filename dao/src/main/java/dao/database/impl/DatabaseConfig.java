package dao.database.impl;

public class DatabaseConfig {
    private static String databaseURL = "jdbc:postgresql://localhost:5432/project";
    private static String login = "postgres";
    private static String password = "root";

    public static String getDatabaseURL() {
        return databaseURL;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }
}
