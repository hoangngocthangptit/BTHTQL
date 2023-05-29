import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "src/app/Service/user.service";
import { Location } from "@angular/common";
import { ToastrService } from "ngx-toastr";
@Component({
  selector: "app-dilalog-change-pass-word",
  templateUrl: "./dilalog-change-pass-word.component.html",
  styleUrls: ["./dilalog-change-pass-word.component.scss"],
})
export class DilalogChangePassWordComponent implements OnInit {
  userId: any;
  hide = true;
  private canGoBack: boolean = false;
  constructor(
    private fromBuilder: FormBuilder,
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router,
    private readonly location: Location,
    private userService: UserService
  ) {
    this.canGoBack = !!this.router.getCurrentNavigation()?.previousNavigation;
  }

  formBCTKCC: FormGroup = this.fromBuilder.group({
    Password: [null, [Validators.required, Validators.minLength(8)]],
  });

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get("userId");
  }

  doUpdate() {
    if (this.formBCTKCC.invalid) {
      this.formBCTKCC.markAllAsTouched();
      // this.matSnackBar.open("Kiểm tra thông tin đầu vào", "ok");
      this.toastr.warning("Thông báo","Kiểm tra thông tin đầu vào")
      return;
    } else {
      let data = {
        password: this.formBCTKCC.get("Password").value,
      };
      this.userService
        .UpdatepassWord(this.userId, data)
        .subscribe((res: any) => {
          if (res.statusCode == 200) {
            this.toastr.success("Thông báo", res.message)
            this.router.navigateByUrl("books");
          }
        });
    }
  }

  goBack(): void {
    if (this.canGoBack) {
      this.location.back();
    }
  }
}
