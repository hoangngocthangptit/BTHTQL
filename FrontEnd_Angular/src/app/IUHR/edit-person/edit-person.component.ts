import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CoreService } from 'src/app/Service/core.service';
import { NhanVienService } from 'src/app/Service/nhan-vien.service';

@Component({
  selector: 'app-edit-person',
  templateUrl: './edit-person.component.html',
  styleUrls: ['./edit-person.component.scss']
})
export class EditPersonComponent implements OnInit {

  uploadedImage !: File
  imgUrl: any
  imgUrl2: string;
  empForm: FormGroup;

  isEditing: boolean = false;
  public addFormSubmitted = false;
  constructor(  private _fb: FormBuilder,
    private bookService: NhanVienService,
    private route: Router,
    private _coreService: CoreService,
    private _dialogRef: MatDialogRef<EditPersonComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
    ) {

      this.empForm = this._fb.group({
        ho: ['',Validators.required],
        ten: '',
        email:  ['',Validators.required],
        gioiTinh: ['',Validators.required],
        diaChi:'',
        ngayBatDau:['',Validators.required],
        quocTich:'',
        image:'',
        chucVu:'',
        phongBan:'',
      });
    }
    isLogin = true;

    ngOnInit(): void {
      this.empForm.patchValue(this.data);
      //   if (this.book.id != undefined){

      // }
      this.imgUrl=`http://localhost:8080/image/${this.empForm.value.image}`;

    }

    onImageUpload(event: any) {
      console.log("rrr",this.imgUrl);
      this.uploadedImage = event.target.files[0];
      const reader = new FileReader();
      reader.readAsDataURL(this.uploadedImage);
      reader.onload = () => {
        this.imgUrl = reader.result;
      };
    }

  get AddEditFrom(){
    return this.empForm.controls;
  }





    getData = () => {

      var formData = new FormData();
      if(this.uploadedImage){
        formData.append('imageFile', this.uploadedImage);
        console.log("thanghn trong"); // Thay thế $scope.media bằng giá trị của tập tin hình ảnh
      } // Thay thế $scope.media bằng giá trị của tập tin hình ảnh
      console.log("thanghn n");
        formData.append('ho', this.empForm.value.ho);
        formData.append('ten', this.empForm.value.ten);
        formData.append('email', this.empForm.value.email);
        formData.append('gioiTinh', this.empForm.value.gioiTinh);
        formData.append('diaChi', this.empForm.value.diaChi);
        formData.append('quocTich', this.empForm.value.quocTich);
        // formData.append('idChucVu', this.empForm.value.chucVu);
        // formData.append('idPhongBan', this.empForm.value.phongBan);
        if(typeof(this.empForm.value.ngayBatDau) != 'string'){
          formData.append('ngayBatDau', this.empForm.value.ngayBatDau);
        }



      return formData
    }

    saveChanges() {
      // Lưu thông tin người dùng đã chỉnh sửa

      this.isEditing = false;
    }

    async onFormSubmit() {
      this.addFormSubmitted=true;
      let formData = this.getData();

          // cap nhat list trong giao dien
          // cap nhat trong db
          if (this.data) {
            this.bookService
          .updateEmployee(this.data.id, formData)
          .subscribe({
            next: (val: any) => {
              this._coreService.openSnackBar('Employee detail updated!');
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              console.error(err);
            },
          });
          }
          else{
          (await this.bookService.addEmployee(formData)).subscribe({
            next: (val: any) => {
              this._coreService.openSnackBar('thêm mới thành công');
              this._dialogRef.close(true);
            },
            error: (err: any) => {
              console.error(err);
            },
          });
        }

      }


}
