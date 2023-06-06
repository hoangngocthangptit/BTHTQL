import { Component, OnInit, ViewChild } from "@angular/core";
import { Title } from "@angular/platform-browser";
import { MatPaginator } from "@angular/material/paginator";
import { MatTableDataSource } from "@angular/material/table";
import { MatDialog } from "@angular/material/dialog";
import { ToastrService } from 'ngx-toastr';
import { UserService } from "src/app/Service/user.service";
import { BookserviceService } from "src/app/Service/bookservice.service";
import { Router } from "@angular/router";
import { DialalogDeleteComponent } from "src/app/Component/dialalog-delete/dialalog-delete.component";
import { EditAddPersonComponent } from "../edit-add-person/edit-add-person.component";
import { NhanVienService } from "src/app/Service/nhan-vien.service";
import { EditPersonComponent } from "../edit-person/edit-person.component";
import { MatSort } from "@angular/material/sort";

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}
const ELEMENT_DATA: PeriodicElement[] = [];
@Component({
  selector: 'app-list-personnel',
  templateUrl: './list-personnel.component.html',
  styleUrls: ['./list-personnel.component.scss']
})

export class ListPersonnelComponent implements OnInit {
  public get userService(): NhanVienService {
    return this.bookService;
  }
  public set userService(value: NhanVienService) {
    this.bookService = value;
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
  displayedColumns: string[] = [
    "position",
    "bookName",
    "authorName",
    "no",
    "no2",
    "noOfBooks",
    "createdDate",
    "thaoTac",

  ];
  dataSource!: MatTableDataSource<any>;
  name: string;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(
    private titleService: Title,
    private dialog: MatDialog,
    private toastr: ToastrService,
    private bookService: NhanVienService,
    private route: Router,
    private matSnackBar: ToastrService,
  ) {}
  isLogin = false;
  role: string;
  ngOnInit(): void {
    this.doSearh();
    this.role = localStorage.getItem("role");
    this.setTitle("QuanLiNhanSu");
    console.log("role check toolbar", this.role);
    if (this.role === "admin") {
      this.isAdmin = true;
      this.isLogin = true;
    }
    console.log(this.dataSource);


  }
  nameEventHander($event: any) {
    this.opened2 = $event;
    console.log("2", this.opened2);
  }

  openAddEditEmpForm() {
    if (localStorage.getItem('token') === null) {
      this.matSnackBar.warning('Please Login first');
      this.route.navigateByUrl('login');
      return;
    }
    const dialogRef = this.dialog.open(EditAddPersonComponent, {
      height: '70%',
      width: '70%'
  });
    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.doSearh();
        }
      },
    });
  }
  openEditForm(data: any) {
    if (localStorage.getItem('token') === null) {
      this.matSnackBar.warning('Please Login first');
      this.route.navigateByUrl('login');
      return;
    }
    const dialogRef = this.dialog.open(EditPersonComponent, {
      height: '70%',
      width: '70%',
      data,
    });

    dialogRef.afterClosed().subscribe({
      next: (val) => {
        if (val) {
          this.doSearh();
        }
      },
    });
  }

  public setTitle(dashboard: string) {
    this.titleService.setTitle(dashboard);
  }
  doSearh() {

    this.userService.getEmployeeList().subscribe((res:any) => {
      this.dataSource = new MatTableDataSource(res.obj);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      // this.totalItems=res.total;
    });
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

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
        this.userService.deleteEmployee(id).subscribe((message) => {
          if (message.statusCode === 200) {

            this.toastr.success("Thông báo","Xóa thành công")
            this.doSearh();
          }
        });
      }
    });
  }
  onChangePage(event: any) {
    this.pageSize = event.pageSize;
    this.page = event.pageIndex;
    this.doSearh();
  }


}
