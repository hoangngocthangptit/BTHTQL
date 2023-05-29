import {Component, OnInit} from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import { DialalogDeleteComponent } from 'src/app/Component/dialalog-delete/dialalog-delete.component';
import { Title } from "@angular/platform-browser";
import { MatPaginator } from "@angular/material/paginator";
import { MatTableDataSource } from "@angular/material/table";
import { MatDialog } from "@angular/material/dialog";
import { ToastrService } from 'ngx-toastr';
import { UserService } from "src/app/Service/user.service";
import { BookserviceService } from "src/app/Service/bookservice.service";
import { Router } from "@angular/router";

import { log } from "console";
export interface PeriodicElement {
  ten: string;
  position: number;
  mucThuong: string;
  mieuTa: string;

}
const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, ten: 'svfd', mucThuong:'ffd', mieuTa: 'H'},
  {position: 2, ten: 'svfd', mucThuong:'ffd', mieuTa: 'H'},
  {position: 3, ten: 'svfd', mucThuong:'ffd', mieuTa: 'H'},
  {position: 4, ten: 'svfd', mucThuong:'ffd', mieuTa: 'H'},


];
@Component({
  selector: 'app-khen-thuong',
  templateUrl: './khen-thuong.component.html',
  styleUrls: ['./khen-thuong.component.scss']
})

export class KhenThuongComponent implements OnInit {
  constructor(    private titleService: Title,
    private dialog: MatDialog,
    private toastr: ToastrService,
    private bookService: BookserviceService,
    private route: Router,
    private matSnackBar: ToastrService,) { }

  ngOnInit(): void {
  }
  opened = true;
  public opened2 = false;
  isUser = false;
  isSeller = false;
  isAdmin = true;
  email:any;
  pageSize:number=10;
  page: number = 0;
  totalItems: number = 0;
  openEditForm(){}
  deleteDilag(id:number){
    if (localStorage.getItem('token') === null) {
      this.matSnackBar.warning('Please Login first');
      this.route.navigateByUrl('login');
      return;
    }
    const dialogRef = this.dialog.open(DialalogDeleteComponent, {
      width: "auto",
      height: "auto",
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
      }
    });
  }
  onChangePage(event: any) {

  }
  nameEventHander($event: any) {
    this.opened2 = $event;
    console.log("2", this.opened2);
  }
  openAddEditEmpForm(){}
  displayedColumns: string[] = ['position',, 'ten','mieuTa', 'thongTin', 'thaoTac'];
  dataSource = ELEMENT_DATA;
}

