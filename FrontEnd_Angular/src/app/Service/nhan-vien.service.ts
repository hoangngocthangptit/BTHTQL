import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NhanVienService {
  constructor(private _http: HttpClient) {}
  addEmployee(data: any): Observable<any> {
    return this._http.post('http://localhost:8080/nhan-vien', data);
  }
  updateEmployee(id: number, data: any): Observable<any> {
    return this._http.put(`http://localhost:8080/nhan-vien/${id}`, data);
  }
  getEmployeeList(): Observable<any> {
    return this._http.get('http://localhost:8080/nhan-vien');
  }
  deleteEmployee(id: number): Observable<any> {
    return this._http.delete(`http://localhost:8080/nhan-vien/delete/${id}`);
  }
  listLuong(time:number): Observable<any> {
    return this._http.get(`http://localhost:8080/phieu-luong/findTime/${time}`);
  }
  chamCong(): Observable<any> {
    return this._http.get('http://localhost:8080/cham-cong');
  }
  doanhThuThang(): Observable<any> {
    return this._http.get('http://localhost:8080/phieu-luong/doanhThuThang');
  }
  doanhThuNam(): Observable<any> {
    return this._http.get('http://localhost:8080/phieu-luong/doanhThuTheoNam');
  }
  chucVu(): Observable<any> {
    return this._http.get('http://localhost:8080/chuc-vu');
  }
  khen(): Observable<any> {
    return this._http.get('http://localhost:8080/khen-phat/khen-thuong');
  }
  phat(): Observable<any> {
    return this._http.get('http://localhost:8080/khen-phat/ky-luat');
  }
}
