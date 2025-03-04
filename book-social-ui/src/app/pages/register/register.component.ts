import { Component } from '@angular/core';
import {RegistrationRequest} from "../../services/models/registration-request";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  registerRequest: RegistrationRequest = {
    firstname: '', lastname: '', email: '', password: ''
  }
  errorMsg: Array<String> = [];

  constructor(private router: Router, private authService: AuthenticationService) {
  }

  register() {
    this.errorMsg = [];
    this.authService.register({
      body: this.registerRequest
    }).subscribe({
      next: value => {this.router.navigate(['activate-account']);},
      error: err => {this.errorMsg = err.error.validationErrors;}
    })
  }

  goToLogin() {
    this.router.navigate(['login']);
  }
}
