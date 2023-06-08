export class SaveRequest{
    type:string;
    content:any;
    constructor(type:string,content:string){
        this.type=type;
        this.content=content;
    }
}