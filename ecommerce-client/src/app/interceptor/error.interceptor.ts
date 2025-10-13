import { HttpInterceptorFn, HttpRequest, HttpHandlerFn, HttpEvent } from '@angular/common/http';
import { inject } from '@angular/core';
import { MessageService } from '../services/message.service';
import { catchError, throwError, Observable } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req: HttpRequest<any>, next: HttpHandlerFn): Observable<HttpEvent<any>> => {
  const messageService = inject(MessageService);

  return next(req).pipe(
    catchError(err => {

      let errorMessage = 'Ocurrio un error en el servidor';

      if (err.error && err.error.message) {
        errorMessage = err.error.message;
      } else if (err.status === 401) {
        errorMessage = 'No autorizado. Revisa tus credenciales';
      } else if (err.status === 403) {
        errorMessage = 'Acceso prohibido';
      }

      messageService.error(errorMessage);
      return throwError(() => err);
    })
  );
};
