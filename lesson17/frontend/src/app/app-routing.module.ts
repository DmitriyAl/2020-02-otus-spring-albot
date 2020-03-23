import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {OrdersComponent} from "./orders/orders.component";
import {ProductsComponent} from "./products/products.component";
import {HomeComponent} from "./main-layout/home.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'orders', component: OrdersComponent},
  {path: 'products', component: ProductsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
