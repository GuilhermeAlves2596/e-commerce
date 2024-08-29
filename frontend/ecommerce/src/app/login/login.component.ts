import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      login: ['', Validators.required],
      senha: ['', Validators.required],
    });
  }

  onSubmit(event: Event): void {
    event.preventDefault();
    console.log('Login data:', this.loginForm.value);
    if(this.loginForm.valid){
      const {login, senha} = this.loginForm.value;
      this.authService.login({login, senha}).subscribe({
        next:(response) => {
          console.log('Login successful', response);
        },
        error:(error) => {
          console.error('Login failed', error);
        }
      })
    }
  }

}
