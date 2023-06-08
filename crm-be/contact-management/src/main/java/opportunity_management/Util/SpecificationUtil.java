package opportunity_management.Util;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtil {
    public static Specification getSpecificationFromSearchField(String searchWord, String searchField){
        return (root, query, cb)-> cb.like(root.get(searchField),"%"+searchWord+"%");
    }
}
