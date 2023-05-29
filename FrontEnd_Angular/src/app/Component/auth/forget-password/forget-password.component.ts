import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/Service/user.service";
import { Router } from "@angular/router";
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: "app-forget-password",
  templateUrl: "./forget-password.component.html",
  styleUrls: ["./forget-password.component.scss"],
})
export class ForgetPasswordComponent implements OnInit {
  public isLoading = false;
  public error = null;
  message = null;
  public form = {
    email: null,
  };

  constructor(
    private user: UserService,
    private route: Router,
    private toastr: ToastrService,
  ) {}

  ngOnInit() {}
  onSubmit() {
    this.isLoading = true;
    this.user.forgetPassword(this.form).subscribe(
      (data) => this.handleResponse(data),
      (error) => this.handleError(error)
    );
  }
  handleError(error: { error: any }) {
    this.isLoading = false;
    this.error = error.error.message;
    this.toastr.error("Thông báo", this.error)
  }
  handleResponse(data) {
    this.isLoading = false;
   this.toastr.success("Thông báo","Vui lòng kiểm tra email")
    this.route.navigateByUrl("login");
  }
}
