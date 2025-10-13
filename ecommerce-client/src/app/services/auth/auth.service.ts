import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {User} from "../../entities/user";
import {jwtDecode} from "jwt-decode";
import {Router} from "@angular/router";
import {JwtToken} from "../../entities/jwt-token";
import {AppConfig} from "../../config/app.constants";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiURL: string = `${AppConfig.API_URL}/auth`

  private readonly TOKEN_KEY: string = "jwtToken";

  constructor(private http: HttpClient, private router: Router) {
  }

  login(credentials: { email: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiURL}/login`, credentials);
  }

  register(user: User): Observable<any> {
    return this.http.post(`${this.apiURL}/register`, user);
  }

  saveToken(token: string) {
    localStorage.setItem(this.TOKEN_KEY, token);
    this.redirectToHomeByRole()
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  clearToken() {
    localStorage.removeItem(this.TOKEN_KEY);
  }

  decodeToken(): JwtToken | null {
    const token = this.getToken();
    if (!token) return null;

    try {
      return jwtDecode<JwtToken>(token);
    } catch (error) {
      console.log('Error al decodificar el token' + error);
      return null;
    }
  }

  isTokenExpired(): boolean {
    const tokenDecoded = this.decodeToken();
    if (!tokenDecoded?.exp) return true;

    const now = Math.floor(Date.now() / 1000)
    return tokenDecoded.exp < now;
  }

  isLoggedIn(): boolean {
    const token = this.getToken();

    return !!token && !this.isTokenExpired();
  }

  redirectToHomeByRole() {
    const jwt: JwtToken | null = this.decodeToken();
    if (jwt) {

      console.log('El rol es: ' + jwt.role)

      switch (jwt.role) {
        case AppConfig.ROLES.ADMIN:
          this.router.navigate(['/admin/home']);
          break;
        case AppConfig.ROLES.LOGISTIC:
          this.router.navigate(['/logistic/home']);
          break;
        case AppConfig.ROLES.MODERATOR:
          this.router.navigate(['/moderator/home']);
          break;
        case AppConfig.ROLES.COMMON:
          this.router.navigate(['/common/home']);
          break;
        default:
          this.router.navigate(['/login']);
      }

    } else {
      this.router.navigate(['/login']);
    }
  }

}
