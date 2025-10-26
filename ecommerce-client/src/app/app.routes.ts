import { Routes } from '@angular/router';
import {RegisterComponent} from "./components/user/register/register.component";
import {LoginComponent} from "./components/user/login/login.component";
import {ModeratorHomeComponent} from "./components/roles/moderator-home/moderator-home.component";
import {AdminHomeComponent} from "./components/roles/admin-home/admin-home.component";
import {LogisticHomeComponent} from "./components/roles/logistic-home/logistic-home.component";
import {CommonHomeComponent} from "./components/roles/common-home/common-home.component";
import {LogoutComponent} from "./components/user/logout/logout.component";
import {NewProductComponent} from "./components/product/new-product/new-product.component";
import {CreateUserComponent} from "./components/user/create-user/create-user.component";
import ApproveProductComponent from "./components/product/approve-product/approve-product.component";
import {DisplayProductComponent} from "./components/product/display-product/display-product.component";
import {ShoppingCartComponent} from "./components/shoppig-cart/shopping-cart.component";
import {PayShoppingCartComponent} from "./components/pay-shopping-cart/pay-shopping-cart.component";
import {
  ApproveDeliveryPackageComponent
} from "./components/delivery-package/approve-delivery-package/approve-delivery-package.component";
import {TopSellingProductsComponent} from "./components/product/top-selling-products/top-selling-products.component";
import {UserTopEarningComponent} from "./components/user/user-top-spent/user-top-earning.component";
import {
  UserTopPackagesOrderedComponent
} from "./components/user/user-top-packages-ordered/user-top-packages-ordered.component";
import {
  UserTopProductsApproveComponent
} from "./components/user/user-top-products-approve/user-top-products-approve.component";
import {
  NoRevisedDeliveryPackagesComponent
} from "./components/delivery-package/no-revised-delivery-packages/no-revised-delivery-packages.component";
import {
  DisplayPackageDetailsComponent
} from "./components/delivery-package/display-package-details/display-package-details.component";
import {AllUsersComponent} from "./components/user/all-users/all-users.component";
import {ShowNotificationsComponent} from "./components/show-notifications/show-notifications.component";
import {ShowSanctionsComponent} from "./components/show-sanctions/show-sanctions.component";
import {UpdateUserComponent} from "./components/user/update-user/update-user.component";
import {
  ProductsCommonSellingComponent
} from "./components/product/products-common-selling/products-common-selling.component";
import {UpdateProductComponent} from "./components/product/update-product/update-product.component";

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'logout',
    component: LogoutComponent
  },
  // MODERADOR
  {
    path: 'moderator/home',
    component: ModeratorHomeComponent,
  },
  {
    path: 'moderator/product/approve',
    component: ApproveProductComponent
  },
  {
    path: 'packages/no-revised',
    component: NoRevisedDeliveryPackagesComponent
  },
  // ADMINISTRADOR
  {
    path: 'admin/home',
    component: AdminHomeComponent,
  },
  {
    path: 'admin/user/new',
    component: CreateUserComponent
  },
  {
    path: 'product/top/selling',
    component: TopSellingProductsComponent
  },
  {
    path: 'user/top/products-send',
    component: TopSellingProductsComponent,
  },
  {
    path: 'user/top/earning',
    component: UserTopEarningComponent
  },
  {
    path: 'user/top/packages-ordered',
    component: UserTopPackagesOrderedComponent
  },
  {
    path: 'user/top/products-approve',
    component: UserTopProductsApproveComponent
  },
  {
    path: 'shopping-cart/display/:id',
    component: DisplayPackageDetailsComponent
  },
  {
    path: 'users/all',
    component: AllUsersComponent
  },
  {
    path: 'notification/:id',
    component: ShowNotificationsComponent,
  },
  {
    path: 'sanction/:id',
    component: ShowSanctionsComponent
  },
  {
    path: 'update-user/:id',
    component: UpdateUserComponent,
  },
  // LOGISTICA
  {
    path: 'logistic/home',
    component: LogisticHomeComponent,
  },
  {
    path: 'logistic/delivery-package',
    component: ApproveDeliveryPackageComponent
  },
  // COMUN
  {
    path: 'common/home',
    component: CommonHomeComponent,
  },
  {
    path: 'product/display/:id',
    component: DisplayProductComponent,
  },
  {
    path: 'shopping-cart',
    component: ShoppingCartComponent,
  },
  {
    path: 'pay-cart',
    component: PayShoppingCartComponent
  },
  {
    path: 'product/selling',
    component: ProductsCommonSellingComponent
  },
  {
    path: 'update-product/:id',
    component: UpdateProductComponent,
  },
  // PRODUCTOS
  {
    path: 'product/new',
    component: NewProductComponent,
  },
  {
    path: 'common/shopping-cart',
    component: ShoppingCartComponent
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
