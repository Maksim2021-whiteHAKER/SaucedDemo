package utils;

public class UserFactory {
    public static User withStandardUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.standardUser"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }

    public static User withLockedUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.lockedUser"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }

    public static User withProblemUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.problemUser"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }

    public static User withPerformanceGlitchUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.performanceGlitchUser"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }

    public static User withErrorUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.errorUser"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }

    public static User withVisualUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.visualUser"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }

    public static User withWrongLoginUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.wrongLogin"),
                PropertyReader.getProperty("saucedemo.password")
        );
    }

    public static User withWrongPasswordUser() {
        return new User(
                PropertyReader.getProperty("saucedemo.standardUser"),
                PropertyReader.getProperty("saucedemo.wrongPassword")
        );
    }
}
