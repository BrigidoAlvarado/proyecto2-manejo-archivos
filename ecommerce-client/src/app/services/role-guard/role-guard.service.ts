import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync,
  Router,
  RouterStateSnapshot
} from "@angular/router";
import {AuthService} from "../auth/auth.service";
import {MessageService} from "../message.service";

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService implements CanActivate {

  constructor(
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService,
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    const token = this.authService.decodeToken();

    if (!token) {
      this.messageService.error('Primero debe iniciar sesion')
      this.router.navigate(['**']);
      return false;
    }

    const allowedRoles = route.data['roles'] as string[];
    const userRole = token.role;

    if (allowedRoles.includes(userRole)) {
      return true;
    }

    this.messageService.error('No tiene acceso a esta ruta')
    this.router.navigate(['**']);
    return false;

  }
}
