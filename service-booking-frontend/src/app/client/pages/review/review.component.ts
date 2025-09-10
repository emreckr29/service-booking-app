import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrl: './review.component.scss'
})
export class ReviewComponent {

  bookId: number = this.activatedRoute.snapshot.params["bookId"];
  validateForm!: FormGroup;


  constructor(private clientService: ClientService,
    private notification: NzNotificationService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder){}


  ngOnInit(){
    this.validateForm = this.fb.group({
      rating : [null, Validators.required],
      review: [null, Validators.required]
    })
  }

  giveReview(){
    const reviewDto = {
      review : this.validateForm.get(['review']).value,
      userId: UserStorageService.getUserId(),
      rating: this.validateForm.get(['rating']).value,
      reservationId: this.bookId,
    }
    this.clientService.giveReview(reviewDto).subscribe(res => {
      this.notification.success(
        'SUCCES',
        'Review posted Successfully',
        { nzDuration: 5000}
      )

    }, error => {
      this.notification.error(
        'ERROR',
        `${error.message}`,
        { nzDuration: 5000}
      )

    })
  }

}
