import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { UserStorageService } from '../../../basic/services/storage/user-storage.service';
import { ClientService } from '../../services/client.service';

@Component({
  selector: 'app-ad-detail',
  templateUrl: './ad-detail.component.html',
  styleUrl: './ad-detail.component.scss'
})
export class AdDetailComponent {

  adId= this.activatedRoute.snapshot.params['adId'];

  avatarUrl:any;

  ad:any;

  validateForm: FormGroup

  reviews:any;

  constructor(private clientService: ClientService,
    private activatedRoute: ActivatedRoute,
    private notification: NzNotificationService,
    private router: Router,
    private fb: FormBuilder) {}


    ngOnInit(){

      this.validateForm = this.fb.group({
        bookDate: [null, [Validators.required]]
      })
      this.getAdDetailByAdId();
    }
    getAdDetailByAdId() {
      this.clientService.getAdDetailsByAdId(this.adId).subscribe(res=>{
        console.log(res);

        this.avatarUrl = 'data:image/jpeg;base64,' + res.adDto.returnedImage;
        this.ad = res.adDto;
        this.reviews = res.reviewDtos;
        console.log(res);
        console.log(res.adDto);
        console.log(res.reviewDtos);
        console.log(this.reviews)
      })
    }

    bookService(){
      const bookServiceDto = {
        bookDate: this.validateForm.get(['bookDate']).value,
        adId: this.adId,
        userId: UserStorageService.getUserId()
      }

      this.clientService.bookService(bookServiceDto).subscribe(res =>{
        this.notification.success(
          'SUCCESS',
          'Request posted successfully',
          {nzDuration: 5000}
        );
        this.router.navigateByUrl('/client/bookings');
      })
    }
}
