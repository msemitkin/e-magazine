package ua.knu.csc.emagazine.api.mapper;

import org.springframework.stereotype.Component;
import ua.knu.csc.emagazine.api.dto.keyword.CreateKeyWordDTO;
import ua.knu.csc.emagazine.api.dto.keyword.KeyWordDTO;
import ua.knu.csc.emagazine.domain.keyword.KeyWord;

@Component
public class KeyWordMapper {

    public KeyWordDTO toDTO(KeyWord keyWord) {
        return new KeyWordDTO(keyWord.getId(), keyWord.getValue());
    }

    public KeyWord toEntity(CreateKeyWordDTO keyWordDTO) {
        return new KeyWord(null, keyWordDTO.getValue());
    }

}
