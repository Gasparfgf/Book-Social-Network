import {Component, NgModule} from '@angular/core';
import {AuthenticationRequest} from "../../services/models/authentication-request";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  authRequest: AuthenticationRequest = {email:'', password: ''};
  errorMsg: Array<String> = [];

  constructor(private router: Router, private authService: AuthenticationService) {
  }

  login() {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: value => {
        //save token
        this.router.navigate(['api/v1/books']);
      },
      error: err => {
        if(err.error.validationErrors){
          this.errorMsg = err.error.validationErrors;
        }else {
          this.errorMsg.push(err.error.errorMsg);
        }
      }
    })
  }

  goToRegister() {
    this.router.navigate(['api/v1/auth/register']).then(r => console.log(r));
  }
}
