import {Component, OnInit} from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule, ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {ValidFormService} from "../../../services/tools/validForm/valid-form.service";
import {AuthService} from "../../../services/auth/auth.service";
import {MessageService} from "../../../services/message.service";
import {RoleService} from "../../../services/role/role.service";

@Component({
  selector: 'app-create-user',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './create-user.component.html',
  styleUrl: './create-user.component.scss'
})
export class CreateUserComponent implements OnInit {

  createUserForm!: FormGroup;
  roles: string[] = [];

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private message: MessageService,
    private roleService: RoleService,
    public validForm: ValidFormService,
  ) {
  }

  // crear validaciones para el formulario y cargar roles
  ngOnInit() {

    this.createUserForm = this.fb.nonNullable.group({
      name:            ['', [Validators.required]],
      email:           ['', [Validators.required, Validators.email]],
      password:        ['', [Validators.required, Validators.minLength(3)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(3)]],
      role:            ['', [Validators.required]],
    },{ validators: this.passwordMatchValidator });

    this.roleService.getRole().subscribe(role => this.roles = role);
  }

  passwordMatchValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
    const group = control as FormGroup;
    const password = group.get('password')?.value;
    const confirm = group.get('confirmPassword')?.value;

    return password && confirm && password !== confirm ? { notMatching: true } : null;
  };

  submit(): void {
    if (this.createUserForm.valid) {
      this.auth.register(this.createUserForm.value).subscribe({
        next: () => {
          this.message.success('Nuevo usuario registrado')
          this.createUserForm.reset();
        },
        error: err => {
          console.error(err);
        }
      })
    } else {
      this.message.error('Formulario invalido por favor revisa los campos')
    }
  }
}
