import { Component, OnInit } from '@angular/core';
import {FormGroup, ReactiveFormsModule, FormBuilder, Validators, FormsModule} from "@angular/forms";
import { User } from "../../../entities/user";
import { AuthService } from "../../../services/auth.service";
import {NgIf} from "@angular/common";

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

  constructor(private fb: FormBuilder, private auth: AuthService) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required, Validators.minLength(6)]],
      confirmPassword: [null, [Validators.required, Validators.minLength(6)]],
    }, { validators: this.passwordMatchValidator });
  }

  // Validador para comprobar que password y confirmPassword coincidan
  passwordMatchValidator(group: FormGroup) {
    const pass = group.get('password')?.value;
    const confirm = group.get('confirmPassword')?.value;
    return pass === confirm ? null : { notMatching: true };
  }

  submit(): void {
    if (this.registerForm.valid) {
      this.submitting = true;

      const data = this.registerForm.value;
      const user: User = {
        name: data.name,
        email: data.email,
        password: data.password,
        role: "COMMON",
        enabled: true
      };

      this.auth.register(user).subscribe({
        next: res => {
          console.log('Registro exitoso', res);
          this.submitting = false;
          this.registerForm.reset();
          alert('Registro completado con éxito');
        },
        error: err => {
          console.error('Error al registrar', err);
          this.submitting = false;
          alert('Ocurrió un error al registrar el usuario');
        }
      });
    } else {
      this.registerForm.markAllAsTouched();
      alert('Formulario inválido. Revisa los campos.');
    }
  }

  // Función para mostrar si un campo es inválido y fue tocado
  isInvalid(field: string): boolean {
    const control = this.registerForm.get(field);
    return !!(control && control.invalid && (control.touched || control.dirty));
  }
}
