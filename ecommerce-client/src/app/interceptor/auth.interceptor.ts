import { HttpInterceptorFn} from "@angular/common/http";
import {inject} from "@angular/core";
import {AuthService} from "../services/auth/auth.service";

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const authService = inject(AuthService)
  const token = authService.getToken()
  const isAuthRequest:boolean = req.url.includes('/auth/')

  let clonedReq = req.clone({
    setHeaders: {
      'ngrok-skip-browser-warning': 'true',
    }
  });

  if (isAuthRequest) {
    return next(clonedReq)
  }


  if (token) {
     clonedReq = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,

      },
    })
    return next(clonedReq)
  }

  return next(clonedReq)
}
