import { Contact } from "../../contacts/models/contact";
import { OpportunityStageEnum } from "./opportunityStageEnum";

export interface Opportunity{
    id?:number,
    name?:string,
    stage?:OpportunityStageEnum,
    contacts?:Contact[];
    closeDate?:Date

}
