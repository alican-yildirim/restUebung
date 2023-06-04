import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {

  loginCheck : any  = {};
  constructor(private client : HttpClient) {}

  login(val : any) : void{

    //https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=BTC,USD,EUR
    this.client.get('http://localhost:8080/login?email=' + val['email'] + '&&passwort=' + val['password']).subscribe(data => {

      this.loginCheck = data;
      console.log(data);
    });

  }

}
