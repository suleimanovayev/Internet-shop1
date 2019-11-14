package mate.academy.internetshop3.dao;

import mate.academy.internetshop3.model.Role;

public interface RoleDao {
    Role get(Role.RoleName roleName);
}
