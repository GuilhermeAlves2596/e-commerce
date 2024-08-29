import { Injectable } from '@angular/core';
import { environment } from '../../enviroment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiLogin = `${environment.apiLogin}`;

constructor(private http: HttpClient) { }

  login(credenciais: {login: string, senha: string}): Observable<any> {
    return this.http.post<any>(this.apiLogin, credenciais);
  } 
}
