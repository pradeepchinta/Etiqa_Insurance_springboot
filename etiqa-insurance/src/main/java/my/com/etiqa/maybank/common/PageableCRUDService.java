package my.com.etiqa.maybank.common;

import org.springframework.data.domain.Page;

public interface PageableCRUDService<Entity, ID> {

    Entity create(Entity e);

    Page<Entity> get(Integer page, Integer per_page, String search);

    Entity getById();

    Entity update(Entity e);

    boolean delete(ID i);

}
