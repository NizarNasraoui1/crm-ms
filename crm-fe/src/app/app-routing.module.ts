import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppLayoutComponent } from "./layout/app.layout.component";
import { NotfoundComponent } from './features/notfound/notfound.component';
import { AuthGardService as AuthGard}  from './core/_services/auth-gard.service';

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: AppLayoutComponent,
                children: [
                    { path: '', loadChildren: () => import('./features/dashboard/dashboard.module').then(m => m.DashboardModule) },
                    {path: 'contact',loadChildren: ()=> import('../app/features/contacts/contacts.module').then(m => m.ContactsModule)},
                    {path: 'opportunities',loadChildren: () => import('./features/opportunities/opportunities.module').then(m => m.OpportunitiesModule)},
                ],
                canActivate:[AuthGard]

            },
            { path: 'auth', loadChildren: () => import('./core/auth/auth.module').then(m => m.AuthModule) },
            { path: 'pages/notfound', component: NotfoundComponent },
            { path: '**', redirectTo: 'pages/notfound' },
        ], { scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
