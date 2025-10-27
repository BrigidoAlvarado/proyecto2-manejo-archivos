import { HttpInterceptorFn} from "@angular/common/http";
import {inject} from "@angular/core";
import {AuthService} from "../services/auth/auth.service";

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const authService = inject(AuthService)
  const token = authService.getToken()
  const isAuthRequest:boolean = req.url.includes('/auth/')

  let cloned = req.clone({
    setHeaders:{
      "ngrok-skip-browser-warning": 'true',
    }
  })

  if ( isAuthRequest ) {
    return next(cloned)
  }


  if (token) {
    cloned = cloned.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    })
    return next(cloned)
  }


  return next(req)
}
