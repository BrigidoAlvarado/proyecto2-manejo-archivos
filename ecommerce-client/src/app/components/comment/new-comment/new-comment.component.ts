import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MessageService} from "../../../services/message.service";
import {Comment} from "../../../entities/comment";
import {ValidFormService} from "../../../services/tools/validForm/valid-form.service";
import {CommentService} from "../../../services/delivery-package/comment.service";

@Component({
  selector: 'app-new-comment',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './new-comment.component.html',
  styleUrl: './new-comment.component.scss'
})
export class NewCommentComponent  implements OnInit {

  @Input(  )
  productId!: number;
  commentForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private messageService: MessageService,
    private commentService: CommentService,
    public validForm: ValidFormService
  ) {
  }

  ngOnInit(): void {

    this.commentForm = this.formBuilder.nonNullable.group({
      comment: ['', Validators.required],
    })
  }

  submit() {
    if (this.commentForm.valid) {

      let commentEntity: Comment = this.commentForm.value as Comment;
      commentEntity.productId = this.productId;

      this.commentService.postComment(commentEntity).subscribe({
        next: () => {
          this.messageService.success('Comentario ingresado exitosamente.');
          this.commentForm.reset();
        },
        error: err => {
          const msg:string = 'Error al guardar el nuevo comentario.';
          this.messageService.error(msg);
          console.error(msg, err);
        }
      })

    } else {
      this.messageService.error('Formulario invalido por favor verifique los campos')
    }
  }
}
