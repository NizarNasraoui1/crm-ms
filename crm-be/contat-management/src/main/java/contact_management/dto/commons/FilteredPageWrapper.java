package contact_management.dto.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilteredPageWrapper<T> {
    private int count;
    private Collection<T>results;
}
