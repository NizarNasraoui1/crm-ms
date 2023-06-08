export class PageRequestParams{
    page!:number;
    pageSize!:number;
    sortDirection!:string;
    sortField!:string;
    searchWord!:string;

    public constructor(page:number,pageSize:number,searchWord:string="",sortDirection?:string,sortField?:string){
        this.page=page;
        this.pageSize=pageSize;
        this.searchWord=searchWord;
        if(sortDirection && sortField){
            this.sortDirection=sortDirection;
            this.sortField=sortField;
        }
    }
}
