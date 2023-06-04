

import { Router, ActivatedRoute,ParamMap} from '@angular/router';

import { Component, OnInit ,ViewChild  } from '@angular/core';
import { MatSidenavModule } from '@angular/material/sidenav';
import { WishlistService } from 'src/app/Service/wishlist.service';
import { CartService } from 'src/app/Service/cart.service';



@Component({
  selector: 'app-sidenavbar',
  templateUrl: './sidenavbar.component.html',
  styleUrls: ['./sidenavbar.component.scss']
})

export class SidenavbarComponent implements OnInit {
  @ViewChild('sidenav', { static: true }) public sidenav: MatSidenavModule;
  isSeller = false;
  isUser = false;
  isAdmin=false;
  role:string;
  isLogin = false;
  name: any;
  email:any;
  length: any;
  wishlistLength: number;
  constructor(private router:Router,private route:ActivatedRoute, private wishlistService: WishlistService, private cartService: CartService,) { }




  ngOnInit() {
    this.wishlistService.autoRefresh$.subscribe(() => {

      this. getWishlistCount();
    }

    );
    this. getWishlistCount();

    this.cartService.autoRefresh$.subscribe(() => {
      this.getCartItemCount();

    });

    this.getCartItemCount();
    this.name = localStorage.getItem('Name');
    this.role = localStorage.getItem('role');
    this.email=localStorage.getItem('email');
    console.log("email",this.email)
    console.log('role check toolbar', this.role);
    if (this.role === 'admin') {
     this.isAdmin = true;
     this.isLogin = true;
   }
    if (this.role === 'seller') {
     this.isSeller = true;
     this.isLogin = true;
   }
    if (this.role === 'user') {
     this.isUser = true;
     this.isLogin = true;
     console.log('is user ', this.isUser);
   }
   this.role= localStorage.getItem('role');
   console.log('role check sidenav',this.role);
   if (this.role === 'admin')
   {
     this.isAdmin=true;
   }
   if (this.role === 'seller')
   {
     this.isSeller=true;
   }
  }
  sellerBooks()
  {
    this.router.navigate(['books'],{queryParams:{book:'unverified'}});
  }

  orders()
  {
    this.router.navigate(['books'],{queryParams:{book:'order'}});
  }

  reviews()
  {
    this.router.navigate(['books'],{queryParams:{book:'review'}});
  }

  books()
  {
    this.router.navigate(['books'],{queryParams:{book:'books'}});
  }
  per()
  {
    this.router.navigate(['per_view'],{queryParams:{book:'books'}});
  }

  pay()
  {
    this.router.navigate(['pay_view'],{queryParams:{book:'books'}});
  }
  time()
  {
    this.router.navigate(['time_view'],{queryParams:{book:'books'}});
  }
  khen()
  {
    this.router.navigate(['khen-thuong'],{queryParams:{book:'books'}});
  }
  chuc()
  {
    this.router.navigate(['chuc-vu'],{queryParams:{book:'books'}});
  }
  phat()
  {
    this.router.navigate(['ki-luat'],{queryParams:{book:'books'}});
  }
  department()
  {
    this.router.navigate(['department'],{queryParams:{book:'books'}});
  }
  bh()
  {
    this.router.navigate(['bhxh'],{queryParams:{book:'books'}});
  }
  User(){
    this.router.navigate(['user']);
  }
  Home(){
    this.router.navigate(['']);
  }
  sellerBook(){

      this.router.navigate(['books'],{queryParams:{book:'sellerbook'}});
  }

  orderStatus(){

this.router.navigate(['books'],{queryParams:{book:'order'}});
  }
  getWishlistCount() {
    this.wishlistService.getWishlistCount().subscribe((response: any) => {
      this.wishlistLength = response.obj;
      console.log('total number wishBook are' + response.obj);
     });
  }
  getCartItemCount() {
    this.cartService.getCartItemCount().subscribe((response: any) => {
      this.length = response.obj;
      console.log('total number of itemes are' + response.obj);
     });
  }

}

