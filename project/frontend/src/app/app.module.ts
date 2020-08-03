import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './header/header.component';
import {HomeComponent} from './page/home/home.component';
import {OrdersComponent} from './component/orders/orders.component';
import {ProductsListComponent} from './component/products-list/products-list.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {ProductCardComponent} from './component/product-card/product-card.component';
import {ProductComponent} from './page/product/product.component';
import {ProductsComponent} from './page/products/products.component';
import {NotifierModule} from "angular-notifier";
import {notifierOptions} from "./utils/notifier-options";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { LoginComponent } from './page/login/login.component';
import {BasicAuthInterceptorService} from "./security/basic-auth-interceptor.service";

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    OrdersComponent,
    ProductsListComponent,
    ProductCardComponent,
    ProductComponent,
    ProductsComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
        defaultLanguage: 'ru',
        loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
        }
      }
    ),
    NotifierModule.withConfig(notifierOptions),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptorService, multi: true
    }
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
