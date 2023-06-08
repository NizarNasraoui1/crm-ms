import { SearchFields } from "./searchFields";

export class SearchForm{
    searchWord:string;
    searchFields:string[];
    sortField:string;
    sortDirection:string;

    constructor(searchWord:string,searchFields:string[],sortField:string,sortDirection:string){
        this.searchWord=searchWord;
        this.searchFields=searchFields;
        this.sortField=sortField;
        this.sortDirection=sortDirection;
    }
}