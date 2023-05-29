import { Injectable } from '@angular/core';
import { HttpserviceService } from './httpservice.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable, Subject } from 'rxjs';
import { tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class BookserviceService {
  private  baseUrl = environment.BASE_URL;
  private _autoRefresh$ = new Subject();
  private httpOtions = {
    headers: new HttpHeaders({ 'content-type': 'application/json' })
  };
  constructor(private http: HttpClient ,private httpService: HttpserviceService) { }

  public DeleteBook(bookId:number){
    return this.httpService
    // tslint:disable-next-line: max-line-length
    .delete(`${environment.BASE_URL}/${environment.Delete_book}/${bookId}`, {headers: new HttpHeaders({token: localStorage.token})})
    .pipe(
    );
  }
  public SearchBook(data:any){
    return this.http.get(`${environment.BASE_URL}/${environment.getAll_book}`,data)
  }
  public updateBook(bookId:number,book: any): Observable<any> {
    const  httpOptions = {
      headers: new HttpHeaders()
    }
    return this.httpService
    // tslint:disable-next-line: max-line-length
    .put(`${environment.BookUrl}/${environment.editBookView}/${bookId}`, book, {headers: new HttpHeaders({token: localStorage.token})})
    .pipe(
      tap(() => {
        this._autoRefresh$.next();
      })
    );
  }
  // public addBook(params:any,file: File): Observable<any>{

  // }
  public async  addBook(book: any): Promise<Observable<any>>{
    const  httpOptions = {
      headers: new HttpHeaders()
    }
    return this.httpService
    // tslint:disable-next-line: max-line-length
    .post('http://localhost:8080/book',book, httpOptions)
    .pipe(
      tap(() => {
        this._autoRefresh$.next();
      })
    );
    // return await this.httpService.callAPIUploadFilesAndData(environment.add_book_view, file, params);
  }
}

