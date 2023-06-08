import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AppLayoutModule } from './layout/app.layout.module';
import { NotfoundComponent } from './features/notfound/notfound.component'
import { authInterceptorProviders } from './core/_helpers/auth.interceptor';
import { AuthService } from './core/_services/auth.service';
import { HttpUtilService } from './util/service/http-util.service';
import { SharedModule } from './shared/shared.module';
import { MessageService } from 'primeng/api';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OpportunitiesModule } from './features/opportunities/opportunities.module';

@NgModule({
    declarations: [
        AppComponent, NotfoundComponent
    ],
    imports: [
        AppRoutingModule,
        AppLayoutModule,
        SharedModule,
        BrowserAnimationsModule,
        OpportunitiesModule

    ],
    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        authInterceptorProviders,AuthService,HttpUtilService,MessageService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
