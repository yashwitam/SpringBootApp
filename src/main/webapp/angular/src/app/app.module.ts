import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpModule} from '@angular/http';

import { AppComponent } from './app.component';
import { CustomerComponent } from './components/customer/customer.component';

@NgModule({
  declarations: [
    AppComponent,
    CustomerComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
