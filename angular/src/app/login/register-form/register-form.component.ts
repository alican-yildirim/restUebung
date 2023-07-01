import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {

  constructor(private client : HttpClient){}

  registerForm : FormGroup = new FormGroup({
    vorname: new FormControl(),
    nachname: new FormControl(),
    password: new FormControl(),
    email: new FormControl(null, [Validators.email, Validators.required]),
    gender: new FormControl(),
    age: new FormControl()
  });
  sliderValue : string = "0";

  register() : void {

    console.log(
    "vorname:" + this.registerForm.controls["vorname"].value +
    " - nachname:" + this.registerForm.controls["nachname"].value +
    " - email:" + this.registerForm.controls["email"].value + 
    " - password:" + this.registerForm.controls["password"].value +
    " - gender:"+ this.registerForm.controls["gender"].value + 
    " - age:" +this.registerForm.controls["age"].value);

    //console.log(this.registerForm);
    //this.registerForm.controls["password"].reset(); // Entleert PW jedesmal

    const request_body = {
      vorname: this.registerForm.controls["vorname"].value,
      nachname: this.registerForm.controls["nachname"].value,
      email: this.registerForm.controls["email"].value,
      password: this.registerForm.controls["password"].value,
      gender: this.registerForm.controls["gender"].value,
      age: this.registerForm.controls["age"].value
    };

    this.client.post('http://localhost:8080/register', request_body ).subscribe(data => {
      
      console.log(data);
    });
  }
  
  
}
