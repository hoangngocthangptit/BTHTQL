import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpserviceService } from './httpservice.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private  baseUrl = environment.BASE_URL;
  constructor(private http: HttpClient ,private httpService: HttpserviceService ) { }
  private _autoRefresh$ = new Subject();
  private httpOtions = {
    headers: new HttpHeaders({ 'content-type': 'application/json' })
  };
 public signUp(data: any) {
    return this.http.post(`${this.baseUrl}/registration`, data);
  }
  public signIn(data: any) {
    return this.http.post(`${this.baseUrl}/user/login`, data);
  }
  verifyUserByToken(token) {
    return this.http.post(`${this.baseUrl}/user/verify/`, token);
  }
  forgetPassword(data) {
    return this.http.post(`${this.baseUrl}/user/forgotpassword`, data);
  }
  public updatePassword(updatePassword: any, token: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/user/update/${token}`,
      updatePassword,
    );
  }
  public getAdress(): Observable<any> {
    return this.http.get(`${this.baseUrl}/${environment.GET_ADDRESS_BY_ADDRES}`, {headers: new HttpHeaders({token: localStorage.token})});
  }
  public addAdress(address: any) {
    return this.http
    .post(`${environment.BASE_URL}/${environment.ADD_ADDRESS}`, address, {headers: new HttpHeaders({token: localStorage.token})});
  }
  public updateAdress(address: any) {
    return this.http
    .put(`${environment.BASE_URL}/${environment.UPDATE_ADDRESS}`, address, {headers: new HttpHeaders({token: localStorage.token})});
   }
  public SearchUser(data:any){
    return this.http.get(`${environment.BASE_URL}/${environment.UserSearch}`,data)
  }
  public ActiveUser(userId: number) {
    return this.httpService
    // tslint:disable-next-line: max-line-length
    .get(`${environment.BASE_URL}/${environment.ActiveUser}/${userId}`, {})
    .pipe(
    );
  }
  public DeleteUser(userId: number){
    return this.httpService
    // tslint:disable-next-line: max-line-length
    .get(`${environment.BASE_URL}/${environment.DeleteUser}/${userId}`, {headers: new HttpHeaders({token: localStorage.token})})
    .pipe(
    );
  }
  public DetailUser(userId:number){
    return this.httpService
    // tslint:disable-next-line: max-line-length
    .get(`${environment.BASE_URL}/${environment.GetUsser}/${userId}`, {headers: new HttpHeaders({token: localStorage.token})})
    .pipe(
    );
  }

  public UpdatepassWord(userId:number,data:any){
    return this.http
    .put(`${environment.BASE_URL}/${environment.UpdatePassword}/${userId}`, data, {headers: new HttpHeaders({token: localStorage.token})});
   }
   public UpdateInfor(data:any){
    return this.http
    .put(`${environment.BASE_URL}/${environment.Updateinfor}`, data, {headers: new HttpHeaders({token: localStorage.token})});
   }

}
