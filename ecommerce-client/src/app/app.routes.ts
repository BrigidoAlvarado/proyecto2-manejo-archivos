import { Routes } from '@angular/router';
import {RegisterComponent} from "./components/user/register/register.component";
import {LoginComponent} from "./components/user/login/login.component";
import {ModeratorHomeComponent} from "./components/moderator-home/moderator-home.component";
import {AdminHomeComponent} from "./components/admin-home/admin-home.component";
import {LogisticHomeComponent} from "./components/logistic-home/logistic-home.component";
import {CommonHomeComponent} from "./components/common-home/common-home.component";

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  // MODERADOR
  {
    path: 'moderator/home',
    component: ModeratorHomeComponent,
  },
  // ADMINISTRADOR
  {
    path: 'admin/home',
    component: AdminHomeComponent,
  },
  // LOGISTICA
  {
    path: 'logistic/home',
    component: LogisticHomeComponent,
  },
  // COMUN
  {
    path: 'common/home',
    component: CommonHomeComponent,
  },
  // EXTRAS
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: '**',
    redirectTo: 'login',
  }
];
