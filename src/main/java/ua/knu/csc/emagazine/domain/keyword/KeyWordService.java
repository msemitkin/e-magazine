package ua.knu.csc.emagazine.domain.keyword;

import org.springframework.stereotype.Service;
import ua.knu.csc.emagazine.repository.KeyWordRepository;

import java.util.List;

@Service
public class KeyWordService {

    private final KeyWordRepository keyWordRepository;

    public KeyWordService(KeyWordRepository keyWordRepository) {
        this.keyWordRepository = keyWordRepository;
    }

    public List<KeyWord> findAll() {
        return keyWordRepository.findAll();
    }

    public KeyWord save(KeyWord keyWord) {
        return keyWordRepository.save(keyWord);
    }
}
