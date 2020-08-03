import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./page/home/home.component";
import {ProductComponent} from "./page/product/product.component";
import {ProductsComponent} from "./page/products/products.component";
import {LoginComponent} from "./page/login/login.component";
import {AuthGuardService} from "./security/auth-guard.service";


const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuardService]},
  {path: 'login', component: LoginComponent},
  {path: 'products', component: ProductsComponent, canActivate: [AuthGuardService]},
  {path: 'products/:id', component: ProductComponent, canActivate: [AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
