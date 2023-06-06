import {Component, OnInit, ViewChild} from '@angular/core';
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
import { MatSort } from '@angular/material/sort';
import { NhanVienService } from 'src/app/Service/nhan-vien.service';

export interface PeriodicElement {
  position: number;
  ten: string;
  mieuTa: string;
  heSo: string;

}

const ELEMENT_DATA: PeriodicElement[] = [



];

@Component({
  selector: 'app-bao-hiem',
  templateUrl: './bao-hiem.component.html',
  styleUrls: ['./bao-hiem.component.scss']
})
export class BaoHiemComponent implements OnInit {


  constructor(    private titleService: Title,
    private dialog: MatDialog,
    private toastr: ToastrService,
    private bookService: NhanVienService,
    private route: Router,
    private matSnackBar: ToastrService,) { }

  ngOnInit(): void {
    this.doSearh();
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
  dataSource!: MatTableDataSource<any>;
  name: string;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
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
  doSearh() {

    this.bookService.bao().subscribe((res:any) => {
      this.dataSource = new MatTableDataSource(res.obj);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      // this.totalItems=res.total;
    });
  }
  nameEventHander($event: any) {
    this.opened2 = $event;
    console.log("2", this.opened2);
  }
  openAddEditEmpForm(){}
  displayedColumns: string[] = ['position', 'ten','heSo','mieuTa', 'muc', 'thaoTac'];
}
