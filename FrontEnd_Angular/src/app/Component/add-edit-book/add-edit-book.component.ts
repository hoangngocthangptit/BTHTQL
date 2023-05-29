import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { BookserviceService } from 'src/app/Service/bookservice.service';
import { CoreService } from 'src/app/Service/core.service';
import { Book } from './Book';
import { log } from 'console';

@Component({
  selector: 'app-add-edit-book',
  templateUrl: './add-edit-book.component.html',
  styleUrls: ['./add-edit-book.component.scss']
})
export class AddEditBookComponent implements OnInit {
  uploadedImage !: File
  imgUrl: any
  book=new Book();
  imgUrl2: string;
  empForm: FormGroup;

  isEditing: boolean = false;
  public addFormSubmitted = false;
    category: string[] = [
    'Văn học',
    'Tu tiên',
    'Chính trị – pháp luật',
    'Kiếm hiệp',
    'Thiếu nhi',
  ];
  constructor(  private _fb: FormBuilder,
    private bookService: BookserviceService,
    private route: Router,
    private _coreService: CoreService,
    private _dialogRef: MatDialogRef<AddEditBookComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
    ) {

      this.empForm = this._fb.group({
        bookName: ['',Validators.required],
        noOfBooks: '',
        title:  ['',Validators.required],
        createdDate: ['',Validators.required],
        category:'',
        authorName:['',Validators.required],
        bookDetails:'',
        image:''
        // gender: '',
        // education: '',
        // company: '',
        // experience: '',
        // package: '',
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

      let data = new FormData();
      var formData = new FormData();
        formData.append('imageFile', this.uploadedImage); // Thay thế $scope.media bằng giá trị của tập tin hình ảnh
        formData.append('bookName', this.empForm.value.bookName);
        formData.append('category', this.empForm.value.category);
        formData.append('authorName', this.empForm.value.authorName);
        formData.append('bookDetails', this.empForm.value.bookDetails);
        formData.append('noOfBooks', this.empForm.value.noOfBooks);
        formData.append('title', this.empForm.value.title);
        formData.append('createdDate', this.empForm.value.createdDate);


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
          (await this.bookService.addBook(formData)).subscribe({
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
