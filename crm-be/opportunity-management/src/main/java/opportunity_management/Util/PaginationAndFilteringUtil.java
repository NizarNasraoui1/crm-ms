package opportunity_management.Util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class PaginationAndFilteringUtil {
    public static PageRequest getPaginationRequest(int page, int size,String sortField, String sortDirection){
        if(sortField!=null){
            if(sortDirection.equals("ASC")){
                return PageRequest.of(page,size,Sort.Direction.ASC,sortField);
            }
            else{
                return PageRequest.of(page,size,Sort.Direction.DESC,sortField);
            }
        }
        return PageRequest.of(page,size, Sort.Direction.DESC,"modifyDate");
    }
}
