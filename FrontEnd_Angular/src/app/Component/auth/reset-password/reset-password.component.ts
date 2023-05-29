import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { UserService } from 'src/app/Service/user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss']
})
export class ResetPasswordComponent implements OnInit {
  public error = null;
  public isLoading = false;
  token: string;
  public form = {
    email: null,
    newPassword: null,
    confirmPassword: null,
  };
  constructor(private user: UserService,
              private route: Router,
              private toastr: ToastrService,
              private activatedRoute: ActivatedRoute
) {
}
ngOnInit() {
  this.activatedRoute.paramMap.subscribe((parameters: ParamMap) => {
  this.token = parameters.get('token');
  console.log(this.token);
  });
  }
handleError(error) {
  this.isLoading = false;
  this.error = error.error.message;
  console.log(error);
  this.toastr.error(this.error);
}
onSubmit() {
  this.isLoading = true;
  this.user.updatePassword(this.form, this.token).subscribe(
  data => this.handleResponse(data),
  error => this.handleError(error)
);
}
handleResponse(data) {
  this.isLoading = false;
  this.toastr.success('Sucessfull Update Password');
  this.route.navigateByUrl('\login');
}

}
