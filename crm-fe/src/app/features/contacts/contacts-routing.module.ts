import { ContactTabsComponent } from './containers/contact-tabs/contact-tabs.component';
import { ContactComponent } from './containers/contact/contact.component';
import { ContactDetailsComponent } from './components/contact-details/contact-details.component';
import { ContactListComponent } from './components/contact-list/contact-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddContactComponent } from './components/add-contact/add-contact.component';

const routes: Routes = [
    {path:'',component:ContactComponent,
    children:[
        {path:'',component:ContactListComponent},
        {path:'add',component:AddContactComponent},
        {path:'details/:id',component:ContactTabsComponent}
]
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContactsRoutingModule { }
