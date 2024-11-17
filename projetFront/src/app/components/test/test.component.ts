import { Component } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [HttpClientModule],
  template: `<h1>Test Component</h1>`,
})
export class TestComponent {
  constructor(private http: HttpClient) {
    this.http.get('https://jsonplaceholder.typicode.com/posts').subscribe(response => {
      console.log(response);
    });
  }
}
