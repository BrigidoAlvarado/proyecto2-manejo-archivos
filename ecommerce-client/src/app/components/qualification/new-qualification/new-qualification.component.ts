import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {ValidFormService} from "../../../services/tools/validForm/valid-form.service";
import {MessageService} from "../../../services/message.service";
import {QualificationService} from "../../../services/qualification/qualification.service";
import {Qualification} from "../../../entities/qualification";

@Component({
  selector: 'app-new-qualification',
  standalone: true,
    imports: [
        FormsModule,
        ReactiveFormsModule
    ],
  templateUrl: './new-qualification.component.html',
  styleUrl: './new-qualification.component.scss'
})
export class NewQualificationComponent implements OnInit {

  @Input() productId!: number;
  qualificationForm!: FormGroup;
  qualificationSelected: string = '0';

  constructor(
    private formBuilder: FormBuilder,
    private message: MessageService,
    private qualificationService: QualificationService,
    public validForm: ValidFormService,

  ) {
  }

  ngOnInit() {
    this.qualificationForm = this.formBuilder.nonNullable.group({
      starts: [0, [Validators.required, Validators.min(1), Validators.max(5)]],
    })

    this.qualificationForm.get('starts')?.valueChanges.subscribe(
      value => this.qualificationSelected = value
    )

  }

  submit() {
    if (this.qualificationForm.valid) {

      let qualification: Qualification = this.qualificationForm.value as Qualification;
      qualification.productId = this.productId;

      this.qualificationService.postQualification( qualification ).subscribe({
        next: () => {
          this.message.success("Se califico el producto exitosamente");
          this.qualificationForm.reset();
        },
        error: err => {
          const msg = 'Error al calificar el producto'
          console.error(msg, err)
          this.message.error(msg)
        }
      })
    } else {
      this.message.error('Formulario invalido por favor revice los campos')
    }
  }

}
