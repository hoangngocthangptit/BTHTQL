import { Component, OnInit, ViewChild } from "@angular/core";
import { Title } from "@angular/platform-browser";
import { MatPaginator } from "@angular/material/paginator";
import { MatTableDataSource } from "@angular/material/table";
import { UserService } from "src/app/Service/user.service";
import { MatDialog } from "@angular/material/dialog";
import { DilalogUnlockComponent } from "../dilalog-unlock/dilalog-unlock.component";
import { DialalogDeleteComponent } from "../dialalog-delete/dialalog-delete.component";
import { ToastrService } from 'ngx-toastr';
export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

const ELEMENT_DATA: PeriodicElement[] = [];
@Component({
  selector: "app-admin-user",
  templateUrl: "./admin-user.component.html",
  styleUrls: ["./admin-user.component.scss"],
})
export class AdminUserComponent implements OnInit {
  opened = true;
  public opened2 = false;
  isUser = false;
  isSeller = false;
  isAdmin = false;
  pageSize:number=10;
  page: number = 0;
  totalItems: number = 0;
  displayedColumns: string[] = [
    "position",
    "name",
    "email",
    "ngaytao",
    "weight",
    "thaoTac",
  ];
  dataSource: any = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
  name: string;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  constructor(
    private titleService: Title,
    private dialog: MatDialog,
    private toastr: ToastrService,
    private userService: UserService
  ) {}
  isLogin = false;
  role: string;
  ngOnInit(): void {
    this.doSearh();
    this.role = localStorage.getItem("role");
    this.setTitle("Nhân sự");
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

  public setTitle(dashboard: string) {
    this.titleService.setTitle(dashboard);
  }
  doSearh() {
    let data = {
      name: this.name==undefined?"":this.name
    };

    this.userService.SearchUser(data).subscribe((res:any) => {

      this.dataSource = res.obj;

    });
  }
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openDialog(id: number) {
    const dialogRef = this.dialog.open(DilalogUnlockComponent, {
      width: "auto",
      height: "auto",
      // panelClass: 'custom-dialog-container',
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.userService.ActiveUser(id).subscribe((message) => {
          if (message.statusCode === 200) {
            this.toastr.success("Thông báo","Cập nhật Trạng thái thành công")
            this.doSearh();
          }
        });
      }
    });
  }
  deleteDilag(id:number){
    const dialogRef = this.dialog.open(DialalogDeleteComponent, {
      width: "auto",
      height: "auto",
      disableClose: true,
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.userService.DeleteUser(id).subscribe((message) => {
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
