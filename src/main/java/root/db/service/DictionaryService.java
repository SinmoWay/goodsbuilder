package root.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.db.dao.DictionaryValueDao;
import root.db.dto.DictionaryValueDTO;
import root.db.entity.DictionaryValueEntity;
import root.db.type.DictionaryType;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryValueDao dictionaryValueDao;

    @Transactional
    public List<DictionaryValueDTO> getAllValuesByDictionary(DictionaryType dictionary) {
        return dictionaryValueDao.getAllByDictionary(dictionary).stream()
                .filter(Objects::nonNull)
                .map(DictionaryValueDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveOrUpdate(DictionaryValueDTO dto) {
        DictionaryValueEntity entity;

        if (dto.getId() == null) {
            entity = new DictionaryValueEntity();
        } else {
            entity = dictionaryValueDao.get(dto.getId());
        }

        entity.setValue(dto.getNodeText());
        dictionaryValueDao.save(entity);
    }

}