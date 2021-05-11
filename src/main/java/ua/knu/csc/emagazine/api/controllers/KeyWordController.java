package ua.knu.csc.emagazine.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.knu.csc.emagazine.api.dto.keyword.CreateKeyWordDTO;
import ua.knu.csc.emagazine.api.dto.keyword.KeyWordDTO;
import ua.knu.csc.emagazine.api.mapper.KeyWordMapper;
import ua.knu.csc.emagazine.domain.keyword.KeyWord;
import ua.knu.csc.emagazine.domain.keyword.KeyWordService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class KeyWordController {

    private final KeyWordService keyWordService;
    private final KeyWordMapper keyWordMapper;

    public KeyWordController(
        KeyWordService keyWordService,
        KeyWordMapper keyWordMapper
    ) {
        this.keyWordService = keyWordService;
        this.keyWordMapper = keyWordMapper;
    }

    @GetMapping("/api/keywords/")
    public List<KeyWordDTO> getAll() {
        return keyWordService.findAll().stream()
            .map(keyWordMapper::toDTO)
            .collect(Collectors.toList());
    }

    @PostMapping("/api/keywords/")
    public KeyWordDTO save(@Valid CreateKeyWordDTO keyWordDTO) {
        KeyWord keyWord = keyWordMapper.toEntity(keyWordDTO);
        KeyWord saved = keyWordService.save(keyWord);
        return keyWordMapper.toDTO(saved);
    }
}
