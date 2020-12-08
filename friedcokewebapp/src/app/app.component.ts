import { Component } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Tour of Heroes';
  private url = 'http://localhost:8080/api/notification';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient) { }

  // ngOnInit() {
  //   this.getMessage().subscribe((res:any) => {
  //     console.log(res)
  //   });
  // }
  
  // public getMessage() {
  //   return this.http.get(this.url);
  // }
  
}
