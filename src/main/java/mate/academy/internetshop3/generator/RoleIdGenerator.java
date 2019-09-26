package mate.academy.internetshop3.generator;

public class RoleIdGenerator {

    private static Long idGenerator = 0L;

    private RoleIdGenerator() {
    }

    public static Long getGeneratedId() {
        return idGenerator++;
    }
}

