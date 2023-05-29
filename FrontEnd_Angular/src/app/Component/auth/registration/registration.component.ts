import { Component, OnInit, Input } from '@angular/core';
import { ThemePalette } from '@angular/material/core';
import { UserService } from 'src/app/Service/user.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {
  public error = null;
  message = null;
  public isloading = false;
  public form = {
    name: null,
    email: null,
    password: null,
    confirmPassword: null,
    mobileNumber: null,
    role: null,
  };
  constructor(private user: UserService,
              private route: Router,
              private toastr: ToastrService,
  ) {
  }
  ngOnInit() {
  }
  handleError(error) {
    this.isloading = false;
    this.error = error.error.message;
    console.log(error);
    this.toastr.error(this.error)
  }
  onSubmit() {
    this.isloading = true;
    this.user.signUp(this.form).subscribe(
      data => this.handleResponse(data),
      error => this.handleError(error)
    );
  }
  handleResponse(data) {
    this.message = data.message;
    this.isloading = false;
    console.log(data);
    this.route.navigateByUrl('/login');
    this.toastr.success('Sucessfull Registration Done ');

  }
}
