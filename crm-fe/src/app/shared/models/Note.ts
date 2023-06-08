export class Note{
    id:number;
    title:string;
    content:string;

    constructor(id?:number,title?:string,content?:string){
        if(id) this.id=id;
        if(title) this.title=title;
        if(content) this.content=content;
    }
}