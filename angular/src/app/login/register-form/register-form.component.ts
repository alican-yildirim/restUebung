import { HttpClient } from '@angular/common/http';
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
    email: new FormControl(null, Validators.email),
    gender: new FormControl(),
    age: new FormControl()
  });
  sliderValue : string = "0";

  register() : void {

    console.log(this.registerForm);
    this.registerForm.controls["password"].reset(); // Entleert PW jedesmal
  }

}
