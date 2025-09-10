import { Component } from '@angular/core';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { CompanyService } from '../../services/company.service';

@Component({
  selector: 'app-all-ads',
  templateUrl: './all-ads.component.html',
  styleUrl: './all-ads.component.scss'
})
export class AllAdsComponent {

  ads:any;

  constructor(private companyService: CompanyService,
    private notification: NzNotificationService) { }

  ngOnInit() {
    this.getAllAdsByUserId();
  }

  getAllAdsByUserId(){
    this.companyService.getAllAdsByUserId().subscribe(res => {
      this.ads = res;
    })
  }

  updateImage(image){
    return 'data:image/jpeg;base64,' + image;
  }

  deleteAd(adId:any){
    this.companyService.deleteAd(adId).subscribe(res => {
      this.notification
      .success(
        'SUCCESS',
        'Ad deleted successfully',
        { nzDuration: 5000}
      );
      this.getAllAdsByUserId();
    })
  }

}
