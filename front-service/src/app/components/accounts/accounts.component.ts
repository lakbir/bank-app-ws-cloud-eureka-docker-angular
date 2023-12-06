import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrl: './accounts.component.css'
})
export class AccountsComponent implements OnInit{
  accounts: any;
  constructor(private http: HttpClient){
  }
  ngOnInit(): void {
    this.http.get('http://localhost:8888/ACCOUNT-SERVICE/accounts')
    .subscribe({
      next: data => {
        this.accounts = data;
        console.log('Data received:', this.accounts);
      },
      error : err => {
        console.error('Error fetching data:', err);
      }
    })
  }

}
