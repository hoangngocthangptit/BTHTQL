import { DashboardComponent } from './Component/dashboard/dashboard.component';
import { SellerComponent } from './Component/seller/seller/seller.component';
import { AdminComponent } from './Component/admin/admin/admin.component';
import { RegistrationComponent } from './Component/auth/registration/registration.component';
import { LoginComponentComponent } from './Component/auth/login-component/login-component.component';
import { ForgetPasswordComponent } from './Component/auth/forget-password/forget-password.component';
import { ResetPasswordComponent } from './Component/auth/reset-password/reset-password.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CartComponent } from './Component/cart/cart.component';
import { OrdergreetingComponent } from './Component/ordergreeting/ordergreeting.component';
import { GiverateComponent } from './Component/giverate/giverate.component';
import { AdminunverifiedbooksComponent } from './Component/adminunverifiedbooks/adminunverifiedbooks.component';
import { RatereviewComponent } from './Component/ratereview/ratereview.component';
import { OrderstatusComponent } from './Component/orderstatus/orderstatus.component';
import { RatedbooksComponent } from './Component/ratedbooks/ratedbooks.component';
import { BookreviewsComponent } from './Component/bookreviews/bookreviews.component';
import { WishComponent } from './Component/wish/wish.component';
import { ChartComponent } from './Component/chart/chart.component';
import { PagenotfoundComponent } from './component/pagenotfound/pagenotfound.component';
import { AdminUserComponent } from './Component/admin-user/admin-user.component';
import { HistoryBuyComponent } from './Component/history-buy/history-buy.component';
import { ThongTinTaiKhoanComponent } from './Component/thong-tin-tai-khoan/thong-tin-tai-khoan.component';
import { DilalogChangePassWordComponent } from './Component/dilalog-change-pass-word/dilalog-change-pass-word.component';
import { BookViewComponent } from './Component/book-view/book-view.component';
import { ListPaycheckComponent } from './IUHR/list-paycheck/list-paycheck.component';
import { ListPersonnelComponent } from './IUHR/list-personnel/list-personnel.component';
import { ListTimekeepingComponent } from './IUHR/list-timekeeping/list-timekeeping.component';
import { HomeViewComponent } from './Component/home-view/home-view.component';
import { DepartmentComponent } from './IUHR/department/department.component';
import { KiLuatComponent } from './IUHR/ki-luat/ki-luat.component';
import { KhenThuongComponent } from './IUHR/khen-thuong/khen-thuong.component';
import { ChucVuComponent } from './IUHR/chuc-vu/chuc-vu.component';
import { BaoHiemComponent } from './IUHR/bao-hiem/bao-hiem.component';

const routes: Routes = [

  {
    path: '', redirectTo: 'chart',
    pathMatch: 'full'
  },
  {path: 'chuc-vu', component: ChucVuComponent},
  {path: 'bhxh', component: BaoHiemComponent},
  {path: 'khen-thuong', component: KhenThuongComponent},
  {path: 'ki-luat', component: KiLuatComponent},
  {path: 'department', component: DepartmentComponent},
  {path: 'home_view', component: HomeViewComponent},
  {path: 'pay_view', component: ListPaycheckComponent},
  {path: 'per_view', component: ListPersonnelComponent},
  {path: 'time_view', component: ListTimekeepingComponent},
  {path: 'book_view', component: BookViewComponent},
  {path: 'books', component: DashboardComponent},
  {path: 'cart', component: CartComponent},
  {path:'history',component:HistoryBuyComponent},
  {path: 'greeting', component: OrdergreetingComponent},
  {path: 'update-password/:token', component: ResetPasswordComponent},
  {path: 'forget-password', component: ForgetPasswordComponent},
  {path: 'login', component: LoginComponentComponent},
  {path: 'seller', component: SellerComponent},
  {path: 'wish', component: WishComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'books/rateandreview/:bookId', component: GiverateComponent},
  {path: 'verifybook', component: AdminunverifiedbooksComponent},
  {path: 'books/info/:bookId', component: RatereviewComponent},
  {path: 'books/:book', component: SellerComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'books/rateandreview/:bookId/:token', component: GiverateComponent},
  {path: 'verifybook', component: AdminunverifiedbooksComponent},
  {path: 'books/reviews/:bookId', component: RatereviewComponent},
  {path: 'books/orders', component: OrderstatusComponent},
  {path: 'ratedbooks', component: RatedbooksComponent},
  {path: 'bookreviews', component: BookreviewsComponent},
  {path:'chart' ,component:ChartComponent},
  {path:'user',component:AdminUserComponent},
  {path:'userid/:userId',component:ThongTinTaiKhoanComponent},
  {path:'passWord/:userId',component:DilalogChangePassWordComponent},
  {path: '**', component: PagenotfoundComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
