import { Note } from "src/app/shared/models/Note";

export class Contact{
    id:number;
    firstName:string;
    lastName:string;
    address:string;
    email:string;
    noteList:Note[];

    constructor(id:number){
        this.id=id;
    }
}
