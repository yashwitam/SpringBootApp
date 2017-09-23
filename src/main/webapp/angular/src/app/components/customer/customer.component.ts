import { Component, OnInit } from '@angular/core';
import {Http} from '@angular/http';
import {Observable} from "rxjs/Rx";
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  customers:Customer[];

  constructor(public http:Http) { 
    console.log('Customer Component connected...');
  }

  ngOnInit() {
      console.log('ngOnInit initalized..')

      this.getCustomerList()
      .subscribe(
        customer => this.customers = customer,
        err => {
          // Log errors if any
          console.log(err);
        });
  }


     getCustomerList():Observable<Customer[]> {
       return this.http.get ('http://localhost:8080/SpringBootApp/api/v1/customer/')
        .map(res => res.json());
    }

}

interface Customer{
  id:number,
  firstName:String,
  lastName:String,
  email:String,
  age:number

}
