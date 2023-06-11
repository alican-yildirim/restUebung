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

    this.client.get('http://localhost:8080/login?email=' + val['email'] + '&&passwort=' + val['password'],{observe:'response'}).subscribe(data => {

      if(data.status == 200){

        this.loginCheck = data;
        console.log(data.body);
      }else{
        console.log('Etwas ist schief gelaufen bei der Abfrage. Status: ' + data.status + '. Statustext: ' + data.statusText);
      }
    });

  }

}
