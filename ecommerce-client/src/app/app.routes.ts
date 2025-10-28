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
import {RoleGuardService} from "./services/role-guard/role-guard.service";
import {AppConfig} from "./config/app.constants";
import {
  DeliveryPackagesByUserComponent
} from "./components/delivery-package/delivery-packages-by-user/delivery-packages-by-user.component";

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
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.MODERATOR ]}
  },
  {
    path: 'moderator/product/approve',
    component: ApproveProductComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.MODERATOR ]}
  },
  {
    path: 'packages/no-revised',
    component: NoRevisedDeliveryPackagesComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.MODERATOR ]}
  },
  // ADMINISTRADOR
  {
    path: 'admin/home',
    component: AdminHomeComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  {
    path: 'admin/user/new',
    component: CreateUserComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  {
    path: 'product/top/selling',
    component: TopSellingProductsComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  {
    path: 'user/top/products-send',
    component: TopSellingProductsComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  {
    path: 'user/top/earning',
    component: UserTopEarningComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  {
    path: 'user/top/packages-ordered',
    component: UserTopPackagesOrderedComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  {
    path: 'user/top/products-approve',
    component: UserTopProductsApproveComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  {
    path: 'shopping-cart/display/:id',
    component: DisplayPackageDetailsComponent,
    canActivate: [ RoleGuardService ],
    data: {
      roles: [
      AppConfig.ROLES.ADMIN,
      AppConfig.ROLES.LOGISTIC,
      AppConfig.ROLES.MODERATOR,
      AppConfig.ROLES.COMMON
      ]}
  },
  {
    path: 'users/all',
    component: AllUsersComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  {
    path: 'notification/:id',
    component: ShowNotificationsComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  {
    path: 'sanction/:id',
    component: ShowSanctionsComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  {
    path: 'update-user/:id',
    component: UpdateUserComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.ADMIN ]}
  },
  // LOGISTICA
  {
    path: 'logistic/home',
    component: LogisticHomeComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.LOGISTIC ]}
  },
  {
    path: 'logistic/delivery-package',
    component: ApproveDeliveryPackageComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.LOGISTIC ]}
  },
  // COMUN
  {
    path: 'common/home',
    component: CommonHomeComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.COMMON ]}
  },
  {
    path: 'product/display/:id',
    component: DisplayProductComponent,
    canActivate: [ RoleGuardService ],
    data: {
      roles: [
        AppConfig.ROLES.ADMIN,
        AppConfig.ROLES.LOGISTIC,
        AppConfig.ROLES.MODERATOR,
        AppConfig.ROLES.COMMON
      ]}
  },
  {
    path: 'shopping-cart',
    component: ShoppingCartComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.COMMON ]}
  },
  {
    path: 'pay-cart',
    component: PayShoppingCartComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.COMMON ]}
  },
  {
    path: 'product/selling',
    component: ProductsCommonSellingComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.COMMON ]}
  },
  {
    path: 'delivery-package/user',
    component: DeliveryPackagesByUserComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.COMMON ]}
  },
  {
    path: 'update-product/:id',
    component: UpdateProductComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.COMMON ]}
  },
  // PRODUCTOS
  {
    path: 'product/new',
    component: NewProductComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.COMMON ]}
  },
  {
    path: 'common/shopping-cart',
    component: ShoppingCartComponent,
    canActivate: [ RoleGuardService ],
    data: { roles: [AppConfig.ROLES.COMMON ]}
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
