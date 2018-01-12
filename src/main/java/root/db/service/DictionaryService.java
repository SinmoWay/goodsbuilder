package root.db.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.db.dao.DictionaryDAO;
import root.db.dao.DictionaryValueDAO;
import root.db.dto.DictionaryValueDTO;
import root.db.entity.DictionaryEntity;
import root.db.entity.DictionaryValueEntity;
import root.db.type.DictionaryType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DictionaryService extends AbstractService<DictionaryValueDAO> {

    private final DictionaryDAO dictionaryDao;

    @Autowired
    public DictionaryService(DictionaryDAO dictionaryDao, DictionaryValueDAO dictionaryValueDao) {
        super(dictionaryValueDao);
        this.dictionaryDao = dictionaryDao;
    }

    @Transactional
    public DictionaryEntity getDictionary(DictionaryType type) {
        return dictionaryDao.getByName(type);
    }

    @Transactional
    public List<DictionaryValueDTO> getAllValuesByDictionaryType(DictionaryType dictionary) {
        DictionaryEntity dictionaryEntity = dictionaryDao.getByName(dictionary);
        Hibernate.initialize(dictionaryEntity.getValues());
        return dictionaryEntity.getValues().stream()
                .filter(Objects::nonNull)
                .map(DictionaryValueDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveOrUpdate(DictionaryValueDTO dto) {
        if (dto.getNodeText() == null || dto.getNodeText().isEmpty()) {
            return;
        }

        DictionaryValueEntity entity;

        if (dto.getId() == null) {
            entity = dao.getByDictionaryAndValue(dto.getDictionary().getName(), dto.getNodeText());
            if (entity != null)
                return;

            entity = new DictionaryValueEntity();
            entity.setDictionary(dto.getDictionary());
        } else {
            entity = dao.get(dto.getId());
        }

        entity.setValue(dto.getNodeText());
        dao.save(entity);
    }

    @Transactional
    public boolean canDelete(Integer id, DictionaryType type) {
        String tableName = null;
        if (type == DictionaryType.CONTENT_NAME) {
            tableName = "Content";
        } else if (type == DictionaryType.FABRICATOR_NAME) {
            tableName = "Fabricator";
        }

        return id != null && id >= 0 && tableName != null && dao.canDelete(tableName, id);
    }

}