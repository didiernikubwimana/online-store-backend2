package edu.miu.cs545.project.onlinestore.repository;

import edu.miu.cs545.project.onlinestore.domain.ShoppingCartLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartLineRepository extends CrudRepository<ShoppingCartLine,Long> {
    public void deleteById(Long cartLineId);
    public ShoppingCartLine save(ShoppingCartLine cartLine);

}
