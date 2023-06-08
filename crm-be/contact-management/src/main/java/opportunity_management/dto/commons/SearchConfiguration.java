package opportunity_management.dto.commons;

import opportunity_management.dto.ParamDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class SearchConfiguration {
    Set<ParamDto> sortFields;
    Set<ParamDto>searchFields;

    public SearchConfiguration(){
        this.searchFields=new HashSet<>();
        this.sortFields=new HashSet<>();
    }
}
