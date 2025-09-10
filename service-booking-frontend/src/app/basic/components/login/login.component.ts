import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { error } from 'console';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from '../../services/auth/auth.service';
import { UserStorageService } from '../../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  validateForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private notification: NzNotificationService,
    private router: Router){}

ngOnInit(){
  this.validateForm = this.fb.group({
    username: [null, [Validators.required]],
    password: [null, [Validators.required]],
  })
}

submitForm(){
  this.authService.login(this.validateForm.get(['username'])!.value, this.validateForm.get(['password'])!.value)
    .subscribe( res => {
      console.log(res);
      if(UserStorageService.isClientLoggedIn()) {
        this.router.navigateByUrl('/client/dashboard')
        console.log("clietttt")
      } else if (UserStorageService.isCompanyLoggedIn()) {
        console.log("compaaanyy")
        this.router.navigateByUrl('company/dashboard')
      }

      console.log( UserStorageService.getUserRole())
      console.log( UserStorageService.isClientLoggedIn());
      console.log( UserStorageService.isCompanyLoggedIn())

    },
    error => {
      this.notification.error(
        'ERROR',
        'Bad Credintials',
        { nzDuration : 5000}
      )
    }
    );
}

}
