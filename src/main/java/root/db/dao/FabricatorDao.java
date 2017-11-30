package root.db.dao;

import org.springframework.stereotype.Repository;
import root.db.entity.FabricatorEntity;

@Repository
public class FabricatorDao extends AbstractDAO<FabricatorEntity> {

    @Override
    protected Class<FabricatorEntity> getClazz() {
        return FabricatorEntity.class;
    }

}