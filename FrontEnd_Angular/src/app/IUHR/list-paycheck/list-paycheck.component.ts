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

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}
const ELEMENT_DATA: PeriodicElement[] = [];
@Component({
  selector: 'app-list-paycheck',
  templateUrl: './list-paycheck.component.html',
  styleUrls: ['./list-paycheck.component.scss']
})
export class ListPaycheckComponent implements OnInit {

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
  public evaluated=0;
  totalItems: number = 0;
  public listChucVu=[{id:"1", name: "tháng 1"}, {id:"2", name: "tháng 2"}, {id:"3", name: "tháng 3"},{id:"4", name: "tháng 4"},{id:"5", name: "tháng 5"},{id:"6", name: "tháng 6"},{id:"7", name: "tháng 7"},{id:"8", name: "tháng 8"},{id:"9", name: "tháng 9"},{id:"10", name: "tháng 10"},{id:"11", name: "tháng 11"},{id:"12", name: "tháng 12"}];
  displayedColumns: string[] = [
    "position",
    "bookName",
    "authorName",
    "no",
    "noOfBooks",
    "createdDate",
    "thaoTac",

  ];
  dataSource: any = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
  name: string;
  @ViewChild(MatPaginator) paginator: MatPaginator;
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
    this.setTitle("HR");
    console.log("role check toolbar", this.role);
    if (this.role === "admin") {
      this.isAdmin = true;
      this.isLogin = true;
    }
    this.dataSource.paginator = this.paginator;

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
      height: '80%',
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
    const dialogRef = this.dialog.open(EditAddPersonComponent, {
      height: '80%',
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

    this.userService.listLuong().subscribe((res:any) => {
      this.dataSource = res.obj;
      // this.totalItems=res.total;
    });
  }
  onChange(){
    this.doSearh();
    console.log("tranin: ",this.evaluated);

  }
  reset(){
    this.evaluated=0;
    this.doSearh;
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
