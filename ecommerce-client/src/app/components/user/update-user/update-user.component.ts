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
import {Location, NgIf} from "@angular/common";
import {AuthService} from "../../../services/auth/auth.service";
import {MessageService} from "../../../services/message.service";
import {User} from "../../../entities/user/user";
import {AppConfig} from "../../../config/app.constants";
import {ValidFormService} from "../../../services/tools/validForm/valid-form.service";
import {UserService} from "../../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";
import {RoleService} from "../../../services/role/role.service";

@Component({
  selector: 'app-update-user',
  standalone: true,
    imports: [
        FormsModule,
        ReactiveFormsModule
    ],
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.scss'
})
export class UpdateUserComponent implements OnInit {

  updateForm!: FormGroup;
  submitting:boolean = false;
  roles: string[] = []
  user: User = {
    name: '',
    role: '',
    email: '',
    enabled: false,
    password: '',
    id: 0
  }
  constructor(
    private fb: FormBuilder,
    private message: MessageService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private location: Location,
    private roleService: RoleService,
    public validForm: ValidFormService,
  ) {}

  ngOnInit(): void {
    // Get id
    const userId: number = Number( this.activatedRoute.snapshot.params['id'] );
    // Get roles from server
    this.roleService.getRole().subscribe(role => this.roles = role);
    // Get user information from server by id
    this.userService.getUserById( userId ).subscribe({
      next: data => {
        this.user = data as User;
        this.createForm()
      },
      error: err => {
        const msg = 'Error al cargar el usuario por id'
        this.message.error(msg);
        console.error(msg, err);
      }
    })
    this.createForm()
  }

  createForm(){
    this.updateForm = this.fb.nonNullable.group({
      id:              [this.user.id,           [Validators.required, Validators.min(1)]],
      name:            [this.user.username,     [Validators.required]],
      email:           [this.user.email,        [Validators.required, Validators.email]],
      role:            [this.user.role,         [Validators.required]],
      password:        ['',                     [ Validators.minLength(3)]],
      confirmPassword: ['',                     [ Validators.minLength(3)]],
    }, { validators: this.passwordMatchValidator });
  }

  // validator to passwords
  passwordMatchValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
    const group = control as FormGroup;
    const password = group.get('password')?.value;
    const confirm = group.get('confirmPassword')?.value;

    return password && confirm && password !== confirm ? { notMatching: true } : null;
  };


  submit(): void {
    if (this.updateForm.valid) {
      this.submitting = true;

      const data = this.updateForm.value;
      const user: User = {
        id: data.id,
        name: data.name,
        email: data.email,
        role: data.role,
      };

      if ( data.password ) user.password = data.password;

      console.log(user);
      this.userService.putUser(user).subscribe({
        next: () => {
          this.submitting = false;
          this.message.success('Actualizacion Exitosa');
          this.ngOnInit()
        },
        error: err => {
          const msg = 'Error al actualizar el usuario'
          console.error(msg, err);
          this.message.error(msg);
          this.submitting = false;
        }
      });
    } else {
      this.updateForm.markAllAsTouched();
      this.message.error('Formulario ivalido, revise los campos');
    }
  }

}
