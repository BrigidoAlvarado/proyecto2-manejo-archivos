import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, FormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../../services/auth/auth.service";
import {MessageService} from "../../../services/message.service";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;

   constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private message: MessageService ) {}

  ngOnInit() {
    this.loginForm = this.fb.nonNullable.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(3)]],
    })
  }

  submit() {
    if (this.loginForm.valid) {

      this.authService.login(this.loginForm.value).subscribe({
        next: (res) => {
          console.log('login successfully');
          this.authService.saveToken( res.token);
          this.message.info('Bienvenido ' + this.authService.decodeToken()?.sub);
        },
        error: err => {
          this.message.error('Correo o contraseña invalidos');
          console.error('error al iniciar sesion:'+err);
        }
      })

    } else {
      this.message.error('Formulario invalido por favor revise los campos');
    }
  }
}
