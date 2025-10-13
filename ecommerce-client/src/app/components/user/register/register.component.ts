import { Component, OnInit } from '@angular/core';
import {
  FormGroup,
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormsModule,
  ValidatorFn,
  AbstractControl, ValidationErrors
} from "@angular/forms";
import { User } from "../../../entities/user";
import { AuthService } from "../../../services/auth/auth.service";
import {NgIf} from "@angular/common";
import {MessageService} from "../../../services/message.service";
import {AppConfig} from "../../../config/app.constants";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule, NgIf],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  submitting = false;

  constructor(private fb: FormBuilder, private auth: AuthService, private message: MessageService) {}

  ngOnInit(): void {
    this.registerForm = this.fb.nonNullable.group({
      name:            ['', [Validators.required]],
      email:           ['', [Validators.required, Validators.email]],
      password:        ['', [Validators.required, Validators.minLength(3)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(3)]],
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
    const group = control as FormGroup;
    const password = group.get('password')?.value;
    const confirm = group.get('confirmPassword')?.value;

    return password && confirm && password !== confirm ? { notMatching: true } : null;
  };

  submit(): void {
    if (this.registerForm.valid) {
      this.submitting = true;

      const data = this.registerForm.value;
      const user: User = {
        name: data.name,
        email: data.email,
        password: data.password,
        role: AppConfig.ROLES.COMMON,
        enabled: true
      };

      this.auth.register(user).subscribe({
        next: res => {
          console.log('Registro exitoso', res);
          this.submitting = false;
          this.message.success('Registro exitoso');
          this.registerForm.reset();
          this.auth.saveToken(res.token);
        },
        error: err => {
          console.error('Error al registrar', err);
          this.submitting = false;
          this.message.error('Ocurrio un error al crear el nuevo registro');
        }
      });
    } else {
      this.registerForm.markAllAsTouched();
      this.message.error('Formulario ivalido, revise los campos');
    }
  }

  isInvalid(field: string): boolean {
    const control = this.registerForm.get(field);
    return !!(control && control.invalid && (control.touched || control.dirty));
  }
}
