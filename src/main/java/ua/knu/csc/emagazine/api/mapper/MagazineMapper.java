package ua.knu.csc.emagazine.api.mapper;

import org.springframework.stereotype.Component;
import ua.knu.csc.emagazine.api.dto.magazine.CreateMagazineDTO;
import ua.knu.csc.emagazine.api.dto.magazine.MagazineDTO;
import ua.knu.csc.emagazine.domain.category.Category;
import ua.knu.csc.emagazine.domain.magazine.Magazine;

@Component
public class MagazineMapper {

    public MagazineDTO toDTO(Magazine magazine) {
        return new MagazineDTO(magazine.getId(), magazine.getName(),
            magazine.getCategory().getValue());
    }

    public Magazine toEntity(CreateMagazineDTO magazineDTO) {
        return new Magazine(null, magazineDTO.getName(),
            new Category(magazineDTO.getCategoryId(), null), null);
    }
}
